package com.githubinsights.github_developer_insights.cli.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    void shouldAcceptValidRepository() {

        boolean result =
                InputValidator.isValidRepository(
                        "spring-projects/spring-boot"
                );

        assertTrue(result);
    }

    @Test
    void shouldRejectInvalidRepository() {

        boolean result =
                InputValidator.isValidRepository(
                        "spring-boot"
                );

        assertFalse(result);
    }

    @Test
    void shouldAcceptValidLimit() {

        assertTrue(
                InputValidator.isValidLimit(10)
        );
    }

    @Test
    void shouldRejectInvalidLimit() {

        assertFalse(
                InputValidator.isValidLimit(101)
        );
    }

    @Test
    void shouldAcceptValidDuration() {

        assertTrue(
                InputValidator.isValidDuration("week")
        );
    }

    @Test
    void shouldRejectInvalidDuration() {

        assertFalse(
                InputValidator.isValidDuration("decade")
        );
    }

    @Test
    void shouldAcceptValidOrder() {

        assertTrue(
                InputValidator.isValidOrder("desc")
        );
    }

    @Test
    void shouldRejectInvalidOrder() {

        assertFalse(
                InputValidator.isValidOrder("random")
        );
    }

    @Test
    void shouldAcceptValidSort() {

        assertTrue(
                InputValidator.isValidSort("stars")
        );
    }

    @Test
    void shouldRejectInvalidSort() {

        assertFalse(
                InputValidator.isValidSort("random")
        );
    }
}