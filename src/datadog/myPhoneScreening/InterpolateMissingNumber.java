package datadog.myPhoneScreening;

import java.util.*;

/**
 *
 interpolate(points: List[Tuple[int, int]], interval int) -> List[Tuple[int, int]]

 input = [(0, 100), (5, 110),            (15, 130) p                       (30, 115) n], interval = 5

 interpolate(input, 5) -> [(0, 100), (5, 110), (10, 120), (15, 130), (20, 125), (25, 120), (30, 115)]


 130┃          []   []    (last known point)
 125┃
 120┃       a        c
 115┃                   [] (next known point)
 110┃    o
 105┃
 100┃ o
 Value ┗━━━━━━━━━━━━━━━━━━━━
 0  5 10 15 20 25 30 (regular interval... in this case 5)

 x = time (always start at 0, we always measure at multiple of interval = 0, 5,  15, 20, 25

 * Created by brianzhang on 5/11/21.
 */
public class InterpolateMissingNumber {
    public static void main(String[] args) {
        List<Metric> testInput = Arrays.asList(new Metric(0L, 100.0), new Metric(5L, 110.0), new Metric(15L, 130.0), new Metric(30L, 115.0));
        InterpolateMissingNumber test = new InterpolateMissingNumber();
        for(Metric m : test.solution(testInput, 5)) {
            System.out.println(m.timestamp + " : " + m.value);
        }
    }

    static class Metric {
        public Long timestamp;
        public Double value;

        public Metric(Long timestamp, Double value){
            this.timestamp = timestamp;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + timestamp + ", " + value + ")";
        }
    }

    public List<Metric> solution(List<Metric> metrics, long interval) {
        if(metrics.size() <= 1) return metrics;

        List<Metric> input = new ArrayList<>(metrics);
        int index = 1;
        while(index < input.size()) {
            long currX = input.get(index).timestamp, prevX = input.get(index-1).timestamp;
            double currY = input.get(index).value, prevY = input.get(index-1).value;

            if(currX - prevX != interval){
                double count = (currX - prevX) / interval;
                double yInterval = (currY -prevY) / count;

                while(count-- > 1){
                    input.add(index, new Metric(input.get(index-1).timestamp + interval, input.get(index-1).value + yInterval));
                    index++;
                }

            }
            index++;
        }
        return input;
    }
}
