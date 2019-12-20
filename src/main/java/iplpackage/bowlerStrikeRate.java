package iplpackage;

public class bowlerStrikeRate implements java.util.Comparator<IPLDAO> {
    @Override
    public int compare(IPLDAO bowler1, IPLDAO bowler2) {
        int runs = (bowler1.fourWicketHaul + bowler1.fiveWicketHaul) - (bowler2.fourWicketHaul + bowler2.fiveWicketHaul);
        return runs;
    }
}
