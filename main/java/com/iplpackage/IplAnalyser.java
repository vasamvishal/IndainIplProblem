package com.iplpackage;

import com.google.gson.Gson;
import csvbuilderanalyser.CSVBuilderException;
import csvbuilderanalyser.CSVBuilderFactory;
import csvbuilderanalyser.ICSVBuilder;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IplAnalyser {
    Map<String, IPLDAO> batsmanMap;

    public IplAnalyser() {
        this.batsmanMap = new HashMap<>();
    }

    public Map<String, IPLDAO> loadIplData(String csvFilePath) throws IPLBatsmenException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder<IPLBatsmenCSV> IPLBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBatsmenCSV> IPLIterator = IPLBuilder.getCSVFileIterator(reader, IPLBatsmenCSV.class);
            Iterable<IPLBatsmenCSV> IPLIterable = () ->IPLIterator;
            StreamSupport.stream(IPLIterable.spliterator(),false)
                    .map(IPLBatsmenCSV.class::cast)
                    .forEach(IPLMAP -> batsmanMap.put(IPLMAP.PLAYER, new IPLDAO(IPLMAP)));
        } catch (IOException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.INPUT_FILE_EXCEPTION, "IO EXCEPTION");
        } catch (CSVBuilderException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.CSV_BUILDER_EXCEPTION, "CSV BUILDER EXCEPTION");
        } catch (RuntimeException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.HEADER_ISSUE, "HEADER ISSUUE");
        }
            return batsmanMap;
    }

    public String sortIplData() {
        Comparator<IPLDAO> comparator = Comparator.comparing(batsmen -> batsmen.avg, Comparator.reverseOrder());
        ArrayList sortedList = batsmanMap.values().stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateData = new Gson().toJson(sortedList);
        return sortedStateData;
    }
}
