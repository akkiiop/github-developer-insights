package com.githubinsights.github_developer_insights.cli;

import com.githubinsights.github_developer_insights.cli.util.CliErrorHandler;
import com.githubinsights.github_developer_insights.exception.GitHubApiException;
import com.githubinsights.github_developer_insights.service.StarTrackingService;
import com.githubinsights.github_developer_insights.cli.util.InputValidator;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
        name = "stars",
        description = "Track GitHub repository star growth"
)
public class StarsCommand implements Runnable {

    @Option(
            names = "--history",
            description = "Display complete star history"
    )
    private boolean history;

    private final StarTrackingService starTrackingService;

    @Parameters(
            index = "0",
            description = "Repository in owner/name format"
    )
    private String repository;

    public StarsCommand(
            StarTrackingService starTrackingService) {

        this.starTrackingService = starTrackingService;
    }

    @Override
    public void run() {

    	if (!InputValidator.isValidRepository(repository)) {

            System.out.println(
                    "Repository must be in owner/name format."
            );

            System.out.println(
                    "Example: stars spring-projects/spring-boot"
            );

            return;
        }

        String[] parts = repository.split("/", 2);

        try {

            starTrackingService.trackStars(
                    parts[0],
                    parts[1]
            );

            if (history) {

                starTrackingService.showStarHistory(
                        parts[0],
                        parts[1]
                );

            } else {

                starTrackingService.showStarGrowth(
                        parts[0],
                        parts[1]
                );
            }

        } catch (GitHubApiException e) {

            CliErrorHandler.handle(e);
        }
    }

    
}