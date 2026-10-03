package com.githubinsights.github_developer_insights.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.githubinsights.github_developer_insights.entity.Favorite;
import com.githubinsights.github_developer_insights.repository.FavoriteRepository;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    @Test
    void shouldAddFavorite() {

        when(
                favoriteRepository.findByOwnerAndRepositoryName(
                        "spring-projects",
                        "spring-boot"
                )
        ).thenReturn(Optional.empty());

        favoriteService.addFavorite(
                "spring-projects",
                "spring-boot"
        );

        verify(favoriteRepository).save(
                any(Favorite.class)
        );
    }

    @Test
    void shouldNotAddDuplicateFavorite() {

        Favorite existingFavorite = new Favorite();

        when(
                favoriteRepository.findByOwnerAndRepositoryName(
                        "spring-projects",
                        "spring-boot"
                )
        ).thenReturn(Optional.of(existingFavorite));

        favoriteService.addFavorite(
                "spring-projects",
                "spring-boot"
        );

        verify(
                favoriteRepository
        ).findByOwnerAndRepositoryName(
                "spring-projects",
                "spring-boot"
        );
    }
}