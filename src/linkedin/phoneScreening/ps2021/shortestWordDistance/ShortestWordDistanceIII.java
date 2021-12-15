package linkedin.phoneScreening.ps2021.shortestWordDistance;

import java.util.HashSet;

public class ShortestWordDistanceIII {

    public static void main(String[] args) {
        System.out.println(solution1(new String[]{"Brian", "goes", "to", "Costco", "Brian"}, 3, "Brian", "goes", "Costco"));
    }

    public int solution(String[] array, String a, String b) {
        int index1=-1, index2=-1;

        int min=Integer.MAX_VALUE;

        for(int i=0; i<array.length; i++) {
            if(array[i].equals(a)) {
                index1 = i;
                if(index2!=-1) min = Math.min(min, index1-index2);
            }
            if(array[i].equals(b)) {
                index2 = i;
                if(index1!=-1) min = Math.min(min, index2-index1);
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }


// ['Brian', 'goes', 'to', 'Costco', 'Brian']
// 'Brian', 'to', 'Costco' -> 2
    public static int solution1(String[] array, int n, String a, String b, String c) {
        int left=0, right=0;
        HashSet<String> origin = new HashSet();
        origin.add(a);
        origin.add(b);
        origin.add(c);
        HashSet<String> track = new HashSet();
        track.add(a);
        track.add(b);
        track.add(c);

        int min = Integer.MAX_VALUE;
        while(left<right || right < array.length) {
            if(track.contains(array[right])){
                track.remove(array[right]);
                if(track.size() == 0) {
                    min = Math.min(min, right-left);
                    while(left < array.length && origin.contains(array[left++])){
                        track.add(array[left-1]);
                    }
                    right++;
                }
            }
            right++;
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

