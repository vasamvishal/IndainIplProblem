
public class IPLDAO {
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
}
