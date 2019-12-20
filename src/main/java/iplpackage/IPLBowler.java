package iplpackage;

import com.opencsv.bean.CsvBindByName;

public class IPLBowler {

    @CsvBindByName(column = "POS")
    public String POS;

    @CsvBindByName(column = "PLAYER")
    public String playerName;

    @CsvBindByName(column = "Mat")
    public String noOfMatches;

    @CsvBindByName(column = "Inns")
    public String inns;

    @CsvBindByName(column = "0v")
    public String overs;

    @CsvBindByName(column = "Runs")
    public String noOfRuns;

    @CsvBindByName(column = "Wkts")
    public String noOfWickets;

    @CsvBindByName(column = "Econ")
    public String economy;

    @CsvBindByName(column = "Avg")
    public String average;

    @CsvBindByName(column = "SR")
    public String strikeRate;

    @CsvBindByName(column = "4w")
    public String fourWicketHaul;

    @CsvBindByName(column = "5w")
    public String fiveWicketHaul;

    public IPLBowler() {
    }
}
