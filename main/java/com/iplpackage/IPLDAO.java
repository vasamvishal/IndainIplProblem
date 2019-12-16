package com.iplpackage;

public class IPLDAO {
    public String POS;
    public String PLAYER;
    public String Mat;
    public String NO;
    public String Runs;
    public String HS;
    public Double avg;
    public String BF;
    public String SR;


    public IPLDAO(IPLBatsmenCSV iplmap) {
        this.POS=iplmap.POS;
        this.PLAYER=iplmap.PLAYER;
        this.Mat=iplmap.Mat;
        this.NO=iplmap.NO;
        this.Runs=iplmap.Runs;
        this.HS=iplmap.HS;
        this.avg=iplmap.Avg.contains("-")?0:Double.valueOf(iplmap.Avg);
        this.BF=iplmap.BF;
        this.SR=iplmap.SR;
    }
}
