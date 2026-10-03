package com.githubinsights.github_developer_insights.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import com.githubinsights.github_developer_insights.cli.util.ConsoleFormatter;

import com.githubinsights.github_developer_insights.entity.Favorite;
import com.githubinsights.github_developer_insights.repository.FavoriteRepository;

@Service
public class FavoriteService {

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
                    "Repository: "
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

        System.out.println("----------------------------------");

        System.out.println(
                "Repository: "
                        + owner
                        + "/"
                        + repositoryName
        );

        System.out.println(
                "Added At: "
                        + favorite.getCreatedAt()
        );
    }

    public void listFavorites() {

        List<Favorite> favorites =
                favoriteRepository.findAllByOrderByCreatedAtAsc();

        System.out.println();
        System.out.println(
                "======================================================================"
        );
        System.out.println(" Favorite Repositories");
        System.out.println(
                "======================================================================"
        );
        System.out.println();

        if (favorites.isEmpty()) {
            System.out.println("No favorite repositories found.");
            return;
        }

        System.out.printf(
                "%-5s %-35s %-20s%n",
                "ID",
                "Repository",
                "Added At"
        );

        System.out.println(
                "----------------------------------------------------------------------"
        );

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int displayId = 1;

        for (Favorite favorite : favorites) {

            String repository =
                    favorite.getOwner()
                            + "/"
                            + favorite.getRepositoryName();

            String addedAt =
                    favorite.getCreatedAt().format(formatter);

            System.out.printf(
                    "%-5d %-35s %-20s%n",
                    displayId,
                    repository,
                    addedAt
            );

            displayId++;
        }

        System.out.println(
                "----------------------------------------------------------------------"
        );

        System.out.println();
        System.out.println(
                "Total Favorites: " + favorites.size()
        );
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
                    "Repository: " + owner + "/" + repositoryName
            );
            return;
        }

        favoriteRepository.delete(existingFavorite.get());

        System.out.println();
        System.out.println("Repository removed from favorites.");
        System.out.println("----------------------------------");
        System.out.println(
                "Repository: " + owner + "/" + repositoryName
        );
    }
}