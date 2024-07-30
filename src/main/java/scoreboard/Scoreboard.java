package scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final List<Match> matches;

    public Scoreboard() {
        matches = new ArrayList<>();
    }

    public void addNewMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }

    List<Match> getMatches() {
        return matches;
    }
}