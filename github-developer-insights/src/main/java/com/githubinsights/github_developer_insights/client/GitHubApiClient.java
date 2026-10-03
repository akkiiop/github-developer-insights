package com.githubinsights.github_developer_insights.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.githubinsights.github_developer_insights.dto.RepositoryDto;
import com.githubinsights.github_developer_insights.dto.SearchResponseDto;
import org.springframework.web.client.RestClientException;
import com.githubinsights.github_developer_insights.exception.GitHubApiException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Component
public class GitHubApiClient {

    private final RestClient restClient;

    public GitHubApiClient(
            @Value("${github.api.base-url}") String baseUrl) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public RepositoryDto getRepository(
            String owner,
            String repository) {

        try {

            return restClient.get()
                    .uri(
                            "/repos/{owner}/{repository}",
                            owner,
                            repository
                    )
                    .retrieve()
                    .body(RepositoryDto.class);

        } catch (HttpClientErrorException e) {

            throw new GitHubApiException(
                    "GitHub rejected the request.",
                    e.getStatusCode().value(),
                    e
            );

        } catch (HttpServerErrorException e) {

            throw new GitHubApiException(
                    "GitHub server error.",
                    e.getStatusCode().value(),
                    e
            );

        } catch (RestClientException e) {

            throw new GitHubApiException(
                    "Could not connect to GitHub API.",
                    -1,
                    e
            );
        }
    }
    
    public SearchResponseDto searchRepositories(
            String query,
            int limit,
            String sort,
            String order) {

        try {

            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search/repositories")
                            .queryParam("q", query)
                            .queryParam("sort", sort)
                            .queryParam("order", order)
                            .queryParam("per_page", limit)
                            .build())
                    .retrieve()
                    .body(SearchResponseDto.class);

        } catch (HttpClientErrorException e) {

            throw new GitHubApiException(
                    "GitHub rejected the request.",
                    e.getStatusCode().value(),
                    e
            );

        } catch (HttpServerErrorException e) {

            throw new GitHubApiException(
                    "GitHub server error.",
                    e.getStatusCode().value(),
                    e
            );

        } catch (RestClientException e) {

            throw new GitHubApiException(
                    "Could not connect to GitHub API.",
                    -1,
                    e
            );
        }
    }
}