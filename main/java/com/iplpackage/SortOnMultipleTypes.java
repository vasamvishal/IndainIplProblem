package com.iplpackage;

import java.util.Comparator;

public class SortOnMultipleTypes implements java.util.Comparator<IPLDAO> {
    @Override
    public int compare(IPLDAO batsman1, IPLDAO batsman2) {
        return ((batsman1.noOfRuns*6+batsman1.noOfRuns*4)-(batsman2.noOfRuns*6+batsman2.noOfRuns*4));
    }
}
