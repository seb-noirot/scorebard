import java.util.List;

public class Scoreboard {

  private final List<Match> matches;

  // Create SOLID constructor with list of matches
  public Scoreboard(List<Match> matches) {
    this.matches = matches;
  }

  public void startNewMatch(String homeTeam, String awayTeam) {
    // check if homeTeam is null or empty
    if (homeTeam == null || homeTeam.isEmpty()) {
      throw new IllegalArgumentException("Home team is null or empty");
    }
    // check if awayTeam is null or empty
    if (awayTeam == null || awayTeam.isEmpty()) {
      throw new IllegalArgumentException("Away team is null or empty");
    }
    // check if match already exists
    if (matches.stream()
        .anyMatch(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))) {
      throw new IllegalArgumentException(
          "Match with home team " + homeTeam + " and away team " + awayTeam + " already exists");
    }
    // create new match
    Match match = new Match(homeTeam, awayTeam);
    // add match to list of matches
    matches.add(match);
  }

  public void updateScore(String homeTeam, int homeTeamScore, String awayTeam, int awayTeamScore) {
    // Check parameters
    if (homeTeamScore < 0) {
      throw new IllegalArgumentException("Home team score is negative");
    }
    if (awayTeamScore < 0) {
      throw new IllegalArgumentException("Away team score is negative");
    }

    // Find match by team names
    Match match =
        matches.stream()
            .filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))
            .findFirst()
            .orElse(null);

    // Check if match exists
    if (match == null) {
      throw new IllegalArgumentException(
          "Match with home team score "
              + homeTeamScore
              + " and away team score "
              + awayTeamScore
              + " does not exist");
    }

    // Update score
    match.setHomeTeamScore(homeTeamScore);
    match.setAwayTeamScore(awayTeamScore);
  }

  // 3. Finish match currently in progress. This removes a match from the scoreboard.
  public void finishMatch(String homeTeam, String awayTeam) {
    // Find match
    Match match =
        matches.stream()
            .filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))
            .findFirst()
            .orElse(null);

    // Check if match exists
    if (match == null) {
      throw new IllegalArgumentException(
          "Match with home team " + homeTeam + " and away team " + awayTeam + " does not exist");
    }

    // Remove match
    matches.remove(match);
  }

  public List<Match> getSummaryOfMatches() {
    // Sort matches by total score and start time to a new list
    return this.matches.stream()
        .sorted(
            (m1, m2) -> {
              // Calculate total score
              int totalScore1 = m1.getHomeTeamScore() + m1.getAwayTeamScore();
              int totalScore2 = m2.getHomeTeamScore() + m2.getAwayTeamScore();

              // Compare total scores
              int compareTotalScores = Integer.compare(totalScore2, totalScore1);
              if (compareTotalScores != 0) {
                return compareTotalScores;
              }

              // Compare start times
              return m2.getStartTime().compareTo(m1.getStartTime());
            })
        .toList();
  }
}
