package iplpackage;

import csvbuilderanalyser.CSVBuilderException;
import csvbuilderanalyser.CSVBuilderFactory;
import csvbuilderanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmanAdaptor extends IPLAdapter {

    @Override
    public Map<String, IPLDAO> loadIplData(String[] csvFilePath) throws IPLBatsmenException {
        Map<String, IPLDAO> IPLMap = this.loadIplData(IPLBatsmenCSV.class, csvFilePath[0]);
        try {
            if (csvFilePath.length == 2)
                this.loadBatsmanMap(IPLMap, csvFilePath[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IPLBatsmenException("Array index out of bond", IPLBatsmenException.IPLException.NO_SUCH_FILE);
        }
        return IPLMap;
    }

    public Map<String, IPLDAO> loadBatsmanMap(Map<String, IPLDAO> censusStateMap, String indiaStateCodeFilePath)
            throws IPLBatsmenException {
        try (Reader reader = Files.newBufferedReader(Paths.get(indiaStateCodeFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBowler> censusCsvIterator = csvBuilder.
                    getCSVFileIterator(reader, IPLBowler.class);
            Iterable<IPLBowler> csvIterable = () -> censusCsvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusStateMap.get(csvState.playerName) != null)
                    .forEach(csvState -> {
                        censusStateMap.get(csvState.playerName).bowlingAverage = Double.parseDouble(csvState.bowlingAverage);
                        censusStateMap.get(csvState.playerName).noOfWickets = csvState.noOfWickets;
                    });
            return censusStateMap;
        } catch (IOException e) {
            throw new IPLBatsmenException("IO EXCEPTION", IPLBatsmenException.IPLException.INPUT_FILE_EXCEPTION);
        } catch (CSVBuilderException e) {
            throw new IPLBatsmenException("CSV BUILDER EXCEPTION", IPLBatsmenException.IPLException.CSV_BUILDER_EXCEPTION
            );
        } catch (RuntimeException e) {
            throw new IPLBatsmenException("HEADER ISSUUE", IPLBatsmenException.IPLException.HEADER_ISSUE);
        }
    }
}



