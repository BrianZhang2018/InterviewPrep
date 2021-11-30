package compass.y2021;

import java.util.ArrayDeque;

/**
 * Created by brianzhang on 11/5/21.
 */
public class TicketGifts {
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"A","A","B","B"}, new String[]{"B","A","B","A"}));
        System.out.println(solution(new String[] {"A","A","A","B","B","A"}, new String[]{"A","B","B","B","A","A"}));

        ArrayDeque<String> q = new ArrayDeque<>();
        for(String s : new String[] {"A","A","C","D","E","F"}) {
            q.push(s);
        }
        System.out.println(q.removeFirstOccurrence("A"));
        System.out.println(q.size());
    }

    public static int solution(String[] guests, String[] gifts) {
        ArrayDeque<String> guestQueue = new ArrayDeque<>(), giftsQueue = new ArrayDeque<>();

        for(String i : guests) guestQueue.add(i);

        for(String i : gifts) giftsQueue.add(i);

        int preLen = guestQueue.size();
        while(!guestQueue.isEmpty() && !giftsQueue.isEmpty()) {

            for(int i=0; i<guestQueue.size(); i++) {
                if(guestQueue.getFirst().equals(giftsQueue.getFirst())) {
                    guestQueue.removeFirstOccurrence(guestQueue.getFirst());
                    giftsQueue.removeFirstOccurrence(giftsQueue.getFirst());
                }else{
                    String temp = guestQueue.getFirst();
                    guestQueue.removeFirst();
                    guestQueue.add(temp);
                }
            }
            if(guestQueue.size() == preLen) return guestQueue.size();

            preLen =guestQueue.size();
        }


        return 0;
    }
}
