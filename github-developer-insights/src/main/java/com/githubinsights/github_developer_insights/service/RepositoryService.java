package com.githubinsights.github_developer_insights.service;

import org.springframework.stereotype.Service;

import com.githubinsights.github_developer_insights.client.GitHubApiClient;
import com.githubinsights.github_developer_insights.dto.RepositoryDto;

@Service
public class RepositoryService {

    private final GitHubApiClient gitHubApiClient;

    public RepositoryService(
            GitHubApiClient gitHubApiClient) {

        this.gitHubApiClient = gitHubApiClient;
    }

    public void getRepositoryDetails(
            String owner,
            String repositoryName) {

        RepositoryDto repository =
                gitHubApiClient.getRepository(
                        owner,
                        repositoryName
                );

        if (repository == null) {
            System.out.println();
            System.out.println("Repository not found: " + owner + "/" + repositoryName);
            return;
        }

        int labelWidth = 16;
        int minWidth = 72;

        int longestContent = minWidth;
        if (repository.getHtmlUrl() != null) {
            longestContent = Math.max(longestContent, labelWidth + 2 + repository.getHtmlUrl().length());
        }
        if (repository.getFullName() != null) {
            longestContent = Math.max(longestContent, labelWidth + 2 + repository.getFullName().length());
        }
        if (repository.getName() != null) {
            longestContent = Math.max(longestContent, labelWidth + 2 + repository.getName().length());
        }

        int totalWidth = longestContent;

        String headerLine = "=".repeat(totalWidth);

        String title = "REPOSITORY DETAILS";
        int pad = Math.max(0, (totalWidth - title.length()) / 2);
        String centeredTitle = " ".repeat(pad) + title;

        System.out.println();
        System.out.println(headerLine);
        System.out.println(centeredTitle);
        System.out.println(headerLine);

        printProperty(labelWidth, "Name", repository.getName());
        printProperty(labelWidth, "Full Name", repository.getFullName());
        printWrappedProperty(labelWidth, totalWidth, "Description", repository.getDescription());
        printProperty(labelWidth, "Language", repository.getLanguage());
        printProperty(labelWidth, "Stars", formatNumber(repository.getStargazersCount()));
        printProperty(labelWidth, "Forks", formatNumber(repository.getForksCount()));
        printProperty(labelWidth, "Open Issues", formatNumber(repository.getOpenIssuesCount()));
        printProperty(labelWidth, "Default Branch", repository.getDefaultBranch());
        printProperty(labelWidth, "Archived", formatBoolean(repository.getArchived()));
        printProperty(labelWidth, "Fork", formatBoolean(repository.getFork()));
        printProperty(labelWidth, "URL", repository.getHtmlUrl());

        System.out.println(headerLine);
    }

    private void printProperty(int labelWidth, String label, String value) {
        System.out.printf("%-" + labelWidth + "s: %s%n", label, value != null ? value : "N/A");
    }

    private void printWrappedProperty(int labelWidth, int maxWidth, String label, String value) {
        if (value == null || value.isBlank()) {
            printProperty(labelWidth, label, "N/A");
            return;
        }

        int prefixLen = labelWidth + 2;
        int wrapWidth = maxWidth - prefixLen;

        if (wrapWidth <= 10 || value.length() <= wrapWidth) {
            printProperty(labelWidth, label, value);
            return;
        }

        String[] words = value.trim().split("\\s+");
        StringBuilder currentLine = new StringBuilder();
        boolean isFirstLine = true;

        for (String word : words) {
            if (currentLine.length() == 0) {
                currentLine.append(word);
            } else if (currentLine.length() + 1 + word.length() <= wrapWidth) {
                currentLine.append(" ").append(word);
            } else {
                if (isFirstLine) {
                    System.out.printf("%-" + labelWidth + "s: %s%n", label, currentLine.toString());
                    isFirstLine = false;
                } else {
                    System.out.println(" ".repeat(prefixLen) + currentLine.toString());
                }
                currentLine.setLength(0);
                currentLine.append(word);
            }
        }

        if (currentLine.length() > 0) {
            if (isFirstLine) {
                System.out.printf("%-" + labelWidth + "s: %s%n", label, currentLine.toString());
            } else {
                System.out.println(" ".repeat(prefixLen) + currentLine.toString());
            }
        }
    }

    private String formatNumber(Integer value) {
        if (value == null) {
            return "N/A";
        }
        return String.format("%,d", value);
    }

    private String formatBoolean(Boolean value) {
        return value == null ? "N/A" : String.valueOf(value);
    }
}