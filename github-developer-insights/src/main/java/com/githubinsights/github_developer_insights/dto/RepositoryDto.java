package com.githubinsights.github_developer_insights.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryDto {

    private Long id;

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("html_url")
    private String htmlUrl;

    private String language;

    @JsonProperty("stargazers_count")
    private Integer stargazersCount;

    @JsonProperty("forks_count")
    private Integer forksCount;

    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;

    private Boolean fork;

    private Boolean archived;

    @JsonProperty("default_branch")
    private String defaultBranch;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getStargazersCount() {
		return stargazersCount;
	}

	public void setStargazersCount(Integer stargazersCount) {
		this.stargazersCount = stargazersCount;
	}

	public Integer getForksCount() {
		return forksCount;
	}

	public void setForksCount(Integer forksCount) {
		this.forksCount = forksCount;
	}

	public Integer getOpenIssuesCount() {
		return openIssuesCount;
	}

	public void setOpenIssuesCount(Integer openIssuesCount) {
		this.openIssuesCount = openIssuesCount;
	}

	public Boolean getFork() {
		return fork;
	}

	public void setFork(Boolean fork) {
		this.fork = fork;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public String getDefaultBranch() {
		return defaultBranch;
	}

	public void setDefaultBranch(String defaultBranch) {
		this.defaultBranch = defaultBranch;
	}
    
    
}