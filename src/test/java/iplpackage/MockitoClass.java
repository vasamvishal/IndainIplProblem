package iplpackage;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoClass {
    private static String IPL_BATSMAN_DATA = "/home/user/new ipl folder/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String BOWLER_IPL_LOAD_DATA = "/home/user/new ipl folder/src/test/resources/IPL2019FactsheetMostWkts.csv";

    Map<String, IPLDAO> map = new HashMap<>();
    IplAnalyser iplAnalyser1;
    BatsmanAdaptor BatsmanAdapter = mock(BatsmanAdaptor.class);
    @Mock
    IPLAdapter adapter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void battingMap() throws IPLBatsmenException {
        this.map.put("DAvid Warner", new IPLDAO("David Warner", 23.34,"12","3",455,45.54));
        this.map.put("Virat kohli", new IPLDAO("MS Dhoni", 41.4,"13","3",44,45.44));
        this.map.put("Ms Dhoni", new IPLDAO("Virat kohli", 24.34,"12","3",444,56.74));
        BatsmanAdaptor BatsmanAdapter = mock(BatsmanAdaptor.class);
        when(BatsmanAdapter.loadIplData(IPL_BATSMAN_DATA)).thenReturn((this.map));
        iplAnalyser1 = new IplAnalyser();
        iplAnalyser1.setValue(BatsmanAdapter);
    }

    @Test
    public void givenIPLBattingFile_ShouldReturnSizeOf3UsingMockito() throws IPLBatsmenException {
        Map<String, IPLDAO> map1 = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
        Assert.assertEquals(3, map1.size());
    }

    @Test
    public void givenIPLBowlingFile_ShouldReturnSizeof3UsingMockito() throws IPLBatsmenException {
        battingMap();
        BowlerAdaptor BowlerAdaptor = mock(BowlerAdaptor.class);
        iplAnalyser1.setValue(BowlerAdaptor);
        when(BowlerAdaptor.loadIplData(BOWLER_IPL_LOAD_DATA)).thenReturn((this.map));
        Map<String, IPLDAO> map1 = iplAnalyser1.loadIplData(SortingTypes.BOWLER, BOWLER_IPL_LOAD_DATA);
        Assert.assertEquals(3, map1.size());
    }

    @Test
    public void givenIPLTestFile_ShouldReturnOutputForPlayers() {
        try {

            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String highestAverage = iplAnalyser1.sortIplData(SortingTypes.PLAYER, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("David Warner", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
        }
    }

    @Test
    public void givenIPLTestFile_ShouldReturnOutput_OfHighestAverageRate() {
        try {
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String highestStrikeRate = iplAnalyser1.sortIplData(SortingTypes.Average, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestStrikeRate, IPLBatsmenCSV[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
        }
    }
    @Test
    public void givenIPLFile_ShouldReturnOutput_MaximumFoursAndSix() {
        try {
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String noOfSixesandFour = iplAnalyser1.sortIplData(SortingTypes.NO_OF_SIXES_AND_FOURS, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(noOfSixesandFour, IPLBatsmenCSV[].class);
            Assert.assertEquals("David Warner", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLFile_ShouldReturnOutput_HighestStrikeRate_WithMaximumFoursAndSixes() {
        try {
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String highestStrikeRate = iplAnalyser1.sortIplData(SortingTypes.HIGHEST_STRIKERATE_BASED_ON_FOURSANDSIXES,
                    stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestStrikeRate, IPLBatsmenCSV[].class);
            Assert.assertEquals("David Warner", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldReturnOutput_WithGreatAveragesAndHighestStrikeRate() {
        try {
            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String basedOnStrikeRateAndAverage = iplAnalyser1.sortIplData
                    (SortingTypes.HIGHEST_AVERAGE_BASED_ON_HIGHEST_STRIKERATE, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(basedOnStrikeRateAndAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldReturnOutput_WithMaximumRunsAndBestAverage() {
        try {

            Map<String, IPLDAO> stringIPLDAOMap = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
            String basedOnStrikeRateAndAverage = iplAnalyser1.sortIplData
                    (SortingTypes.MAXIMUM_RUNS_AND_BESTAVERAGE, stringIPLDAOMap);
            IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(basedOnStrikeRateAndAverage, IPLBatsmenCSV[].class);
            Assert.assertEquals("David Warner ", iplBatsmenCSVS[0].playerName);
        } catch (IPLBatsmenException e) {
            e.printStackTrace();
        }
    }

}
