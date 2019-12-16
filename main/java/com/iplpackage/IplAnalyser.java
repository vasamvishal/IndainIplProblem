package com.iplpackage;

import com.google.gson.Gson;
import csvbuilderanalyser.CSVBuilderException;
import csvbuilderanalyser.CSVBuilderFactory;
import csvbuilderanalyser.ICSVBuilder;


import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IplAnalyser {
    Map<String, IPLDAO> batsmanMap;
    Map<SortingTypes,Comparator<IPLDAO>> sortingMap;
    public IplAnalyser() {
        this.batsmanMap = new HashMap<>();
        this.sortingMap=new HashMap<>();
        this.sortingMap.put(SortingTypes.Average, Comparator.comparing(sortType -> sortType.avg,Comparator.reverseOrder()));
        this.sortingMap.put(SortingTypes.NO_OF_SIXES_AND_FOURS,new SortOnMultipleTypes());
    }

    public Map<String, IPLDAO> loadIplData(String csvFilePath) throws IPLBatsmenException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder<IPLBatsmenCSV> IPLBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBatsmenCSV> IPLIterator = IPLBuilder.getCSVFileIterator(reader, IPLBatsmenCSV.class);
            Iterable<IPLBatsmenCSV> IPLIterable = () -> IPLIterator;
            StreamSupport.stream(IPLIterable.spliterator(), false)
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

    public String sortIplData(SortingTypes sortType) {
        ArrayList sortedList = batsmanMap.values().stream()
                .sorted(this.sortingMap.get(sortType))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateData = new Gson().toJson(sortedList);
        return sortedStateData;
    }
}
