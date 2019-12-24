package iplpackage;

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
    @Mock
    IPLAdapter adapter;

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    public void name() {
        this.map.put("DAvid Warner", new IPLDAO("1", "David Warner ", "12", "12",
                "2", "692", "100*", "69.2", "481", "143.86",
                "1", "8", "57", "21"));
        this.map.put("Virat kohli", new IPLDAO("1", "David Warner ", "12", "12",
                "2", "692", "100*", "69.2", "481", "143.86",
                "1", "8", "57", "21"));
        this.map.put("Ms Dhoni", new IPLDAO("1", "David Warner ", "12", "12",
                "2", "692", "100*", "69.2", "481", "143.86",
                "1", "8", "57", "21"));
    }

    @Test
    public void givenIPLBattingFile_ShouldReturnSizeOf3UsingMockito() throws IPLBatsmenException {
        name();
        BatsmanAdaptor BatsmanAdapter= mock(BatsmanAdaptor.class);
        IplAnalyser iplAnalyser1 = new IplAnalyser();
        iplAnalyser1.setValue(BatsmanAdapter);
        when(BatsmanAdapter.loadIplData(IPL_BATSMAN_DATA)).thenReturn((this.map));
        Map<String, IPLDAO> map1 = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
        Assert.assertEquals(3,map1.size());
    }

    @Test
    public void givenIPLBowlingFile_ShouldReturnSizeof3UsingMockito() throws IPLBatsmenException {
        name();
        BowlerAdaptor BowlerAdaptor=mock(BowlerAdaptor.class);
        IplAnalyser iplAnalyser1 = new IplAnalyser();
        iplAnalyser1.setValue(BowlerAdaptor);
        when(BowlerAdaptor.loadIplData(BOWLER_IPL_LOAD_DATA)).thenReturn((this.map));
        Map<String, IPLDAO> map1 = iplAnalyser1.loadIplData(SortingTypes.BOWLER, BOWLER_IPL_LOAD_DATA);
        Assert.assertEquals(3,map1.size());
    }
}
