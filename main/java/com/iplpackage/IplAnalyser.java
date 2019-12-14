package com.iplpackage;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class IplAnalyser {

    public List<IPLBatsmenCSV> loadIplData(String csvFilePath) throws IPLBatsmenException {
        List<IPLBatsmenCSV> csvUsers=null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBean<IPLBatsmenCSV> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IPLBatsmenCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
             csvUsers= csvToBean.parse();
        } catch (RuntimeException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.HEADER_ISSUE, "HEADER PROBLEM");
        } catch (NoSuchFileException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.NO_SUCH_FILE, "NO SUCH FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvUsers;
    }
}
