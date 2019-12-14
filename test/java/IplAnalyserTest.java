//Welcome to ipl problem


import com.iplpackage.IplAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IplAnalyserTest {

    private static  String IPL_LOAD_DATA="/home/user/IPlProblem/IPLProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIPLCsvFile_ShouldReturn_ExactCount() {
        IplAnalyser iplAnalyser = new IplAnalyser();
        int iplData = iplAnalyser.loadIplData(IPL_LOAD_DATA);
        Assert.assertEquals(101,iplData);
    }
}
