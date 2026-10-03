package com.githubinsights.github_developer_insights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.githubinsights.github_developer_insights.cli.FavoriteCommand;
import com.githubinsights.github_developer_insights.cli.GithubInsightsCommand;

import picocli.CommandLine;

@SpringBootApplication
public class GithubDeveloperInsightsApplication {

    public static void main(String[] args) {

        var context = SpringApplication.run(
                GithubDeveloperInsightsApplication.class,
                args
        );

        GithubInsightsCommand command =
                context.getBean(GithubInsightsCommand.class);

        CommandLine commandLine = new CommandLine(command);

        commandLine.addSubcommand(
                "trending",
                command.createTrendingCommand()
        );
        
        commandLine.addSubcommand(
                "search",
                command.createSearchCommand()
        );
        
        commandLine.addSubcommand(
                "repository",
                command.createRepositoryCommand()
        );
        
        commandLine.addSubcommand(
                "compare",
                command.createCompareCommand()
        );
        
        commandLine.addSubcommand(
                "stars",
                command.createStarsCommand()
        );
        
        FavoriteCommand favoriteCommand =
                command.createFavoriteCommand();

        CommandLine favoriteLine =
                new CommandLine(favoriteCommand);

        favoriteLine.addSubcommand(
                "remove",
                command.createRemoveFavoriteCommand()
        );

        favoriteLine.addSubcommand(
                "list",
                command.createListFavoriteCommand()
        );

        commandLine.addSubcommand(
                "favorite",
                favoriteLine.getCommandSpec()
        );
        
       
        
        int exitCode = commandLine.execute(args);

        System.exit(exitCode);
    }
}