package com.githubinsights.github_developer_insights.cli;

import org.springframework.stereotype.Component;

import com.githubinsights.github_developer_insights.service.SearchService;
import com.githubinsights.github_developer_insights.service.TrendingService;
import com.githubinsights.github_developer_insights.service.RepositoryService;
import com.githubinsights.github_developer_insights.service.CompareService;
import com.githubinsights.github_developer_insights.service.StarTrackingService;
import com.githubinsights.github_developer_insights.service.FavoriteService;

import picocli.CommandLine.Command;

@Component
@Command(
        name = "github-insights",
        description = "GitHub Developer Insights CLI",
        mixinStandardHelpOptions = true
)
public class GithubInsightsCommand implements Runnable {
	
	private final TrendingService trendingService;
	private final SearchService searchService;
	private final RepositoryService repositoryService;
	private final CompareService compareService;
	private final StarTrackingService starTrackingService;
	private final FavoriteService favoriteService;
	private final RemoveFavoriteCommand removeFavoriteCommand;
	private final ListFavoriteCommand listFavoriteCommand;
	
	public GithubInsightsCommand(
	        TrendingService trendingService,
	        SearchService searchService,
	        RepositoryService repositoryService,
	        CompareService compareService,
	        StarTrackingService starTrackingService,
	        FavoriteService favoriteService,
	        RemoveFavoriteCommand removeFavoriteCommand,
	        ListFavoriteCommand listFavoriteCommand) {

	    this.trendingService = trendingService;
	    this.searchService = searchService;
	    this.repositoryService = repositoryService;
	    this.compareService = compareService;
	    this.starTrackingService = starTrackingService;
	    this.favoriteService = favoriteService;
	    this.removeFavoriteCommand = removeFavoriteCommand;
	    this.listFavoriteCommand = listFavoriteCommand;
	}
	
    @Override
    public void run() {
    	System.out.println("Use a command. Try:");
        System.out.println("github-insights trending");
    }
    
    public TrendingCommand createTrendingCommand() {
    	return new TrendingCommand(trendingService);
    }
    
    public SearchCommand createSearchCommand() {

        return new SearchCommand(
                searchService
        );
    }
    
    public RepositoryCommand createRepositoryCommand() {

        return new RepositoryCommand(
                repositoryService
        );
    }
    
    public CompareCommand createCompareCommand() {
        return new CompareCommand(compareService);
    }
    
    public StarsCommand createStarsCommand() {
        return new StarsCommand(starTrackingService);
    }
    
    public FavoriteCommand createFavoriteCommand() {
        return new FavoriteCommand(favoriteService);
    }
    
    public RemoveFavoriteCommand createRemoveFavoriteCommand() {
        return removeFavoriteCommand;
    }
    
    public ListFavoriteCommand createListFavoriteCommand() {
        return listFavoriteCommand;
    }
   
}
