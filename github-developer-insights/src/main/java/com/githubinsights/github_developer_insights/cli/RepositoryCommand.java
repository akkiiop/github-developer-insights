package com.githubinsights.github_developer_insights.cli;

import com.githubinsights.github_developer_insights.cli.util.CliErrorHandler;
import com.githubinsights.github_developer_insights.cli.util.InputValidator;
import com.githubinsights.github_developer_insights.exception.GitHubApiException;
import com.githubinsights.github_developer_insights.service.RepositoryService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "repository",
        description = "Display details of a GitHub repository"
)
public class RepositoryCommand implements Runnable {

    private final RepositoryService repositoryService;

    @Parameters(
            index = "0",
            description = "Repository owner/name"
    )
    private String repository;

    public RepositoryCommand(
            RepositoryService repositoryService) {

        this.repositoryService = repositoryService;
    }

    @Override
    public void run() {

        if (!InputValidator.isValidRepository(repository)) {

            System.out.println(
                    "Repository must be in owner/name format."
            );

            System.out.println(
                    "Example: repository spring-projects/spring-boot"
            );

            return;
        }

        String[] parts = repository.split("/", 2);

        try {

            repositoryService.getRepositoryDetails(
                    parts[0],
                    parts[1]
            );

        } catch (GitHubApiException e) {

            CliErrorHandler.handle(e);
        }
    }
}