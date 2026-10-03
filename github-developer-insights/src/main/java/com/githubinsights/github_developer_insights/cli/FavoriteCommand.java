package com.githubinsights.github_developer_insights.cli;

import org.springframework.stereotype.Component;

import com.githubinsights.github_developer_insights.service.FavoriteService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(
        name = "favorite",
        description = "Manage favorite GitHub repositories"
)
public class FavoriteCommand implements Runnable {

    private final FavoriteService favoriteService;

    @Parameters(
            index = "0",
            description = "Repository in owner/name format",
            arity = "0..1"
    )
    private String repository;

    public FavoriteCommand(
            FavoriteService favoriteService) {

        this.favoriteService = favoriteService;
    }

    @Override
    public void run() {

        if (repository == null) {

            System.out.println();
            System.out.println("Favorite Repository Commands");
            System.out.println("----------------------------------");
            System.out.println(
                    "favorite owner/repository"
            );
            System.out.println(
                    "favorite remove owner/repository"
            );

            return;
        }

        if (!isValidRepository()) {

            System.out.println(
                    "Repository must be in owner/name format."
            );

            System.out.println(
                    "Example: favorite spring-projects/spring-boot"
            );

            return;
        }

        String[] parts = repository.split("/", 2);

        favoriteService.addFavorite(
                parts[0],
                parts[1]
        );
    }

    private boolean isValidRepository() {

        if (repository == null || repository.isBlank()) {
            return false;
        }

        String[] parts = repository.split("/", 2);

        return parts.length == 2
                && !parts[0].isBlank()
                && !parts[1].isBlank();
    }
}