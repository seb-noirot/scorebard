import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    // Test that the constructor sets the home team and away team
    void testMatchConstructor() {
        // Create a match
        Match match = new Match("home", "away");
        // Check that the home team is set
        assertEquals("home", match.getHomeTeam());
        // Check that the away team is set
        assertEquals("away", match.getAwayTeam());
        // Check score is 0
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());
        // Check start time not null
        assertNotNull(match.getStartTime());
    }
}
