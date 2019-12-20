package iplpackage;

public class IPLDAO {
    public int fiveWicketHaul;
    public String economy;
    public String noOfWickets;
    public int fourWicketHaul;
    public String inns;
    public String overs;
    public String position;
    public String playerName;
    public String noOfMatches;
    public String NO;
    public int noOfRuns;
    public String highestScore;
    public Double average;
    public String BF;
    public Double strikeRate;
    public String fours;
    public String sixes;

    public IPLDAO() {
    }

    public IPLDAO(IPLBatsmenCSV iplmap) {
        this.position=iplmap.POS;
        this.playerName=iplmap.playerName;
        this.noOfMatches=iplmap.noOfMatches;
        this.NO=iplmap.NO;
        this.noOfRuns=Integer.parseInt(iplmap.noOfRuns);
        this.highestScore=iplmap.highestScore;
        this.average =iplmap.average.contains("-")?0:Double.valueOf(iplmap.average);
        this.BF=iplmap.BF;
        this.strikeRate=Double.valueOf(iplmap.strikeRate);
        this.fours =iplmap.fours;
        this.sixes =iplmap.sixes;
    }

    public IPLDAO(IPLBowler iplmap) {
        this.position=iplmap.POS;
        this.playerName=iplmap.playerName;
        this.noOfMatches=iplmap.noOfMatches;
        this.noOfRuns=Integer.parseInt(iplmap.noOfRuns);
        this.overs=iplmap.overs;
        this.average =iplmap.average.contains("-")?0:Double.parseDouble(iplmap.average);
        this.inns=iplmap.inns;
        this.noOfWickets=iplmap.noOfWickets;
        this.economy=iplmap.economy;
        this.fourWicketHaul=Integer.parseInt(iplmap.fourWicketHaul);
        this.fiveWicketHaul=Integer.parseInt(iplmap.fiveWicketHaul);
        this.strikeRate=Double.valueOf(iplmap.strikeRate);
    }
}
