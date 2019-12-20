package iplpackage;

import java.util.Map;
public class BatsmanAdaptor extends IPLAdapter {

    @Override
    public Map<String, IPLDAO> loadIplData(String csvFilePath) throws IPLBatsmenException {
        return super.loadIplData(IPLBatsmenCSV.class,csvFilePath);
    }
}



