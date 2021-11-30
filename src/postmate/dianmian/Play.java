package postmate.dianmian;

import java.util.List;

/**
 * Created by brianzhang on 7/31/20.
 */
public class Play {

    public int getTotalTime(List<DeliveryEvent> events) {
        long totaltime = events.get(events.size()-1).timestamp - events.get(0).timestamp;
        long cp = 0, idleTime = 0;

        for(int i=1; i<events.size(); i++){
            if(events.get(i-1).type == "dropoff" && events.get(i).type == "pickup" && cp == 0){
                idleTime += events.get(i-1).timestamp - events.get(i).timestamp;
            }else if (events.get(i).type == "dropoff" && cp>0){
                cp--;
            }else{
                cp++;
            }
        }

        return (int)(totaltime-idleTime)/60;

    }

}
