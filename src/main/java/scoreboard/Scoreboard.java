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

    public void finishMatch(String homeTeam, String awayTeam) {
        Match matchToRemove = new Match(homeTeam, awayTeam);
        matches.remove(matchToRemove);
    }

    public List<Match> getSummary() {
        List<Match> summary = new ArrayList<>(matches);
        summary.sort((m1, m2) -> {
            int scoreCompare = Integer.compare(m2.getScoreSum(), m1.getScoreSum());
            if (scoreCompare != 0) {
                return scoreCompare;
            }
            return matches.indexOf(m2) - matches.indexOf(m1);
        });
        return summary;
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