package com.iplpackage;

import com.opencsv.bean.CsvBindByName;
public class IPLBatsmenCSV {

    @CsvBindByName(column = "POS")
    public String POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String playerName;

    @CsvBindByName(column = "Mat",required = true)
    public String noOfMatches;

    @CsvBindByName(column = "NO",required = true)
    public String NO;

    @CsvBindByName(column = "Runs",required = true)
    public String noOfRuns;

    @CsvBindByName(column = "HS",required = true)
    public String highestScore;

    @CsvBindByName(column = "Avg",required = true)
    public String Average;

    @CsvBindByName(column = "BF",required = true)
    public String BF;

    @CsvBindByName(column = "SR",required = true)
    public String strikeRate;

    @CsvBindByName(column = "6s",required = true)
    public String Sixes;

    @CsvBindByName(column = "4s",required = true)
    public String Fours;

}
