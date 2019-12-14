package com.iplpackage;

import com.google.gson.Gson;
import csvbuilderanalyser.CSVBuilderException;
import csvbuilderanalyser.CSVBuilderFactory;
import csvbuilderanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class IplAnalyser {
    List<IPLBatsmenCSV> batsmanList;
    public List<IPLBatsmenCSV> loadIplData(String csvFilePath) throws IPLBatsmenException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){

            ICSVBuilder<IPLBatsmenCSV> csvBuilder= CSVBuilderFactory.createCSVBuilder();
             batsmanList = csvBuilder.getCSVFileList(reader, IPLBatsmenCSV.class);
            return batsmanList;
        }
        catch (IOException e)
        {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.INPUT_FILE_EXCEPTION,"IO EXCEPTION");
        }
        catch (CSVBuilderException e)
        {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.CSV_BUILDER_EXCEPTION,"CSV BUILDER EXCEPTION");
        }
        catch (RuntimeException e)
        {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.HEADER_ISSUE,"HEADER ISSUUE");
        }
    }

    public String SortIplData(List<IPLBatsmenCSV> iplData) throws IPLBatsmenException {
        if(batsmanList == null)
        {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.LIST_IS_EMPTY,"LIST IS EMPTY");
        }
        batsmanList =iplData;
        Comparator<IPLBatsmenCSV> comparator=Comparator.comparing(batsmen->batsmen.Avg,Comparator.reverseOrder());
        batsmanList.sort(comparator);
        String sortedStateData = new Gson().toJson(batsmanList);
        return sortedStateData;
    }

    public String SortIplDataBasedOnStrikeRate(List<IPLBatsmenCSV> iplData) throws IPLBatsmenException {
        if(batsmanList==null)
        {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.LIST_IS_EMPTY,"LIST IS EMPTY");
        }
        batsmanList =iplData;
        Comparator<IPLBatsmenCSV> comparator=Comparator.comparing(batsmen->batsmen.Avg,Comparator.reverseOrder());
        batsmanList.sort(comparator);
        String sortedStateData = new Gson().toJson(batsmanList);
        return sortedStateData;
    }
}
