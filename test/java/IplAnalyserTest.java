//Welcome to ipl problem


import com.google.gson.Gson;
import com.iplpackage.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.Map;

public class IplAnalyserTest {
    private static String IPL_BATSMAN_DATA = "/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String WRONG_BATSMAN_IPL_LOAD_DATA = "/home/user/IPlProblem/IPL/Problem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String BOWLER_IPL_LOAD_DATA = "/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static String IPL_BATSMAN_DELIMITER_DATA = "/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetDelimiterTypeMostRuns.csv";
    private static String IPL_BATSMAN_TEST_FILE = "/home/user/IPlProblem/IPLProblem/src/test/resources/ipl2019FactsheetTestFile.csv";

    @Test
    public void givenIPLCsvFile_ShouldReturn_ExactCount() {
        IplAnalyser iplAnalyser = new IplAnalyser();
        try {
            Map<String, IPLDAO> iplData = iplAnalyser.loadIplData(IPL_BATSMAN_DATA);
            Assert.assertEquals(100, iplData.size());
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLWrongCsvFile_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(WRONG_BATSMAN_IPL_LOAD_DATA);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.INPUT_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenIPLWrongCsvFileType_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(BOWLER_IPL_LOAD_DATA);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.HEADER_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPCsvFileForDelimiterIssue_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(IPL_BATSMAN_DELIMITER_DATA);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.HEADER_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLCsvFileForHeaderIssue_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(IPL_BATSMAN_DELIMITER_DATA);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.HEADER_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLTestFile_ShouldReturnOutput() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> iplData = iplAnalyser.loadIplData(IPL_BATSMAN_TEST_FILE);
            String highestAverage = iplAnalyser.sortIplData(SortingTypes.PLAYER);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmenCSVS[0].PLAYER);
        } catch (IPLBatsmenException e) {
        }
    }

    @Test
    public void givenIPLTestFile_ShouldReturnOutput_OfHighestAverageRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(IPL_BATSMAN_DATA);
            String highestStrikeRate = iplAnalyser.sortIplData(SortingTypes.Average);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestStrikeRate, IPLBatsmenCSV[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmenCSVS[0].PLAYER);
        } catch (IPLBatsmenException e) {
        }
    }
    @Test
    public void givenIPLFile_ShouldReturnOutput_MaximumFoursAndSix() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(IPL_BATSMAN_DATA);
            String noOfSixesandFour = iplAnalyser.sortIplData(SortingTypes.NO_OF_SIXES_AND_FOURS);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(noOfSixesandFour, IPLBatsmenCSV[].class);
            Assert.assertEquals("David Warner ", iplBatsmenCSVS[99].PLAYER);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }
}