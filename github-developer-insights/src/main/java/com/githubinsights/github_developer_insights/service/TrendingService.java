package com.githubinsights.github_developer_insights.service;


import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;

import com.githubinsights.github_developer_insights.dto.RepositoryDto;
import com.githubinsights.github_developer_insights.dto.SearchResponseDto;
import com.githubinsights.github_developer_insights.client.GitHubApiClient;

@Service
public class TrendingService {

    private final GitHubApiClient gitHubApiClient;
    
    @Value("${github.api.min-stars}")
    private int minimumStars;
    
    public TrendingService(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    public void getTrendingRepositories(
            String duration,
            int limit, String language) {

        System.out.println("GitHub Trending Repositories");
        System.out.println("----------------------------------");
        System.out.println("Duration: " + duration);
        System.out.println("Limit: " + limit);

        LocalDate startDate = calculateStartDate(duration);

        String query =
                "created:>" + startDate
                + " stars:>" + minimumStars;

        if (language != null && !language.isBlank()) {

            query = query + " language:" + language;
        }

        System.out.println(
                "Search Query: " + query
        );
        
        SearchResponseDto response =
                gitHubApiClient.searchRepositories(
                        query,
                        limit,
                        "stars",
                        "desc"
                );

        List<RepositoryDto> repositories =
                response.getItems();

        System.out.println();
        System.out.println("Repositories Found: "
                + response.getTotalCount());

        System.out.println();

        for (RepositoryDto repository : repositories) {

            System.out.println(
                    repository.getFullName()
            );

            System.out.println(
                    "Language: "
                    + repository.getLanguage()
            );

            System.out.println(
                    "Stars: "
                    + repository.getStargazersCount()
            );

            System.out.println("----------------------------------");
        }
    }
    
    
    private LocalDate calculateStartDate(String duration) {

        LocalDate today = LocalDate.now();

        switch (duration) {

            case "day":
                return today.minusDays(1);

            case "week":
                return today.minusDays(7);

            case "month":
                return today.minusMonths(1);

            case "year":
                return today.minusYears(1);

            default:
                return today.minusDays(7);
        }
    }
}