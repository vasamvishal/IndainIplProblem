//Welcome to ipl problem


import com.iplpackage.IPLBatsmenException;
import com.iplpackage.IplAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class IplAnalyserTest {

    private static String IPL_BATSMAN_DATA = "/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String WRONG_BATSMAN_IPL_LOAD_DATA = "/home/user/IPlProblem/IPL/Problem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static String BOWLER_IPL_LOAD_DATA = "/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLCsvFile_ShouldReturn_ExactCount() {
        IplAnalyser iplAnalyser = new IplAnalyser();
        try {
            int iplData = iplAnalyser.loadIplData(IPL_BATSMAN_DATA);
            Assert.assertEquals(101, iplData);
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
            Assert.assertEquals(IPLBatsmenException.IPLException.NO_SUCH_FILE, e.type);
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
}
