package scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void shouldNotAllowedToAddTeamsWithSameName() {
        //given
        //when
        scoreboard.addNewMatch("TeamOne", "TeamOne");

        //then
        assertEquals(0, scoreboard.getMatches().size());

    }

    @Test
    public void shouldUpdateScoresOfMatch() {
        //given
        String homeTeam = "home";
        String awayTeam = "away";
        Match match = new Match(homeTeam, awayTeam);
        scoreboard.getMatches().add(match);

        //when
        scoreboard.updateScore(homeTeam, awayTeam, 1, 2);

        //then
        assertEquals(1, scoreboard.getMatches().getFirst().getHomeScore());
        assertEquals(2, scoreboard.getMatches().getFirst().getAwayScore());
    }

    @Test
    public void shouldNotUpdateWithNegativeScore() {
        //given
        String homeTeam = "home";
        String awayTeam = "away";
        Match match = new Match(homeTeam, awayTeam);
        scoreboard.getMatches().add(match);

        //when
        scoreboard.updateScore(homeTeam, awayTeam, -1, -2);

        //then
        assertEquals(0, scoreboard.getMatches().getFirst().getHomeScore());
        assertEquals(0, scoreboard.getMatches().getFirst().getAwayScore());
    }

    @Test
    public void shouldNotUpdateWithOnlyOneNegativeScore() {
        //given
        String homeTeam = "home";
        String awayTeam = "away";
        Match match = new Match(homeTeam, awayTeam);
        scoreboard.getMatches().add(match);

        //when
        scoreboard.updateScore(homeTeam, awayTeam, 2, -1);

        //then
        assertEquals(0, scoreboard.getMatches().getFirst().getHomeScore());
        assertEquals(0, scoreboard.getMatches().getFirst().getAwayScore());
    }

    @Test
    public void shouldDeleteMatchFromScoreboardWhenFinishes() {
        //given
        String homeTeam = "home";
        String awayTeam = "away";
        Match match = new Match(homeTeam, awayTeam);
        scoreboard.getMatches().add(match);

        //when
        scoreboard.finishMatch(homeTeam, awayTeam);

        //then
        assertEquals(0, scoreboard.getMatches().size());
    }

    @Test
    public void shouldNotDeleteMatchFromScoreboardWhenDoesNotExist() {
        //given
        Match match = new Match("home", "away");
        scoreboard.getMatches().add(match);

        //when
        scoreboard.finishMatch("test1", "test2");

        //then
        assertEquals(1, scoreboard.getMatches().size());
        assertTrue(scoreboard.getMatches().contains(match));
    }

    @Test
    public void shouldDeleteOnlySelectedMatchFromScoreboardWhenFinishes() {
        //given
        Match match1 = new Match("home1", "away1");
        Match match2 = new Match("home2", "away2");
        Match match3 = new Match("home3", "away3");
        scoreboard.getMatches().add(match1);
        scoreboard.getMatches().add(match2);
        scoreboard.getMatches().add(match3);

        //when
        scoreboard.finishMatch(match2.getHomeTeam(), match2.getAwayTeam());

        //then
        assertFalse(scoreboard.getMatches().contains(match2));
    }

    @Test
    public void shouldReturnSummaryInDescendingOrder() {
        //given
        Match match1 = new Match("home1", "away1", 1, 0);
        Match match2 = new Match("home2", "away2", 0, 1);
        Match match3 = new Match("home3", "away3", 3,0);
        Match match4 = new Match("home4", "away4", 2,2);
        scoreboard.getMatches().add(match1);
        scoreboard.getMatches().add(match2);
        scoreboard.getMatches().add(match3);
        scoreboard.getMatches().add(match4);

        List<Match> expected = new ArrayList<>();
        expected.add(match4);
        expected.add(match3);
        expected.add(match2);
        expected.add(match1);

        //when
        List<Match> result = scoreboard.getSummary();

        //then
        assertIterableEquals(expected, result);

    }

    @Test
    public void shouldReturnSummaryInCorrectOrder() {
        //given
        Match match1 = new Match("Mexico", "Canada", 0, 5);
        Match match2 = new Match("Spain", "Brazil", 10, 2);
        Match match3 = new Match("Germany", "France", 2,2);
        Match match4 = new Match("Uruguay", "Italy", 6,6);
        Match match5 = new Match("Argentina", "Australia", 3,1);
        scoreboard.getMatches().add(match1);
        scoreboard.getMatches().add(match2);
        scoreboard.getMatches().add(match3);
        scoreboard.getMatches().add(match4);
        scoreboard.getMatches().add(match5);

        List<Match> expected = new ArrayList<>();
        expected.add(match4);
        expected.add(match2);
        expected.add(match1);
        expected.add(match5);
        expected.add(match3);

        //when
        List<Match> result = scoreboard.getSummary();

        //then
        assertIterableEquals(expected, result);

    }

}
