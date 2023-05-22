import java.time.LocalDateTime;

// Create a match class with home team, away team, home team score, away team score
public class Match {
  // final home team
  private final String homeTeam;
  // final away team
  private final String awayTeam;
  private final LocalDateTime startTime;
  // home team score
  private int homeTeamScore;
  // away team score
  private int awayTeamScore;

  public Match(String homeTeam, String awayTeam) {
    // set home and away team
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeTeamScore = 0;
    this.awayTeamScore = 0;
    // init start time with now
    startTime = LocalDateTime.now();
  }

  // getter for homeTeam
  public String getHomeTeam() {
    return homeTeam;
  }

  // getter for awayTeam
  public String getAwayTeam() {
    return awayTeam;
  }

  // getter for homeTeamScore
  public int getHomeTeamScore() {
    return homeTeamScore;
  }

  // setter for homeTeamScore
  public void setHomeTeamScore(int homeTeamScore) {
    this.homeTeamScore = homeTeamScore;
  }

  // getter for awayTeamScore
  public int getAwayTeamScore() {
    return awayTeamScore;
  }

  // setter for awayTeamScore
  public void setAwayTeamScore(int awayTeamScore) {
    this.awayTeamScore = awayTeamScore;
  }

  // getter for startTime
  public LocalDateTime getStartTime() {
    return startTime;
  }
}
