package iplpackage;

import csvbuilderanalyser.CSVBuilderException;
import csvbuilderanalyser.CSVBuilderFactory;
import csvbuilderanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {
    private Map<String, IPLDAO> bowlingRecordMap;
    private Map<String, IPLDAO> battingRecordMap;

    public IPLAdapter() {
        this.battingRecordMap = new HashMap<>();
        this.bowlingRecordMap = new HashMap<>();
    }

    public abstract Map<String, IPLDAO> loadIplData(String csvFilePath) throws IPLBatsmenException;

    public <E> Map<String, IPLDAO> loadIplData(Class eClass, String csvFilePath) throws IPLBatsmenException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder IPLBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> IPLIterator = IPLBuilder.getCSVFileIterator(reader, eClass);
            Iterable<E> IPLIterable = () -> IPLIterator;
            System.out.println(eClass.getName());
            if (eClass.getName().equals("iplpackage.IPLBatsmenCSV")) {
                StreamSupport.stream(IPLIterable.spliterator(), false)
                        .map(IPLBatsmenCSV.class::cast)
                        .forEach(IPLMAP -> battingRecordMap.put(IPLMAP.playerName, new IPLDAO(IPLMAP)));
                        return battingRecordMap;
            } else if (eClass.getName().equals("iplpackage.IPLBowler")) {
                StreamSupport.stream(IPLIterable.spliterator(), false)
                        .map(IPLBowler.class::cast)
                        .forEach(IPLMAP -> bowlingRecordMap.put(IPLMAP.playerName, new IPLDAO(IPLMAP)));
                        return bowlingRecordMap;
            }
        } catch (IOException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.INPUT_FILE_EXCEPTION, "IO EXCEPTION");
        } catch (CSVBuilderException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.CSV_BUILDER_EXCEPTION,
                    "CSV BUILDER EXCEPTION");
        } catch (RuntimeException e) {
            throw new IPLBatsmenException(IPLBatsmenException.IPLException.HEADER_ISSUE, "HEADER ISSUUE");
        }

        return null;
    }
}



