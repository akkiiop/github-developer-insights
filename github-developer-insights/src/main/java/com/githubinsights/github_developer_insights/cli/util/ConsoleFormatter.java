package com.githubinsights.github_developer_insights.cli.util;

public final class ConsoleFormatter {

    private ConsoleFormatter() {
    }

    public static void printHeader(String title) {

        System.out.println();
        System.out.println(
                "============================================================"
        );

        System.out.println(" " + title);

        System.out.println(
                "============================================================"
        );

        System.out.println();
    }

    public static void printSeparator() {

        System.out.println(
                "------------------------------------------------------------"
        );
    }

    public static String formatNumber(Integer value) {

        if (value == null) {
            return "N/A";
        }

        return String.format("%,d", value);
    }

    public static String formatValue(Object value) {

        if (value == null) {
            return "N/A";
        }

        return String.valueOf(value);
    }
}