package iplpackage;

import org.junit.Assert;
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

    @Mock
    IPLAdapter adapter;

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();


    @Test
    public void testQuery() throws IPLBatsmenException {
        IplAnalyser iplAnalyser = new IplAnalyser();
        Map<String,IPLDAO> map =new HashMap<>();
        map.put("Vishal",new IPLDAO("1","David Warner ","12","12","2","692","100*","69.2","481","143.86","1","8","57","21"));
        IPLAdapter adapter= mock(IPLAdapter.class);
        when(adapter.loadIplData(IPLAdapter.class,IPL_BATSMAN_DATA)).thenReturn((map));
        Assert.assertEquals(1,map.size());
    }
}
