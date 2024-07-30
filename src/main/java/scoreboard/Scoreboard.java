package scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final List<Match> matches;

    public Scoreboard() {
        matches = new ArrayList<>();
    }

    /**
     *
     * @param homeTeam home team name
     * @param awayTeam away team name
     * @return true when match was added to scoreboard
     */
    public boolean addNewMatch(String homeTeam, String awayTeam) {
        if (isPlaying(homeTeam) || isPlaying(awayTeam) || homeTeam.equals(awayTeam)) {
            return false;
        }
        return matches.add(new Match(homeTeam, awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            return;
        }
        Match matchToFind = new Match(homeTeam, awayTeam);
        for (Match match : matches) {
            if (match.equals(matchToFind)) {
                match.setHomeScore(homeScore);
                match.setAwayScore(awayScore);
            }
        }
    }

    List<Match> getMatches() {
        return matches;
    }

    private boolean isPlaying(String team) {
        for (Match match : matches) {
            if (match.getHomeTeam().equals(team) || match.getAwayTeam().equals(team)) {
                return true;
            }
        }
        return false;
    }
}