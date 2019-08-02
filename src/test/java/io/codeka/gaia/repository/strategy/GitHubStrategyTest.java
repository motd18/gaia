package io.codeka.gaia.repository.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GitHubStrategyTest {

    private GitHubStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new GitHubStrategy();
    }

    @Test
    void getPattern_shouldReturnPattern() {
        assertNotNull(strategy.getPattern());
    }

    @Test
    void matches_shouldReturnTrueForValidUrl() {
        assertTrue(strategy.matches("https://github.com/CodeKaio/gaia.git"));
    }

    @Test
    void matches_shouldReturnFalseForInvalidUrl() {
        assertFalse(strategy.matches("https://gitlab.com/CodeKaio/gaia.git"));
    }

    @Test
    void getRawUrl_shouldReturnRawUrl() {
        // given
        var url = "https://github.com/CodeKaio/gaia.git";

        // when
        var result = strategy.getRawUrl(url, "branch", "directory");

        // then
        assertThat(result).isNotNull().isEqualTo("https://raw.githubusercontent.com/CodeKaio/gaia/branch/directory");
    }

    @Test
    void getRawUrl_shouldManageEmptyParameters() {
        // given
        var url = "https://github.com/CodeKaio/gaia.git";

        // when
        var result = strategy.getRawUrl(url, null, null);

        // then
        assertThat(result).isNotNull().isEqualTo("https://raw.githubusercontent.com/CodeKaio/gaia/master");
    }

    @Test
    void getRawUrl_shouldReturnEmptyForUnmatchedUrl() {
        // given
        var url = "https://gitlab.com/CodeKaio/gaia.git";

        // when
        var result = strategy.getRawUrl(url, null, null);

        // then
        assertThat(result).isEmpty();
    }

}