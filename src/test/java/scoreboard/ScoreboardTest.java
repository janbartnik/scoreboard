package scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardTest {

    @Test
    public void shouldAddMatchWithZeroScoreToScoreboard() {
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        scoreboard.addNewMatch("TeamHome", "TeamAway");

        //then

        assertEquals(1, scoreboard.getMatches().size());
        assertEquals(0, scoreboard.getMatches().getFirst().getHomeScore());
        assertEquals(0, scoreboard.getMatches().getFirst().getAwayScore());
    }
}
