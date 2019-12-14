//Welcome to ipl problem


import com.iplpackage.IPLBatsmenException;
import com.iplpackage.IplAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IplAnalyserTest {

    private static  String IPL_LOAD_DATA="/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static  String WRONGIPL_LOAD_DATA="/home/user/IPlProblem/IPL/Problem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIPLCsvFile_ShouldReturn_ExactCount() {
        IplAnalyser iplAnalyser = new IplAnalyser();
        int iplData = 0;
        try {
            iplData = iplAnalyser.loadIplData(IPL_LOAD_DATA);
            Assert.assertEquals(101,iplData);
        } catch (IPLBatsmenException e) {
            Assert.assertEquals(IPLBatsmenException.IPLException.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenIPLWrongCsvFile_ShouldReturnException() {
        try {
            IplAnalyser iplAnalyser = new IplAnalyser();
             iplAnalyser.loadIplData(WRONGIPL_LOAD_DATA);
        }
        catch (IPLBatsmenException e)
        {
            Assert.assertEquals(IPLBatsmenException.IPLException.NO_SUCH_FILE,e.type);
        }
    }
}
