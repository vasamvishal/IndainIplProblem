package iplpackage;

public class IPLDAO {
    public int battingNoOfRuns;
    public double bowlingStrikeRate;
    public String BBI;
    public int fiveWicketHaul;
    public double economy;
    public double noOfWickets;
    public int fourWicketHaul;
    public String inns;
    public String overs;
    public String position;
    public String playerName;
    public double noOfMatches;
    public String NO;
    public int noOfRuns;
    public String highestScore;
    public Double battingAverage;
    public Double bowlingAverage;
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
        this.battingNoOfRuns=Integer.parseInt(iplmap.noOfRuns);
        this.highestScore=iplmap.highestScore;
        this.battingAverage =iplmap.average.contains("-")?0:Double.parseDouble(iplmap.average);
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
        this.bowlingAverage =iplmap.bowlingAverage.contains("-")?0:Double.parseDouble(iplmap.bowlingAverage);
        this.inns=iplmap.inns;
        this.noOfWickets=iplmap.noOfWickets;
        this.BBI=iplmap.BBI;
        this.economy=Double.parseDouble(iplmap.economy);
        this.fourWicketHaul=Integer.parseInt(iplmap.fourWicketHaul);
        this.fiveWicketHaul=Integer.parseInt(iplmap.fiveWicketHaul);
        this.bowlingStrikeRate=iplmap.strikeRate;
}
}
