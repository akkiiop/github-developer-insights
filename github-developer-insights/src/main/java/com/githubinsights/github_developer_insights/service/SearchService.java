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

        if (repositories == null || repositories.isEmpty()) {
            System.out.println();
            System.out.println("No repositories found matching your query.");
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
        int col5Width = 12;

        int tableWidth = col1Width + 1 + col2Width + 1 + col3Width + 1 + col4Width + 1 + col5Width;

        StringBuilder meta = new StringBuilder();
        meta.append("Query: ").append(searchQuery);
        if (language != null && !language.isBlank()) {
            meta.append(" | Language: ").append(language);
        }
        meta.append(" | Sort: ").append(sort).append(" (").append(order).append(")");
        meta.append(" | Repositories Found: ").append(formatNumber(response.getTotalCount()));

        String metaLine = meta.toString();
        int totalWidth = Math.max(tableWidth, metaLine.length());

        if (totalWidth > tableWidth) {
            col2Width += (totalWidth - tableWidth);
        }

        String headerLine = "=".repeat(totalWidth);
        String subLine = "-".repeat(totalWidth);

        String title = "GITHUB REPOSITORY SEARCH";
        int pad = Math.max(0, (totalWidth - title.length()) / 2);
        String centeredTitle = " ".repeat(pad) + title;

        String format = "%-" + col1Width + "s %-" + col2Width + "s %-" + col3Width + "s %" + col4Width + "s %" + col5Width + "s%n";

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
                "Stars",
                "Forks"
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
                    formatNumber(repository.getStargazersCount()),
                    formatNumber(repository.getForksCount())
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
}