package iplpackage;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IplAnalyser {
    private Map<String, IPLDAO> bowlingSortMap;
    Map<String, IPLDAO> battingRecordMap;
    Map<SortingTypes, Comparator<IPLDAO>> sortingMap;
    private IPLAdapter setFactory;


    public IplAnalyser() {
        this.sortingMap = new HashMap<>();
        this.sortingMap.put(SortingTypes.Average, Comparator.comparing(sortType -> sortType.battingAverage,
                Comparator.reverseOrder()));
        this.sortingMap.put(SortingTypes.NO_OF_SIXES_AND_FOURS, new SortOnMultipleTypes().reversed());
        this.sortingMap.put(SortingTypes.PLAYER, Comparator.comparing(sortType -> sortType.playerName));
        this.sortingMap.put(SortingTypes.HIGHEST_STRIKERATE_BASED_ON_FOURSANDSIXES, new SortOnMultipleTypes()
                .reversed().thenComparing((sortType -> sortType.strikeRate)));
        Comparator<IPLDAO> compareAverage = Comparator.comparing(sortType -> sortType.battingAverage);
        this.sortingMap.put(SortingTypes.HIGHEST_AVERAGE_BASED_ON_HIGHEST_STRIKERATE, compareAverage
                .thenComparing(sortType -> sortType.strikeRate).reversed());
        Comparator<IPLDAO> compareBasedOnRuns = Comparator.comparing(sortType -> sortType.noOfRuns);
        this.sortingMap.put(SortingTypes.MAXIMUM_RUNS_AND_BESTAVERAGE, compareBasedOnRuns.
                thenComparing(sortType -> sortType.battingAverage).reversed());
        this.sortingMap.put(SortingTypes.BESTAVERAGE_IN_BOWLERS,
                Comparator.comparing(sorttype -> sorttype.bowlingAverage, Comparator.reverseOrder()));
        this.sortingMap.put(SortingTypes.BESTSTRIKE_RATE_IN_BOWLERS, Comparator.comparing
                (sorttype -> sorttype.bowlingStrikeRate, Comparator.reverseOrder()));
        this.sortingMap.put(SortingTypes.BESTECONOMY_IN_BOWLERS, Comparator.comparing
                (sorttype -> sorttype.economy));
        this.sortingMap.put(SortingTypes.BESTSTRIKE_RATE_WITH4AND6, new bowlerStrikeRate().
                thenComparing(sorttype -> sorttype.bowlingStrikeRate).reversed());
        Comparator<IPLDAO> compareBasedOnAverage = Comparator.comparing(sortType -> sortType.bowlingAverage);
        this.sortingMap.put(SortingTypes.BEST_AVERGAE_BESTSTRIKE_RATE, compareBasedOnAverage.
                thenComparing(sorttype -> sorttype.bowlingStrikeRate).reversed());
        Comparator<IPLDAO> compareBasedOnWickets = Comparator.comparing(sortType -> sortType.noOfWickets);
        this.sortingMap.put(SortingTypes.MAX_WICKETS_AND_BEST_AVERAGE,
                compareBasedOnWickets.thenComparing(sorttype -> sorttype.battingAverage).reversed());
        Comparator<IPLDAO> compareBasedOnBattingAverage = Comparator.comparing(sortType -> sortType.battingAverage);
        this.sortingMap.put(SortingTypes.BEST_ALLROUNDER_AVERAGE, compareBasedOnBattingAverage
                .thenComparing(sortType -> sortType.bowlingStrikeRate).reversed());
        Comparator<IPLDAO> compareBasedOnBatsmanRuns = Comparator.comparing(sortType -> sortType.battingNoOfRuns);
        this.sortingMap.put(SortingTypes.ALL_ROUNDER_BASED_ON_RUNS_AND_WICKETS,compareBasedOnBatsmanRuns.thenComparing
                (sortType ->sortType.noOfWickets).reversed());
    }

    public Map<String, IPLDAO> loadIplData(SortingTypes eClass, String... csvFilePath) throws IPLBatsmenException {
     //   IPLAdapter iplObject = IPLFactory.createObject(eClass);
        Map<String, IPLDAO> stringIPLDAOMap = setFactory.loadIplData(csvFilePath);
        return stringIPLDAOMap;
    }

    public String sortIplData(SortingTypes sortType, Map<String, IPLDAO> stringIPLDAOMap) {
        Comparator<IPLDAO> comparating = this.sortingMap.get(sortType);
        ArrayList sortedList = stringIPLDAOMap.values().stream()
                .sorted(comparating)
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateData = new Gson().toJson(sortedList);
        return sortedStateData;
    }

    public void setValue(IPLAdapter batsman) {
        this.setFactory=batsman;
    }
}
