package com.githubinsights.github_developer_insights.cli;

import com.githubinsights.github_developer_insights.cli.util.CliErrorHandler;
import com.githubinsights.github_developer_insights.cli.util.InputValidator;
import com.githubinsights.github_developer_insights.exception.GitHubApiException;
import com.githubinsights.github_developer_insights.service.SearchService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "search",
        description = "Search GitHub repositories"
)
public class SearchCommand implements Runnable {

    private final SearchService searchService;

    @Option(
            names = "--query",
            description = "Repository search query",
            required = true
    )
    private String query;

    @Option(
            names = "--limit",
            description = "Maximum number of results",
            defaultValue = "10"
    )
    private int limit;

    @Option(
            names = "--language",
            description = "Filter repositories by programming language"
    )
    private String language;

    @Option(
            names = "--sort",
            description = "Sort results by: stars, forks, updated",
            defaultValue = "stars"
    )
    private String sort;

    @Option(
            names = "--order",
            description = "Sort order: asc or desc",
            defaultValue = "desc"
    )
    private String order;

    public SearchCommand(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public void run() {

        if (!InputValidator.isValidLimit(limit)) {

            System.out.println(
                    "Limit must be between 1 and 100."
            );

            return;
        }

        if (!InputValidator.isValidSort(sort)) {

            System.out.println(
                    "Invalid sort: " + sort
            );

            System.out.println(
                    "Supported: stars, forks, updated"
            );

            return;
        }

        if (!InputValidator.isValidOrder(order)) {

            System.out.println(
                    "Invalid order: " + order
            );

            System.out.println(
                    "Supported: asc, desc"
            );

            return;
        }

        try {

            searchService.searchRepositories(
                    query,
                    limit,
                    language,
                    sort,
                    order
            );

        } catch (GitHubApiException e) {

            CliErrorHandler.handle(e);
        }
    }
}