package iplpackage;

import com.opencsv.bean.CsvBindByName;

public class IPLBowler {

    @CsvBindByName(column = "POS")
    public String POS;

    @CsvBindByName(column = "PLAYER")
    public String playerName;

    @CsvBindByName(column = "Mat")
    public double noOfMatches;

    @CsvBindByName(column = "Inns")
    public String inns;

    @CsvBindByName(column = "Ov")
    public String overs;

    @CsvBindByName(column = "Runs")
    public String noOfRuns;

    @CsvBindByName(column = "Wkts")
    public double noOfWickets;

    @CsvBindByName(column = "BBI")
    public String BBI;

    @CsvBindByName(column = "Avg")
    public String bowlingAverage;

    @CsvBindByName(column = "Econ")
    public String economy;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    @CsvBindByName(column = "4w")
    public String fourWicketHaul;

    @CsvBindByName(column = "5w")
    public String fiveWicketHaul;

    public IPLBowler() {
    }
}
