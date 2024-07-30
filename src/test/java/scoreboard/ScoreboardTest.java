package scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardTest {

    private Scoreboard scoreboard;
    @BeforeEach
    public void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    public void shouldAddMatchWithZeroScoreToScoreboard() {
        //given
        //when
        scoreboard.addNewMatch("TeamHome", "TeamAway");

        //then
        assertEquals(1, scoreboard.getMatches().size());
        assertEquals(0, scoreboard.getMatches().getFirst().getHomeScore());
        assertEquals(0, scoreboard.getMatches().getFirst().getAwayScore());
    }

    @Test
    public void shouldNotAddMatchWithExistingTeamsToScoreboard() {
        //given
        scoreboard.addNewMatch("TeamOne", "TeamTwo");

        //when
        scoreboard.addNewMatch("TeamOne", "TeamTwo");
        scoreboard.addNewMatch("TeamTwo", "TeamOne");
        scoreboard.addNewMatch("TeamOne", "TeamThree");
        scoreboard.addNewMatch("TeamTwo", "TeamFour");


        //then
        assertEquals(1, scoreboard.getMatches().size());

    }
}
