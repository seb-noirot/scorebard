import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ScoreboardTest {

  @Test
  void startNewMatch() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home", "away");
    // Check that the match was added to the list of matches
    assertEquals(1, scoreboard.getSummaryOfMatches().size());
    // Check that the home team is set
    var match = scoreboard.getSummaryOfMatches().get(0);
    assertEquals("home", match.getHomeTeam());
    // Check that the away team is set
    assertEquals("away", match.getAwayTeam());
  }

  @Test
  void startNewMatchInvalid() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    assertThrows(IllegalArgumentException.class, () -> scoreboard.startNewMatch(null, "away"));
    assertThrows(IllegalArgumentException.class, () -> scoreboard.startNewMatch("home", null));
    assertThrows(IllegalArgumentException.class, () -> scoreboard.startNewMatch(null, null));
    // Check empty team name
    assertThrows(IllegalArgumentException.class, () -> scoreboard.startNewMatch("", "away"));
    assertThrows(IllegalArgumentException.class, () -> scoreboard.startNewMatch("home", ""));
    assertThrows(IllegalArgumentException.class, () -> scoreboard.startNewMatch("", ""));
    // check that the match was not added to the list of matches
    assertEquals(0, scoreboard.getSummaryOfMatches().size());
  }

  @Test
  void startNewMatchAlreadyExist() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home", "away");
    // Check that the match was added to the list of matches
    assertEquals(1, scoreboard.getSummaryOfMatches().size());
    // Start the same match raise an exception
    assertThrows(IllegalArgumentException.class, () -> scoreboard.startNewMatch("home", "away"));
  }

  @Test
  void updateScore() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home", "away");
    // Update score
    scoreboard.updateScore("home", 1, "away", 2);
    // Check that the score was updated
    var match = scoreboard.getSummaryOfMatches().get(0);
    assertEquals(1, match.getHomeTeamScore());
    assertEquals(2, match.getAwayTeamScore());
  }

  @Test
  void updateScoreMatchNotExist() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Update score
    assertThrows(
        IllegalArgumentException.class, () -> scoreboard.updateScore("home", 1, "away", 2));
  }

  @Test
  void updateScoreNegativeHomeScore() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home", "away");
    // Update score
    assertThrows(
        IllegalArgumentException.class, () -> scoreboard.updateScore("home", -1, "away", 2));
  }

  @Test
  void updateScoreNegativeAwayScore() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home", "away");
    // Update score
    assertThrows(
        IllegalArgumentException.class, () -> scoreboard.updateScore("home", 1, "away", -2));
  }

  @Test
  void finishMatch() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home", "away");
    // Finish match
    scoreboard.finishMatch("home", "away");
    // Check that the match was removed from the list of matches
    assertEquals(0, scoreboard.getSummaryOfMatches().size());
  }

  @Test
  void finishMatchMatchNotExist() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Finish match
    assertThrows(IllegalArgumentException.class, () -> scoreboard.finishMatch("home", "away"));
  }

  @Test
  void getSummaryOfMatchesInProgressOrderedByTotalScore() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home", "away");
    // Update score
    scoreboard.updateScore("home", 1, "away", 2);
    // Check that the match was added to the list of matches
    assertEquals(1, scoreboard.getSummaryOfMatches().size());
    // Check that the home team is set
    var match = scoreboard.getSummaryOfMatches().get(0);
    assertEquals("home", match.getHomeTeam());
    // Check that the away team is set
    assertEquals("away", match.getAwayTeam());
    // Check that the score was updated
    assertEquals(1, match.getHomeTeamScore());
    assertEquals(2, match.getAwayTeamScore());
  }

  @Test
  void getSummaryOfMatchesInProgressOrderedByTotalScoreEmpty() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Check that the match was added to the list of matches
    assertEquals(0, scoreboard.getSummaryOfMatches().size());
  }

  @Test
  void getSummaryOfMatchesInProgressOrderedByTotalScoreSeveral() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home1", "away1");
    // Update score
    scoreboard.updateScore("home1", 1, "away1", 2);
    // Start a new match
    scoreboard.startNewMatch("home2", "away2");
    // Update score
    scoreboard.updateScore("home2", 2, "away2", 3);
    // Start a new match
    scoreboard.startNewMatch("home3", "away3");
    // Update score
    scoreboard.updateScore("home3", 3, "away3", 4);
    // Check that the match was added to the list of matches
    assertEquals(3, scoreboard.getSummaryOfMatches().size());
    // Check that the home team is set
    var match = scoreboard.getSummaryOfMatches().get(0);
    assertEquals("home3", match.getHomeTeam());
    // Check that the away team is set
    assertEquals("away3", match.getAwayTeam());
  }

  @Test
  void getSummaryOfMatchesWithSameScoreSortedByStartTimeDesc() {
    // Create a scoreboard
    Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
    // Start a new match
    scoreboard.startNewMatch("home1", "away1");
    // Update score
    scoreboard.updateScore("home1", 1, "away1", 2);
    // Start a new match
    scoreboard.startNewMatch("home2", "away2");
    // Update score
    scoreboard.updateScore("home2", 1, "away2", 2);
    // Start a new match
    scoreboard.startNewMatch("home3", "away3");
    // Update score
    scoreboard.updateScore("home3", 1, "away3", 2);
    // Check that the match was added to the list of matches
    assertEquals(3, scoreboard.getSummaryOfMatches().size());
    // Check that the home team is set
    var match = scoreboard.getSummaryOfMatches().get(0);
    assertEquals("home3", match.getHomeTeam());
    // Check that the away team is set
    assertEquals("away3", match.getAwayTeam());
  }

  //For example, if following matches are started in the specified order and their scores
    //respectively updated:
    //a. Mexico 0 - Canada 5
    //b. Spain 10 - Brazil 2
    //c. Germany 2 - France 2
    //d. Uruguay 6 - Italy 6
    //e. Argentina 3 - Australia 1
    //
    //The summary should be as follows:
    //1. Uruguay 6 - Italy 6
    //2. Spain 10 - Brazil 2
    //3. Mexico 0 - Canada 5
    //4. Argentina 3 - Australia 1
    //5. Germany 2 - France 2
    @Test
    void testExample() {
      // Create a scoreboard
        Scoreboard scoreboard = new Scoreboard(new ArrayList<>());
        // Start a new match
        scoreboard.startNewMatch("Mexico", "Canada");
        // Update score
        scoreboard.updateScore("Mexico", 0, "Canada", 5);
        // Start a new match
        scoreboard.startNewMatch("Spain", "Brazil");
        // Update score
        scoreboard.updateScore("Spain", 10, "Brazil", 2);
        // Start a new match
        scoreboard.startNewMatch("Germany", "France");
        // Update score
        scoreboard.updateScore("Germany", 2, "France", 2);
        // Start a new match
        scoreboard.startNewMatch("Uruguay", "Italy");
        // Update score
        scoreboard.updateScore("Uruguay", 6, "Italy", 6);
        // Start a new match
        scoreboard.startNewMatch("Argentina", "Australia");
        // Update score
        scoreboard.updateScore("Argentina", 3, "Australia", 1);
        // Check that the match was added to the list of matches
        assertEquals(5, scoreboard.getSummaryOfMatches().size());
        // Check that the home team is set
        var match = scoreboard.getSummaryOfMatches().get(0);
        assertEquals("Uruguay", match.getHomeTeam());
        // Check that the away team is set
        assertEquals("Italy", match.getAwayTeam());
        // Check that the home team is set
        match = scoreboard.getSummaryOfMatches().get(1);
        assertEquals("Spain", match.getHomeTeam());
        // Check that the away team is set
        assertEquals("Brazil", match.getAwayTeam());
        // Check that the home team is set
        match = scoreboard.getSummaryOfMatches().get(2);
        assertEquals("Mexico", match.getHomeTeam());
        // Check that the away team is set
        assertEquals("Canada", match.getAwayTeam());
        // Check that the home team is set
        match = scoreboard.getSummaryOfMatches().get(3);
        assertEquals("Argentina", match.getHomeTeam());
        // Check that the away team is set
        assertEquals("Australia", match.getAwayTeam());
        // Check that the home team is set
        match = scoreboard.getSummaryOfMatches().get(4);
        assertEquals("Germany", match.getHomeTeam());
        // Check that the away team is set
        assertEquals("France", match.getAwayTeam());

    }
}
