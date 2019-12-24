package iplpackage;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class BatsmanAdapterTest {
    private static String IPL_BATSMAN_DATA = "/home/admin1/IPLVishal/IndainIplProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String BOWLER_IPL_LOAD_DATA = "/home/admin1/IPLVishal/IndainIplProblem/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenClassShouldLoadFile_Returnrecords() {
        try {
            BatsmanAdaptor batsmanAdaptor = new BatsmanAdaptor();
            Map<String, IPLDAO> stringIPLDAOMap = batsmanAdaptor.loadIplData(IPLBatsmenCSV.class, IPL_BATSMAN_DATA);
            Assert.assertEquals(100,stringIPLDAOMap.size());
        }
        catch (Exception e)
        {

        }
    }
    @Test
    public void givenClassShouldLoadFileforBattingandbowling_AndReturnAverageOfBatsman() {
        try {
            BatsmanAdaptor batsmanAdaptor = new BatsmanAdaptor();
            Map<String, IPLDAO> stringIPLDAOMap = batsmanAdaptor.loadIplData(IPLBatsmenCSV.class, IPL_BATSMAN_DATA);
            batsmanAdaptor.loadBatsmanMap(stringIPLDAOMap,BOWLER_IPL_LOAD_DATA);
            Assert.assertEquals(100,stringIPLDAOMap.size());
        }
        catch (Exception e)
        {

        }
    }
}
