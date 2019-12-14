//Welcome to ipl problem


import censusanalyser.IndiaCensusCSV;
import com.google.gson.Gson;
import com.iplpackage.IPLBatsmenCSV;
import com.iplpackage.IPLBatsmenException;
import com.iplpackage.IplAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IplAnalyserTest {

    private static String IPL_BATSMAN_DATA = "/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String WRONG_BATSMAN_IPL_LOAD_DATA = "/home/user/IPlProblem/IPL/Problem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String BOWLER_IPL_LOAD_DATA = "/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static String IPL_BATSMAN_DELIMITER_DATA ="/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetDelimiterTypeMostRuns.csv";
    private static String IPL_BATSMAN_TEST_FILE="/home/user/IPlProblem/IPLProblem/src/test/resources/ipl2019FactsheetTestFile.csv";
    @Test
    public void givenIPLCsvFile_ShouldReturn_ExactCount() {
        IplAnalyser iplAnalyser = new IplAnalyser();
        try {
            List<IPLBatsmenCSV> iplData = iplAnalyser.loadIplData(IPL_BATSMAN_DATA);
            Assert.assertEquals(101, iplData.size());
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
    public void givenIPLTestFile_ShouldReturnOutput() throws IPLBatsmenException {
        IplAnalyser iplAnalyser = new IplAnalyser();
        List<IPLBatsmenCSV> iplData = iplAnalyser.loadIplData(IPL_BATSMAN_TEST_FILE);
        String highestStrikeRate = iplAnalyser.SortIplData(iplData);
        IPLBatsmenCSV[] iplBatsmenCSVS = new Gson().fromJson(highestStrikeRate, IPLBatsmenCSV[].class);
        Assert.assertEquals("MS Dhoni",iplBatsmenCSVS[0].PLAYER);
    }
}