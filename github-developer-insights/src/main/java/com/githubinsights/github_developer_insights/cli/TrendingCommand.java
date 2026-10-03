package com.githubinsights.github_developer_insights.cli;

import com.githubinsights.github_developer_insights.cli.util.CliErrorHandler;
import com.githubinsights.github_developer_insights.exception.GitHubApiException;
import com.githubinsights.github_developer_insights.service.TrendingService;
import com.githubinsights.github_developer_insights.cli.util.InputValidator;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "trending",
        description = "Display trending GitHub repositories"
)
public class TrendingCommand implements Runnable {

    private final TrendingService trendingService;

    @Option(
            names = "--duration",
            description = "Time duration",
            defaultValue = "week"
    )
    private String duration;

    @Option(
            names = "--limit",
            description = "Maximum number of repositories"
    )
    private int limit = 10;

    @Option(
            names = "--language",
            description = "Filter repositories by programming language"
    )
    private String language;

    public TrendingCommand(
            TrendingService trendingService) {

        this.trendingService = trendingService;
    }

    @Override
    public void run() {

    	if (!InputValidator.isValidDuration(duration)) {

    	    System.out.println(
    	            "Invalid duration: " + duration
    	    );

    	    System.out.println(
    	            "Supported: day, week, month, year"
    	    );

    	    return;
    	}

    	if (!InputValidator.isValidLimit(limit)) {

    	    System.out.println(
    	            "Limit must be between 1 and 100."
    	    );

    	    return;
    	}

        try {

            trendingService.getTrendingRepositories(
                    duration,
                    limit,
                    language
            );

        } catch (GitHubApiException e) {

            CliErrorHandler.handle(e);
        }
    }
}