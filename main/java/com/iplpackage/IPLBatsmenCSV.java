package com.iplpackage;

import com.opencsv.bean.CsvBindByName;
public class IPLBatsmenCSV {

    @CsvBindByName(column = "POS")
    public int  POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String PLAYER;

    @CsvBindByName(column = "Mat",required = true)
    public int Mat;

    @CsvBindByName(column = "NO",required = true)
    public int NO;

    @CsvBindByName(column = "Runs",required = true)
    public int Runs;

    @CsvBindByName(column = "Avg",required = true)
    public float Avg;

    @CsvBindByName(column = "BF",required = true)
    public int BF;

    @CsvBindByName(column = "SR",required = true)
    public float SR;

}
