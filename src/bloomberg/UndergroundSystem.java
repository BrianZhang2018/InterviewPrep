package bloomberg;

import category.model.Pair;
import java.util.*;

/**
 * https://leetcode.com/problems/design-underground-system/
 *
 * Created by brianzhang on 5/12/20.
 */
public class UndergroundSystem {
    public static void main(String[] args) {
        UndergroundSystem test = new UndergroundSystem();
        test.checkIn(45, "Leyton", 3);
        test.checkIn(32, "Paradise", 8);
        test.checkIn(27, "Leyton", 10);
        test.checkOut(45, "Waterloo", 15);
        test.checkOut(27, "Waterloo", 20);
        test.checkOut(32, "Cambridge", 22);
        System.out.println(test.getAverageTime("Leyton", "Waterloo"));
    }

    Map<Integer, Pair<String, Integer>> checkin = new HashMap<>();  // Uid : {StationName, Time}
    Map<String, Pair<Integer, Integer>> checkout = new HashMap<>(); // Route : {TotalTime, Count}

    public UndergroundSystem() {}

    public void checkIn(int id, String stationName, int t) {
        checkin.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> ci = checkin.get(id);
        String route = ci.getKey() + "_" + stationName; // combination of start and end station name as route
        int totalTime = t - ci.getValue();
        Pair<Integer, Integer> ck = this.checkout.getOrDefault(route, new Pair<>(0, 0));
        this.checkout.put(route, new Pair<>(ck.getKey() + totalTime, ck.getValue() + 1));
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "_" + endStation;
        Pair<Integer, Integer> checkout = this.checkout.get(route);
        return (double) checkout.getKey() / checkout.getValue();
    }
}
