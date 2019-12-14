package com.iplpackage;

import com.opencsv.bean.CsvBindByName;
public class IPLBatsmenCSV {

    @CsvBindByName(column = "POS")
    public String POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String PLAYER;

    @CsvBindByName(column = "Mat",required = true)
    public String Mat;

    @CsvBindByName(column = "NO",required = true)
    public String NO;

    @CsvBindByName(column = "Runs",required = true)
    public String Runs;

    @CsvBindByName(column = "HS",required = true)
    public String HS;

    @CsvBindByName(column = "Avg",required = true)
    public String Avg;

    @CsvBindByName(column = "BF",required = true)
    public String BF;

    @CsvBindByName(column = "SR",required = true)
    public String SR;

}
