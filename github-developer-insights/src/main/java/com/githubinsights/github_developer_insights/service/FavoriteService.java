package com.githubinsights.github_developer_insights.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.githubinsights.github_developer_insights.entity.Favorite;
import com.githubinsights.github_developer_insights.repository.FavoriteRepository;

@Service
public class FavoriteService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(
            FavoriteRepository favoriteRepository) {

        this.favoriteRepository = favoriteRepository;
    }

    public void addFavorite(
            String owner,
            String repositoryName) {

        Optional<Favorite> existingFavorite =
                favoriteRepository.findByOwnerAndRepositoryName(
                        owner,
                        repositoryName
                );

        if (existingFavorite.isPresent()) {

            System.out.println();
            System.out.println(
                    "Repository is already in favorites."
            );

            System.out.println(
                    "Repository  : "
                            + owner
                            + "/"
                            + repositoryName
            );

            return;
        }

        Favorite favorite = new Favorite();

        favorite.setOwner(owner);

        favorite.setRepositoryName(repositoryName);

        favorite.setCreatedAt(
                LocalDateTime.now()
        );

        favoriteRepository.save(favorite);

        System.out.println();
        System.out.println(
                "Repository added to favorites."
        );

        System.out.println("--------------------------------------------------");

        System.out.println(
                "Repository  : "
                        + owner
                        + "/"
                        + repositoryName
        );

        System.out.println(
                "Added At    : "
                        + favorite.getCreatedAt().format(DATE_TIME_FORMATTER)
        );
    }

    public void listFavorites() {

        List<Favorite> favorites =
                favoriteRepository.findAllByOrderByCreatedAtAsc();

        if (favorites.isEmpty()) {
            System.out.println();
            System.out.println("No favorite repositories found.");
            return;
        }

        int col1Width = 4;
        int maxRepoLength = "Repository".length();
        for (Favorite fav : favorites) {
            String fullName = fav.getOwner() + "/" + fav.getRepositoryName();
            if (fullName.length() > maxRepoLength) {
                maxRepoLength = fullName.length();
            }
        }
        int col2Width = Math.max(35, maxRepoLength + 2);
        int col3Width = 20;

        int totalWidth = col1Width + 1 + col2Width + 1 + col3Width;

        String headerLine = "=".repeat(totalWidth);
        String subLine = "-".repeat(totalWidth);

        String title = "FAVORITE REPOSITORIES";
        int titlePadding = Math.max(0, (totalWidth - title.length()) / 2);
        String centeredTitle = " ".repeat(titlePadding) + title;

        String format = "%-" + col1Width + "s %-" + col2Width + "s %-" + col3Width + "s%n";

        System.out.println();
        System.out.println(headerLine);
        System.out.println(centeredTitle);
        System.out.println(headerLine);
        System.out.println();

        System.out.printf(
                format,
                "ID",
                "Repository",
                "Added At"
        );

        System.out.println(subLine);

        int displayId = 1;

        for (Favorite favorite : favorites) {

            String repository =
                    favorite.getOwner()
                            + "/"
                            + favorite.getRepositoryName();

            String addedAt =
                    favorite.getCreatedAt().format(DATE_TIME_FORMATTER);

            System.out.printf(
                    format,
                    String.valueOf(displayId),
                    repository,
                    addedAt
            );

            displayId++;
        }

        System.out.println(subLine);
        System.out.println(
                "Total Favorites : " + favorites.size()
        );
        System.out.println(headerLine);
    }

    public void removeFavorite(String owner, String repositoryName) {

        Optional<Favorite> existingFavorite =
                favoriteRepository.findByOwnerAndRepositoryName(
                        owner,
                        repositoryName
                );

        if (existingFavorite.isEmpty()) {
            System.out.println();
            System.out.println("Repository is not in favorites.");
            System.out.println(
                    "Repository  : " + owner + "/" + repositoryName
            );
            return;
        }

        favoriteRepository.delete(existingFavorite.get());

        System.out.println();
        System.out.println("Repository removed from favorites.");
        System.out.println("--------------------------------------------------");
        System.out.println(
                "Repository  : " + owner + "/" + repositoryName
        );
    }
}