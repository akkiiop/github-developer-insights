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

        LocalDate startDate = calculateStartDate(duration);

        String query =
                "created:>" + startDate
                + " stars:>" + minimumStars;

        if (language != null && !language.isBlank()) {

            query = query + " language:" + language;
        }

        SearchResponseDto response =
                gitHubApiClient.searchRepositories(
                        query,
                        limit,
                        "stars",
                        "desc"
                );

        List<RepositoryDto> repositories =
                response.getItems();

        if (repositories == null || repositories.isEmpty()) {
            System.out.println();
            System.out.println("No trending repositories found for the specified period.");
            return;
        }

        int col1Width = 4;
        int maxRepoLength = "Repository".length();
        int maxLangLength = "Language".length();

        for (RepositoryDto repo : repositories) {
            if (repo.getFullName() != null && repo.getFullName().length() > maxRepoLength) {
                maxRepoLength = repo.getFullName().length();
            }
            if (repo.getLanguage() != null && repo.getLanguage().length() > maxLangLength) {
                maxLangLength = repo.getLanguage().length();
            }
        }

        int col2Width = Math.max(30, maxRepoLength + 2);
        int col3Width = Math.max(12, maxLangLength + 2);
        int col4Width = 12;

        int tableWidth = col1Width + 1 + col2Width + 1 + col3Width + 1 + col4Width;

        String langStr = (language != null && !language.isBlank()) ? language : "All";
        String metaLine = "Duration: " + duration
                + " | Language: " + langStr
                + " | Repositories Found: " + formatNumber(response.getTotalCount());

        int totalWidth = Math.max(tableWidth, metaLine.length());

        if (totalWidth > tableWidth) {
            col2Width += (totalWidth - tableWidth);
        }

        String headerLine = "=".repeat(totalWidth);
        String subLine = "-".repeat(totalWidth);

        String title = "GITHUB TRENDING REPOSITORIES";
        int pad = Math.max(0, (totalWidth - title.length()) / 2);
        String centeredTitle = " ".repeat(pad) + title;

        String format = "%-" + col1Width + "s %-" + col2Width + "s %-" + col3Width + "s %" + col4Width + "s%n";

        System.out.println();
        System.out.println(headerLine);
        System.out.println(centeredTitle);
        System.out.println(headerLine);
        System.out.println(metaLine);
        System.out.println(subLine);

        System.out.printf(
                format,
                "#",
                "Repository",
                "Language",
                "Stars"
        );

        System.out.println(subLine);

        int displayId = 1;

        for (RepositoryDto repository : repositories) {

            String lang = repository.getLanguage() == null
                    ? "N/A"
                    : repository.getLanguage();

            System.out.printf(
                    format,
                    String.valueOf(displayId),
                    repository.getFullName(),
                    lang,
                    formatNumber(repository.getStargazersCount())
            );

            displayId++;
        }

        System.out.println(headerLine);
    }

    private String formatNumber(Integer value) {

        if (value == null) {
            return "N/A";
        }

        return String.format("%,d", value);
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