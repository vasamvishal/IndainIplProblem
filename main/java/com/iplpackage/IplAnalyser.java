package com.iplpackage;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class IplAnalyser {

    public int loadIplData(String csvFilePath) throws IPLBatsmenException {
        int count = 0;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBean<IPLBatsmenCSV> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IPLBatsmenCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IPLBatsmenCSV> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                IPLBatsmenCSV iplBatsmenCSV = csvUserIterator.next();
                count++;
            }
        } catch (NoSuchFileException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.NO_SUCH_FILE, "NO SUCH FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
