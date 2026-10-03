package com.githubinsights.github_developer_insights.cli.util;

import com.githubinsights.github_developer_insights.exception.GitHubApiException;

public final class CliErrorHandler {

    private CliErrorHandler() {
    }

    public static void handle(GitHubApiException exception) {

        System.out.println();
        System.out.println("ERROR");
        System.out.println("----------------------------------");

        switch (exception.getStatusCode()) {

            case 400:
                System.out.println("Invalid request.");
                break;

            case 401:
                System.out.println("GitHub authentication failed.");
                break;

            case 403:
                System.out.println(
                        "GitHub API access was denied or rate limit was reached."
                );
                break;

            case 404:
                System.out.println(
                        "Repository or resource was not found."
                );
                break;

            case 422:
                System.out.println(
                        "GitHub could not process the request."
                );
                break;

            case -1:
                System.out.println(
                        "Could not connect to GitHub API."
                );
                break;

            default:
                if (exception.getStatusCode() >= 500) {
                    System.out.println(
                            "GitHub server error. Please try again later."
                    );
                } else {
                    System.out.println(
                            exception.getMessage()
                    );
                }
        }

        System.out.println("----------------------------------");
    }
}