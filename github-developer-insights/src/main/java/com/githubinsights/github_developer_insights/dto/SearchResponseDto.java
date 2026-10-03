package com.githubinsights.github_developer_insights.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponseDto {
	
	@JsonProperty("total_count")
    private Integer totalCount;
	
	@JsonProperty("incomplete_results")
    private Boolean incompleteResults;

    private List<RepositoryDto> items;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<RepositoryDto> getItems() {
        return items;
    }

    public void setItems(List<RepositoryDto> items) {
        this.items = items;
    }
}