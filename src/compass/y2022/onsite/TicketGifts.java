package compass.y2022.onsite;

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
        ArrayDeque<String> guestQ = new ArrayDeque<>(), giftsQ = new ArrayDeque<>();
        for(String i : guests) guestQ.add(i);
        for(String i : gifts) giftsQ.add(i);

        int preLen = guestQ.size();
        while(!guestQ.isEmpty() && !giftsQ.isEmpty()) {
            for(int i=0; i<guestQ.size(); i++) {
                if(guestQ.getFirst().equals(giftsQ.getFirst())) {
                    guestQ.removeFirstOccurrence(guestQ.getFirst());
                    giftsQ.removeFirstOccurrence(giftsQ.getFirst());
                }else{
                    String temp = guestQ.getFirst();
                    guestQ.removeFirst();
                    guestQ.add(temp);
                }
            }
            if(guestQ.size() == preLen) return guestQ.size();
            preLen =guestQ.size();
        }
        return 0;
    }
}
