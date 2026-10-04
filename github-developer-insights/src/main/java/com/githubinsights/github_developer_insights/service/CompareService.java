package com.githubinsights.github_developer_insights.service;

import org.springframework.stereotype.Service;

import com.githubinsights.github_developer_insights.client.GitHubApiClient;
import com.githubinsights.github_developer_insights.dto.RepositoryDto;

@Service
public class CompareService {

    private final GitHubApiClient gitHubApiClient;

    public CompareService(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    public void compareRepositories(
            String repository1,
            String repository2) {

        String[] repo1Parts = repository1.split("/", 2);
        String[] repo2Parts = repository2.split("/", 2);

        RepositoryDto repo1 =
                gitHubApiClient.getRepository(
                        repo1Parts[0],
                        repo1Parts[1]
                );

        RepositoryDto repo2 =
                gitHubApiClient.getRepository(
                        repo2Parts[0],
                        repo2Parts[1]
                );

        int col1Width = 16;
        int col2Width = Math.max(16, repo1.getName().length() + 2);
        int col3Width = Math.max(16, repo2.getName().length() + 2);
        int col4Width = 15;

        int totalWidth = col1Width + 1 + col2Width + 1 + col3Width + 1 + col4Width;

        String headerLine = "=".repeat(totalWidth);
        String subLine = "-".repeat(totalWidth);

        String title = "REPOSITORY COMPARISON";
        int titlePadding = Math.max(0, (totalWidth - title.length()) / 2);
        String centeredTitle = " ".repeat(titlePadding) + title;

        String format = "%-" + col1Width + "s %" + col2Width + "s %" + col3Width + "s %" + col4Width + "s%n";

        System.out.println();
        System.out.println(headerLine);
        System.out.println(centeredTitle);
        System.out.println(headerLine);
        System.out.println();

        System.out.printf(
                format,
                "Metric",
                repo1.getName(),
                repo2.getName(),
                "Difference"
        );

        System.out.println(subLine);

        printComparison(
                format,
                "Stars",
                repo1.getStargazersCount(),
                repo2.getStargazersCount()
        );

        printComparison(
                format,
                "Forks",
                repo1.getForksCount(),
                repo2.getForksCount()
        );

        printComparison(
                format,
                "Open Issues",
                repo1.getOpenIssuesCount(),
                repo2.getOpenIssuesCount()
        );

        System.out.println(subLine);

        printTextComparison(
                format,
                "Language",
                repo1.getLanguage(),
                repo2.getLanguage()
        );

        printTextComparison(
                format,
                "Archived",
                repo1.getArchived(),
                repo2.getArchived()
        );

        printTextComparison(
                format,
                "Fork",
                repo1.getFork(),
                repo2.getFork()
        );

        System.out.println(headerLine);
    }

    private void printComparison(
            String format,
            String metric,
            Integer value1,
            Integer value2) {

        int first = value1 == null ? 0 : value1;
        int second = value2 == null ? 0 : value2;

        int difference = second - first;

        System.out.printf(
                format,
                metric,
                formatNumber(first),
                formatNumber(second),
                formatDifference(difference)
        );
    }

    private void printTextComparison(
            String format,
            String metric,
            Object value1,
            Object value2) {

        System.out.printf(
                format,
                metric,
                formatValue(value1),
                formatValue(value2),
                compareTextValues(value1, value2)
        );
    }

    private String compareTextValues(Object value1, Object value2) {

        if (value1 == null && value2 == null) {
            return "N/A";
        }

        if (value1 == null || value2 == null) {
            return "Different";
        }

        if (value1 instanceof String s1 && value2 instanceof String s2) {
            return s1.equalsIgnoreCase(s2) ? "Same" : "Different";
        }

        return value1.equals(value2) ? "Same" : "Different";
    }

    private String formatNumber(int value) {
        return String.format("%,d", value);
    }

    private String formatDifference(int value) {

        if (value > 0) {
            return "+" + formatNumber(value);
        }

        return formatNumber(value);
    }

    private String formatValue(Object value) {

        return value == null
                ? "N/A"
                : String.valueOf(value);
    }
}