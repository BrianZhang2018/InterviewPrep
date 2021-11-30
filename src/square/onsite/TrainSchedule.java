package square.onsite;

import javafx.util.Pair;
import java.util.*;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=745252&page=2&extra=#pid15321130
 *
 * refer the BusRoutes.java
 *
 * Created by brianzhang on 4/12/21.
 */
public class TrainSchedule {

    public static void main(String[] args) {
        String[][] schedules =new String[][]{
                {"0",  "17", "-", "40", "60"},
                {"10", "17", "25","-", "50"},
                {"-",  "18", "-", "30", "55"}};

        System.out.println(earliestArrivalNoTransfer(schedules, 0, 3));
        System.out.println(Arrays.toString(earliestArrivalWithOneTransfer(schedules, 0, 3)));
    }

    public static int earliestArrivalNoTransfer(String[][] schedules, int start, int target) {
        // stop -> Schedules
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i=0; i<schedules.length; i++){
            for(int stop=0; stop<schedules[i].length; stop++){
                if(!schedules[i][stop].equals("-")){
                    graph.putIfAbsent(stop, new ArrayList<>());
                    graph.get(stop).add(i);
                }
            }
        }

        int earliestArrivalTime = Integer.MAX_VALUE, earliestArrivalBusSchedule = -1;

        List<Integer> validSchedules = graph.get(start);
        for(int schedule : validSchedules){
            String[] stops = schedules[schedule];

            for(int i=0; i<stops.length; i++){
                if(stops[i].equals("-") || i > target) continue;

                if(i == target && Integer.valueOf(stops[i]) < earliestArrivalTime) {
                    earliestArrivalTime = Integer.valueOf(stops[i]);
                    earliestArrivalBusSchedule = schedule;
                    break;
                }
            }
        }

        return earliestArrivalBusSchedule;
    }

    public static int[] earliestArrivalWithOneTransfer(String[][] trainSchedules, int start, int target) {
        // stop : [(busSchedule, arrivalTime)]
        Map<Integer, List<Pair<Integer, String>>> graph = new HashMap<>();
        for(int schedule=0; schedule < trainSchedules.length; schedule++){
            for(int stop=0; stop < trainSchedules[schedule].length; stop++){
                if(!trainSchedules[schedule][stop].equals("-")){
                    graph.putIfAbsent(stop, new ArrayList<>());
                    graph.get(stop).add(new Pair<>(schedule, trainSchedules[schedule][stop]));
                }
            }
        }

        int earliestArrivalTime = Integer.MAX_VALUE, earliestArrivalTrainSchedule = -1;
        
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> used = new HashSet<>();
        q.add(start);
        int step = 0;

        // BFS level-wise traverse, 只能换一次车，等同于step=1
        while(!q.isEmpty() && step<=1){
            int size = q.size();
            while(size-- > 0){
                int stop = q.poll();
                int minArrivalTimeCurrLevel = Integer.MAX_VALUE;
                // got the schedules which serve this stop
                List<Pair<Integer, String>> schedules = graph.get(stop);
                for(Pair<Integer, String> p : schedules){
                    int schedule = p.getKey();
                    int arrivalTime = Integer.valueOf(p.getValue());
                    // skip the used train, but need get the minArrivalTimeCurrLevel which be used to check whether can catch the "transfer train"
                    if(!used.add(schedule)) {
                        minArrivalTimeCurrLevel = Math.min(arrivalTime, minArrivalTimeCurrLevel);
                        continue;
                    }
                    // 1. step = 0: Don't need transfer on the initial stop, so don't need compare the arrival time
                    // 2. step > 0: We can transfer between the schedule, but the arrival time of "stop(transfer to)" which need later than "stop(transfer from)"
                    if(arrivalTime < minArrivalTimeCurrLevel && step > 0) continue; // not able to transfer to this stop as People arrived time later than arrival time of this stop

                    for(int i=stop+1; i<trainSchedules[schedule].length; i++){
                        if(i > target) break;

                        if(trainSchedules[schedule][i].equals("-")) continue;

                        if(i == target) {
                            earliestArrivalTime =Integer.valueOf(trainSchedules[schedule][i]);
                            earliestArrivalTrainSchedule = schedule;
                            break;
                        }

                        if(!q.contains(i)){
                            q.add(i);
                        }
                    }
                }
            }
            step++;
        }

        return new int[]{earliestArrivalTrainSchedule, earliestArrivalTime};
    }
}
