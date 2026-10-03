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

        System.out.println();
        System.out.println("================================================================");
        System.out.println("                    REPOSITORY COMPARISON");
        System.out.println("================================================================");
        System.out.println();

        System.out.printf(
                "%-20s %15s %15s %15s%n",
                "Metric",
                repo1.getName(),
                repo2.getName(),
                "Difference"
        );

        System.out.println("----------------------------------------------------------------");

        printComparison(
                "Stars",
                repo1.getStargazersCount(),
                repo2.getStargazersCount()
        );

        printComparison(
                "Forks",
                repo1.getForksCount(),
                repo2.getForksCount()
        );

        printComparison(
                "Open Issues",
                repo1.getOpenIssuesCount(),
                repo2.getOpenIssuesCount()
        );

        System.out.println("----------------------------------------------------------------");

        printTextComparison(
                "Language",
                repo1.getLanguage(),
                repo2.getLanguage()
        );

        printTextComparison(
                "Archived",
                repo1.getArchived(),
                repo2.getArchived()
        );

        printTextComparison(
                "Fork",
                repo1.getFork(),
                repo2.getFork()
        );

        System.out.println("================================================================");
    }

    private void printComparison(
            String metric,
            Integer value1,
            Integer value2) {

        int first = value1 == null ? 0 : value1;
        int second = value2 == null ? 0 : value2;

        int difference = second - first;

        System.out.printf(
                "%-20s %15s %15s %15s%n",
                metric,
                formatNumber(first),
                formatNumber(second),
                formatDifference(difference)
        );
    }

    private void printTextComparison(
            String metric,
            Object value1,
            Object value2) {

        System.out.printf(
                "%-20s %15s %15s %15s%n",
                metric,
                formatValue(value1),
                formatValue(value2),
                "-"
        );
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