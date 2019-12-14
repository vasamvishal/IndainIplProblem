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
    List<IPLBatsmenCSV> csvFileList;
    public List<IPLBatsmenCSV> loadIplData(String csvFilePath) throws IPLBatsmenException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){

            ICSVBuilder<IPLBatsmenCSV> csvBuilder= CSVBuilderFactory.createCSVBuilder();
             csvFileList = csvBuilder.getCSVFileList(reader, IPLBatsmenCSV.class);
            return csvFileList;
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

    public String SortIplData(List<IPLBatsmenCSV> iplBatsmanTestFile) {
        csvFileList=iplBatsmanTestFile;
        Comparator<IPLBatsmenCSV> comparator=Comparator.comparing(batsmen->batsmen.Avg,Comparator.reverseOrder());
        csvFileList.sort(comparator);
        String sortedStateData = new Gson().toJson(csvFileList);
        return sortedStateData;
    }
}
