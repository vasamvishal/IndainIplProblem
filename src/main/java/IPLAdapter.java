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
    private  Map<String,IPLDAO> battingRecordMap;

    public IPLAdapter() {
        this.battingRecordMap = new HashMap<>();
    }

    public abstract Map<String, IPLDAO> loadIplData(String csvFilePath) throws IPLBatsmenException;

    public Map<String, IPLDAO> loadIplData(Class eClass, String csvFilePath) throws IPLBatsmenException{
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                ICSVBuilder<IPLBatsmenCSV> IPLBuilder = CSVBuilderFactory.createCSVBuilder();
                Iterator<IPLBatsmenCSV> IPLIterator = IPLBuilder.getCSVFileIterator(reader, IPLBatsmenCSV.class);
                Iterable<IPLBatsmenCSV> IPLIterable = () -> IPLIterator;
                System.out.println(eClass.getName());

                    StreamSupport.stream(IPLIterable.spliterator(), false)
                            .map(IPLBatsmenCSV.class::cast)
                            .forEach(IPLMAP -> battingRecordMap.put(IPLMAP.playerName, new IPLDAO(IPLMAP)));

            } catch (IOException e) {
                throw new IPLBatsmenException(IPLBatsmenException.IPLException.INPUT_FILE_EXCEPTION, "IO EXCEPTION");
            } catch (CSVBuilderException e) {
                throw new IPLBatsmenException(IPLBatsmenException.IPLException.CSV_BUILDER_EXCEPTION,
                        "CSV BUILDER EXCEPTION");
            } catch (RuntimeException e) {
                throw new IPLBatsmenException(IPLBatsmenException.IPLException.HEADER_ISSUE, "HEADER ISSUUE");
            }
            return battingRecordMap;
        }
    }



