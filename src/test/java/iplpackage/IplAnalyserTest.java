package iplpackage;//Welcome to ipl problem

import com.google.gson.Gson;
import iplpackage.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IplAnalyserTest {
    private static String IPL_BATSMAN_DATA = "/home/user/new ipl folder/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String WRONG_BATSMAN_IPL_LOAD_DATA = "/home/user/IPlProblem/IPL/Problem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String BOWLER_IPL_LOAD_DATA = "/home/user/new ipl folder/src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static String IPL_BATSMAN_DELIMITER_DATA = "/home/user/new ipl folder/src/test/resources/IPL2019FactsheetDelimiterTypeMostRuns.csv";
    private static String IPL_BATSMAN_TEST_FILE = "/home/user/new ipl folder/src/test/resources/ipl2019FactsheetTestFile.csv";
    private static String DEMO_IPLBOWLER_FILE="/home/user/new ipl folder/src/test/resources/DemoBowlerFile.csv";
    @Test
    public void givenIPLCsvFile_ShouldReturn_ExactCount() {

        IplAnalyser iplAnalyser = new IplAnalyser();

        try {
            Map<String, IPLDAO> iplData = iplAnalyser.loadIplData(SortingTypes.BATSMAN,IPL_BATSMAN_DATA);
            Assert.assertEquals(100, iplData.size());
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLWrongCsvFile_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(SortingTypes.BATSMAN,WRONG_BATSMAN_IPL_LOAD_DATA);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.INPUT_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenIPLWrongCsvFileType_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(SortingTypes.BATSMAN,BOWLER_IPL_LOAD_DATA);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.HEADER_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPCsvFileForDelimiterIssue_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(SortingTypes.BATSMAN,IPL_BATSMAN_DELIMITER_DATA);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.HEADER_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLCsvFileForHeaderIssue_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.loadIplData(SortingTypes.BATSMAN,IPL_BATSMAN_DELIMITER_DATA);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.HEADER_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLTestFile_ShouldReturnOutput() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_TEST_FILE);
            String highestAverage = iplAnalyser.sortIplData(SortingTypes.PLAYER,stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("Andre Russell", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
        }
    }

    @Test
    public void givenIPLTestFile_ShouldReturnOutput_OfHighestAverageRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String highestStrikeRate = iplAnalyser.sortIplData(SortingTypes.Average, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestStrikeRate, IPLBatsmenCSV[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
        }
    }

    @Test
    public void givenIPLFile_ShouldReturnOutput_MaximumFoursAndSix() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();

            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String noOfSixesandFour = iplAnalyser.sortIplData(SortingTypes.NO_OF_SIXES_AND_FOURS, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(noOfSixesandFour, IPLBatsmenCSV[].class);
            Assert.assertEquals("David Warner ", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldReturnOutput_HighestStrikeRate_WithMaximumFoursAndSixes() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_TEST_FILE);
            String highestStrikeRate = iplAnalyser.sortIplData(SortingTypes.HIGHEST_STRIKERATE_BASED_ON_FOURSANDSIXES, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestStrikeRate, IPLBatsmenCSV[].class);
            Assert.assertEquals("Andre Russell", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLFile_ShouldReturnOutput_WithGreatAveragesAndHighestStrikeRate() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String basedOnStrikeRateAndAverage = iplAnalyser.sortIplData(SortingTypes.HIGHEST_AVERAGE_BASED_ON_HIGHEST_STRIKERATE, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(basedOnStrikeRateAndAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLFile_ShouldReturnOutput_WithMaximumRunsAndBestAverage() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_TEST_FILE);
            String basedOnStrikeRateAndAverage = iplAnalyser.sortIplData(SortingTypes.MAXIMUM_RUNS_AND_BESTAVERAGE, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(basedOnStrikeRateAndAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("Andre Russell", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLBowlerFile_ShouldReturnOutput_WithMaximumAverageInBowlers() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BOWLER, DEMO_IPLBOWLER_FILE);
            String basedOnStrikeRateAndAverage = iplAnalyser.sortIplData(SortingTypes.BESTAVERAGE_IN_BOWLERS, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(basedOnStrikeRateAndAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("Kagiso Rabada", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLBowlerFile_ShouldReturnOutput_WithMaximumStrikeRateInBowlers() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BOWLER, DEMO_IPLBOWLER_FILE);
            String basedOnStrikeRateAndAverage = iplAnalyser.sortIplData(SortingTypes.BESTSTRIKE_RATE_IN_BOWLERS, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(basedOnStrikeRateAndAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("Ravichandran Ashwin", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLBowlerFile_ShouldReturnOutput_WithBestEconomyInBowlers() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser.loadIplData(SortingTypes.BOWLER, DEMO_IPLBOWLER_FILE);
            String basedOnStrikeRateAndAverage = iplAnalyser.sortIplData(SortingTypes.BESTECONOMY_IN_BOWLERS, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(basedOnStrikeRateAndAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("Lasith Malinga", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }
}

