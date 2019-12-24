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
    Map<String, IPLDAO> map=null;
    @Mock
    IPLAdapter adapter;

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();



    @Test
    public void givenIPLBattingFile_ShouldReturnSizeOf3UsingMockito() throws IPLBatsmenException {
            Map<String, IPLDAO> map = new HashMap<>();
            map.put("Vishal", new IPLDAO("1", "David Warner ", "12", "12",
                    "2", "692", "100*", "69.2", "481", "143.86",
                    "1", "8", "57", "21"));
            map.put("Vishal12", new IPLDAO("1", "David Warner ", "12", "12",
                    "2", "692", "100*", "69.2", "481", "143.86",
                    "1", "8", "57", "21"));
            map.put("Vishal123", new IPLDAO("1", "David Warner ", "12", "12",
                    "2", "692", "100*", "69.2", "481", "143.86",
                    "1", "8", "57", "21"));

        BatsmanAdaptor batsmanAdaptor= mock(BatsmanAdaptor.class);
        IplAnalyser iplAnalyser1 = new IplAnalyser();
        iplAnalyser1.setValue(batsmanAdaptor);
        when(batsmanAdaptor.loadIplData(IPL_BATSMAN_DATA)).thenReturn((map));
        Map<String, IPLDAO> map1 = iplAnalyser1.loadIplData(SortingTypes.BATSMAN, IPL_BATSMAN_DATA);
        Assert.assertEquals(3,map1.size());
    }
}
