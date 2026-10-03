package com.githubinsights.github_developer_insights.cli;

import org.springframework.stereotype.Component;

import com.githubinsights.github_developer_insights.service.FavoriteService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import com.githubinsights.github_developer_insights.cli.util.InputValidator;
@Component
@Command(
        name = "remove",
        description = "Remove a repository from favorites"
)
public class RemoveFavoriteCommand implements Runnable {

    private final FavoriteService favoriteService;

    @Parameters(
            index = "0",
            description = "Repository in owner/name format"
    )
    private String repository;

    public RemoveFavoriteCommand(
            FavoriteService favoriteService) {

        this.favoriteService = favoriteService;
    }

    @Override
    public void run() {

    	if (!InputValidator.isValidRepository(repository)) {

            System.out.println(
                    "Repository must be in owner/name format."
            );

            System.out.println(
                    "Example: favorite remove spring-projects/spring-boot"
            );

            return;
        }

        String[] parts = repository.split("/", 2);

        favoriteService.removeFavorite(
                parts[0],
                parts[1]
        );
    }

    
}