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

        System.out.println();
        System.out.println("Repository Details");
        System.out.println("----------------------------------");

        System.out.println(
                "Name: " + repository.getName()
        );

        System.out.println(
                "Full Name: "
                + repository.getFullName()
        );

        System.out.println(
                "Description: "
                + repository.getDescription()
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

        System.out.println(
                "Open Issues: "
                + repository.getOpenIssuesCount()
        );

        System.out.println(
                "Default Branch: "
                + repository.getDefaultBranch()
        );

        System.out.println(
                "Archived: "
                + repository.getArchived()
        );

        System.out.println(
                "Fork: "
                + repository.getFork()
        );

        System.out.println(
                "URL: "
                + repository.getHtmlUrl()
        );
    }
}