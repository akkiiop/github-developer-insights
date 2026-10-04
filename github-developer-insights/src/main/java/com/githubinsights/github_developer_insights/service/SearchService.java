package com.githubinsights.github_developer_insights.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.githubinsights.github_developer_insights.client.GitHubApiClient;
import com.githubinsights.github_developer_insights.dto.RepositoryDto;
import com.githubinsights.github_developer_insights.dto.SearchResponseDto;

@Service
public class SearchService {

    private final GitHubApiClient gitHubApiClient;

    public SearchService(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }
    
    

    public void searchRepositories(
            String searchQuery,
            int limit, 
            String language,
            String sort,
            String order) {

        String finalQuery = searchQuery;

        if (language != null && !language.isBlank()) {

            finalQuery =
                    finalQuery + " language:" + language;
        }

        SearchResponseDto response =
                gitHubApiClient.searchRepositories(
                        finalQuery,
                        limit,
                        sort,
                        order
                );

        List<RepositoryDto> repositories =
                response.getItems();

        System.out.println();
        System.out.println("GitHub Repository Search");
        System.out.println("----------------------------------");
        System.out.println(
                "Query: " + searchQuery
        );

        if (language != null && !language.isBlank()) {

            System.out.println(
                    "Language: " + language
            );
        }

        System.out.println(
                "Sort: " + sort
                + " | Order: " + order
        );

        System.out.println(
                "Repositories Found: "
                + response.getTotalCount()
        );

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

            System.out.println(
                    "Forks: "
                    + repository.getForksCount()
            );

            System.out.println("----------------------------------");
        }
    }
}