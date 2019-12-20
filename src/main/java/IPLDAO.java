
public class IPLDAO {
    public String position;
    public String playerName;
    public String noOfMatches;
    public String NO;
    public int noOfRuns;
    public String highestScore;
    public Double Average;
    public String BF;
    public String strikeRate;
    public String Fours;
    public String Sixes;

    public IPLDAO() {
    }

    public IPLDAO(IPLBatsmenCSV iplmap) {
        this.position=iplmap.POS;
        this.playerName=iplmap.playerName;
        this.noOfMatches=iplmap.noOfMatches;
        this.NO=iplmap.NO;
        this.noOfRuns=Integer.parseInt(iplmap.noOfRuns);
        this.highestScore=iplmap.highestScore;
        this.Average=iplmap.Average.contains("-")?0:Double.valueOf(iplmap.Average);
        this.BF=iplmap.BF;
        this.strikeRate=iplmap.strikeRate;
        this.Fours=iplmap.Fours;
        this.Sixes=iplmap.Sixes;
    }
}
