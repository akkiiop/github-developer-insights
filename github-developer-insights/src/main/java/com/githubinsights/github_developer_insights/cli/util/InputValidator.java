package com.githubinsights.github_developer_insights.cli.util;

public final class InputValidator {

    private InputValidator() {
    }

    public static boolean isValidRepository(String repository) {

        if (repository == null || repository.isBlank()) {
            return false;
        }

        String[] parts = repository.split("/", 2);

        return parts.length == 2
                && !parts[0].isBlank()
                && !parts[1].isBlank();
    }

    public static boolean isValidLimit(int limit) {

        return limit >= 1 && limit <= 100;
    }

    public static boolean isValidDuration(String duration) {

        if (duration == null) {
            return false;
        }

        return duration.equals("day")
                || duration.equals("week")
                || duration.equals("month")
                || duration.equals("year");
    }

    public static boolean isValidOrder(String order) {

        if (order == null) {
            return false;
        }

        return order.equals("asc")
                || order.equals("desc");
    }

    public static boolean isValidSort(String sort) {

        if (sort == null) {
            return false;
        }

        return sort.equals("stars")
                || sort.equals("forks")
                || sort.equals("updated");
    }
}