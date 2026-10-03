package com.githubinsights.github_developer_insights.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githubinsights.github_developer_insights.entity.Favorite;

public interface FavoriteRepository
        extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByOwnerAndRepositoryName(
            String owner,
            String repositoryName
    );

    List<Favorite> findAllByOrderByCreatedAtAsc();
}