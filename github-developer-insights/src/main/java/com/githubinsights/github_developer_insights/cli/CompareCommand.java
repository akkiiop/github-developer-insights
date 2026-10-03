package com.githubinsights.github_developer_insights.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import com.githubinsights.github_developer_insights.exception.GitHubApiException;
import com.githubinsights.github_developer_insights.cli.util.CliErrorHandler;
import com.githubinsights.github_developer_insights.service.CompareService;
import com.githubinsights.github_developer_insights.cli.util.InputValidator;
@Command(
        name = "compare",
        description = "Compare two GitHub repositories"
)
public class CompareCommand implements Runnable {

    private final CompareService compareService;

    @Parameters(
            index = "0",
            description = "First repository in owner/name format"
    )
    private String repository1;

    @Parameters(
            index = "1",
            description = "Second repository in owner/name format"
    )
    private String repository2;

    public CompareCommand(CompareService compareService) {
        this.compareService = compareService;
    }

    @Override
    public void run() {

    	if (!InputValidator.isValidRepository(repository1)) {
            System.out.println(
                    "First repository must be in owner/name format."
            );
            return;
        }

    	if (!InputValidator.isValidRepository(repository2)) {
            System.out.println(
                    "Second repository must be in owner/name format."
            );
            return;
        }

        try {
            compareService.compareRepositories(
                    repository1,
                    repository2
            );
        } catch (GitHubApiException e) {
            CliErrorHandler.handle(e);
        }
    }

    
}