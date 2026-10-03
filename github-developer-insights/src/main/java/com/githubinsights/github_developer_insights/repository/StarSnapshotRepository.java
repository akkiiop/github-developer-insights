package com.githubinsights.github_developer_insights.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githubinsights.github_developer_insights.entity.StarSnapshot;

public interface StarSnapshotRepository
        extends JpaRepository<StarSnapshot, Long> {

    List<StarSnapshot> findByOwnerAndRepositoryNameOrderByCapturedAtAsc(
            String owner,
            String repositoryName
    );
}