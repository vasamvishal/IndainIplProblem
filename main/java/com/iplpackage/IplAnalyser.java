package com.iplpackage;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import csvbuilderanalyser.CSVBuilderException;
import csvbuilderanalyser.CSVBuilderFactory;
import csvbuilderanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class IplAnalyser {

    public List<IPLBatsmenCSV> loadIplData(String csvFilePath) throws IPLBatsmenException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){

            ICSVBuilder<IPLBatsmenCSV> csvBuilder= CSVBuilderFactory.createCSVBuilder();
            List<IPLBatsmenCSV> csvFileList = csvBuilder.getCSVFileList(reader, IPLBatsmenCSV.class);
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
    }
}
