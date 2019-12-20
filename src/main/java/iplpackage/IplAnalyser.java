package iplpackage;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class IplAnalyser {
    private  Map<String, IPLDAO> bowlingSortMap;
    Map<String, IPLDAO> battingRecordMap;
    Map<SortingTypes,Comparator<IPLDAO>> sortingMap;
    public IplAnalyser() {
        this.sortingMap=new HashMap<>();
        this.sortingMap.put(SortingTypes.Average,Comparator.comparing(sortType -> sortType.average,
                Comparator.reverseOrder()));
        this.sortingMap.put(SortingTypes.NO_OF_SIXES_AND_FOURS,new SortOnMultipleTypes().reversed());
        this.sortingMap.put(SortingTypes.PLAYER,Comparator.comparing(sortType -> sortType.playerName));
        this.sortingMap.put(SortingTypes.HIGHEST_STRIKERATE_BASED_ON_FOURSANDSIXES,new SortOnMultipleTypes()
                .reversed().thenComparing((sortType -> sortType.strikeRate)));
        Comparator<IPLDAO> compareAverage=Comparator.comparing(sortType ->sortType.average);
        this.sortingMap.put(SortingTypes.HIGHEST_AVERAGE_BASED_ON_HIGHEST_STRIKERATE,compareAverage
                .thenComparing(sortType ->sortType.strikeRate).reversed());
        Comparator<IPLDAO> compareBasedOnRuns=Comparator.comparing(sortType ->sortType.noOfRuns);
        this.sortingMap.put(SortingTypes.MAXIMUM_RUNS_AND_BESTAVERAGE,compareBasedOnRuns.
                thenComparing(sortType ->sortType.average).reversed());
        this.sortingMap.put(SortingTypes.BESTAVERAGE_IN_BOWLERS,Comparator.comparing(sorttype -> sorttype.average));
    }
    public Map<String,IPLDAO> loadIplData(SortingTypes eClass,String csvFilePath) throws IPLBatsmenException {
            IPLAdapter iplObject = IPLFactory.createObject(eClass);
            Map<String, IPLDAO> stringIPLDAOMap = iplObject.loadIplData(csvFilePath);
            return stringIPLDAOMap;
    }

    public String sortIplData(SortingTypes sortType, Map<String, IPLDAO> stringIPLDAOMap) {
        ArrayList sortedList = stringIPLDAOMap.values().stream()
                .sorted(this.sortingMap.get(sortType))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateData = new Gson().toJson(sortedList);
        return sortedStateData;
    }
}
