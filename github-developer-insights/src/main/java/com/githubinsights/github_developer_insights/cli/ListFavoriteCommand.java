package com.githubinsights.github_developer_insights.cli;

import org.springframework.stereotype.Component;

import com.githubinsights.github_developer_insights.service.FavoriteService;

import picocli.CommandLine.Command;

@Component
@Command(
        name = "list",
        description = "Display all favorite repositories"
)
public class ListFavoriteCommand implements Runnable {

    private final FavoriteService favoriteService;

    public ListFavoriteCommand(
            FavoriteService favoriteService) {

        this.favoriteService = favoriteService;
    }

    @Override
    public void run() {
        favoriteService.listFavorites();
    }
}