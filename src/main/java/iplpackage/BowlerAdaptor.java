package iplpackage;

import java.util.Map;

public class BowlerAdaptor extends IPLAdapter {
    @Override
    public Map<String, IPLDAO> loadIplData(String ... csvFilePath) throws IPLBatsmenException {
          return super.loadIplData(IPLBowler.class,csvFilePath[0]);
    }
}
