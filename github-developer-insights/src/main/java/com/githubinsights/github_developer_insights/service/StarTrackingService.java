package com.githubinsights.github_developer_insights.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.githubinsights.github_developer_insights.client.GitHubApiClient;
import com.githubinsights.github_developer_insights.dto.RepositoryDto;
import com.githubinsights.github_developer_insights.entity.StarSnapshot;
import com.githubinsights.github_developer_insights.repository.StarSnapshotRepository;

@Service
public class StarTrackingService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final GitHubApiClient gitHubApiClient;

    private final StarSnapshotRepository starSnapshotRepository;

    public StarTrackingService(
            GitHubApiClient gitHubApiClient,
            StarSnapshotRepository starSnapshotRepository) {

        this.gitHubApiClient = gitHubApiClient;
        this.starSnapshotRepository = starSnapshotRepository;
    }

    public void trackStars(
            String owner,
            String repositoryName) {

        RepositoryDto repository =
                gitHubApiClient.getRepository(
                        owner,
                        repositoryName
                );

        StarSnapshot snapshot = new StarSnapshot();

        snapshot.setOwner(owner);

        snapshot.setRepositoryName(repositoryName);

        snapshot.setStars(
                repository.getStargazersCount()
        );

        snapshot.setCapturedAt(
                LocalDateTime.now()
        );

        starSnapshotRepository.save(snapshot);

        System.out.println();
        System.out.println("Star snapshot saved successfully.");
        System.out.println("--------------------------------------------------");

        System.out.println(
                "Repository  : "
                        + repository.getFullName()
        );

        System.out.println(
                "Stars       : "
                        + formatNumber(repository.getStargazersCount())
        );

        System.out.println(
                "Captured At : "
                        + snapshot.getCapturedAt().format(DATE_TIME_FORMATTER)
        );
    }

    public void showStarGrowth(
            String owner,
            String repositoryName) {

        List<StarSnapshot> snapshots =
                starSnapshotRepository
                        .findByOwnerAndRepositoryNameOrderByCapturedAtAsc(
                                owner,
                                repositoryName
                        );

        if (snapshots.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No star snapshots found for this repository."
            );

            return;
        }

        StarSnapshot firstSnapshot =
                snapshots.get(0);

        StarSnapshot latestSnapshot =
                snapshots.get(
                        snapshots.size() - 1
                );

        int firstStars =
                firstSnapshot.getStars();

        int latestStars =
                latestSnapshot.getStars();

        int growth =
                latestStars - firstStars;

        System.out.println();
        System.out.println("==================================================");
        System.out.println("                   STAR GROWTH");
        System.out.println("==================================================");

        System.out.println(
                "Repository  : "
                        + owner
                        + "/"
                        + repositoryName
        );

        System.out.println();

        System.out.println(
                "First Snapshot : "
                        + formatNumber(firstStars)
        );

        System.out.println(
                "Latest Snapshot: "
                        + formatNumber(latestStars)
        );

        System.out.println(
                "Growth         : "
                        + formatGrowth(growth)
        );

        System.out.println(
                "Snapshots      : "
                        + snapshots.size()
        );

        System.out.println(
                "First Captured : "
                        + firstSnapshot.getCapturedAt().format(DATE_TIME_FORMATTER)
        );

        System.out.println(
                "Latest Captured: "
                        + latestSnapshot.getCapturedAt().format(DATE_TIME_FORMATTER)
        );

        System.out.println("==================================================");
    }

    public void showStarHistory(
            String owner,
            String repositoryName) {

        List<StarSnapshot> snapshots =
                starSnapshotRepository
                        .findByOwnerAndRepositoryNameOrderByCapturedAtAsc(
                                owner,
                                repositoryName
                        );

        if (snapshots.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No star history found for this repository."
            );

            return;
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("                   STAR HISTORY");
        System.out.println("==================================================");

        System.out.println(
                "Repository  : "
                        + owner
                        + "/"
                        + repositoryName
        );

        System.out.println();

        System.out.printf(
                "%-28s %21s%n",
                "Captured At",
                "Stars"
        );

        System.out.println(
                "--------------------------------------------------"
        );

        for (StarSnapshot snapshot : snapshots) {

            System.out.printf(
                    "%-28s %21s%n",
                    snapshot.getCapturedAt().format(DATE_TIME_FORMATTER),
                    formatNumber(snapshot.getStars())
            );
        }

        System.out.println(
                "--------------------------------------------------"
        );

        StarSnapshot firstSnapshot =
                snapshots.get(0);

        StarSnapshot latestSnapshot =
                snapshots.get(
                        snapshots.size() - 1
                );

        int growth =
                latestSnapshot.getStars()
                        - firstSnapshot.getStars();

        System.out.println(
                "Total Growth : "
                        + formatGrowth(growth)
        );

        System.out.println(
                "Snapshots    : "
                        + snapshots.size()
        );

        System.out.println("==================================================");
    }

    private String formatNumber(Integer value) {

        if (value == null) {
            return "N/A";
        }

        return String.format("%,d", value);
    }

    private String formatGrowth(int growth) {

        if (growth > 0) {
            return "+" + String.format("%,d", growth);
        }

        return String.format("%,d", growth);
    }
}