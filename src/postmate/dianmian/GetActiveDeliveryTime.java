package postmate.dianmian;

import java.util.Arrays;
import java.util.List;

/**
 * Created by brianzhang on 7/27/20.
 */
public class GetActiveDeliveryTime {

    public static void main(String[] args) {
        List<DeliveryEvent> deliveryEvents = Arrays.asList(
                new DeliveryEvent(1, 1502803800, "pickup"),
                new DeliveryEvent(1, 1502805600, "dropoff"),
                new DeliveryEvent(2, 1502807400, "pickup"),
                new DeliveryEvent(3, 1502807700, "pickup"),
                new DeliveryEvent(2, 1502809200, "dropoff"),
                new DeliveryEvent(3, 1502809800, "dropoff"));

        System.out.println(solution(deliveryEvents));
    }

    public static int solution(List<DeliveryEvent> events) {

        long totalTime = events.get(events.size() - 1).timestamp - events.get(0).timestamp;
        long idleTime = 0, curryingPackages = 0;
        for (int i = 1; i < events.size(); i++) {
            if (events.get(i - 1).type == "dropoff" && events.get(i).type == "pickup" && curryingPackages == 0) {
                idleTime += events.get(i).timestamp - events.get(i - 1).timestamp;
            } else if (events.get(i).type == "dropoff" && curryingPackages > 0) {
                curryingPackages--;
            } else if (events.get(i).type == "pickup") {
                curryingPackages++;
            }
        }

        return (int) (totalTime - idleTime) / 60;
    }
}


class DeliveryEvent {
    int deliveryId;
    long timestamp;
    String type;

    public DeliveryEvent(int deliveryId, long timestamp, String kind) {
        this.deliveryId = deliveryId;
        this.timestamp = timestamp;
        this.type = kind;
    }
}
