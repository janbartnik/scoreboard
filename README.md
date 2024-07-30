Notes:
addNewMatch method in Scoreboard return true, when collection was changed, it could be
done the same for updateMatch and finishMatch.

GetSummary could cache summary, so when it is called again before any changes are made,
no sorting will have to be done.

I used LinkedList as there is a lot of removal (especially as matches have close to 
same duration) and addition.