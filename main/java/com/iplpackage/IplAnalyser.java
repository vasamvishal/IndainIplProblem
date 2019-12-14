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
    public List<IPLBatsmenCSV> batsmanList;
    Map<String, IPLDAO> batsmanMap;

    public Map<String, IPLDAO> loadIplData(String csvFilePath) throws IPLBatsmenException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder<IPLBatsmenCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
            batsmanList = csvBuilder.getCSVFileList(reader, IPLBatsmenCSV.class);
            StreamSupport.stream(() -> batsmanList.spliterator(), Spliterator.ORDERED, false)
                    .map(IPLBatsmenCSV.class::cast)
                    .forEach(IPLMAP -> batsmanMap.put(IPLMAP.Runs, new IPLDAO(IPLMAP)));
        } catch (IOException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.INPUT_FILE_EXCEPTION, "IO EXCEPTION");
        } catch (CSVBuilderException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.CSV_BUILDER_EXCEPTION, "CSV BUILDER EXCEPTION");
        } catch (RuntimeException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.HEADER_ISSUE, "HEADER ISSUUE");
        }
        return batsmanMap;
    }

    public String SortIplData() {

        Comparator<IPLBatsmenCSV> comparator = Comparator.comparing(batsmen -> batsmen.Avg, Comparator.reverseOrder());
        ArrayList sortedList = batsmanList.stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateData = new Gson().toJson(sortedList);
        return sortedStateData;
    }
}
