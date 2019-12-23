package iplpackage;

import com.opencsv.bean.CsvBindByName;

public class IPLBatsmenCSV {

    @CsvBindByName(column = "POS")
    public String POS;

    @CsvBindByName(column = "PLAYER")
    public String playerName;

    @CsvBindByName(column = "Mat")
    public double noOfMatches;

    @CsvBindByName(column = "NO")
    public String NO;

    @CsvBindByName(column = "Runs")
    public String noOfRuns;

    @CsvBindByName(column = "HS")
    public String highestScore;

    @CsvBindByName(column = "Avg")
    public String average;

    @CsvBindByName(column = "BF")
    public String BF;

    @CsvBindByName(column = "SR")
    public String strikeRate;

    @CsvBindByName(column = "6s")
    public String sixes;

    @CsvBindByName(column = "4s")
    public String fours;

}
