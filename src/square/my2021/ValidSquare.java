package square.my2021;

import java.util.*;

/**
 * If we calculate all distances between 4 points, 4 smaller distances should be equal (sides),
 * and 2 larger distances should be equal too (diagonals)
 *
 * if the input is integer, the solution-1 is enough
 * if th input can be decimal, we need solution-2
 *
 * Created by brianzhang on 11/22/21.
 */
public class ValidSquare {
    public static void main(String[] args) {
        System.out.println(validSquare(new int[]{0,0}, new int[]{0,1}, new int[]{1,0}, new int[]{1,1}));
        System.out.println(validSquare1(new int[]{0,0}, new int[]{0,1}, new int[]{1,0}, new int[]{1,1}));
    }

    // solution -1
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> set = new HashSet(Arrays.asList(dist(p1, p2), dist(p1, p3),dist(p1, p4),
                dist(p2, p3), dist(p2, p4), dist(p3, p4)));

        return !set.contains(0) && set.size() == 2;
    }

    // solution-2
    // (4 sides of equal length && 2 diagonals of equal length) <--> The four points can form a square.
    public static boolean validSquare1(int[] p1, int[] p2, int[] p3, int[] p4) {
        Map<Integer, Integer> map = new TreeMap<>();    // Maps distance to number of points with that distance.
        helper(p1, p2, map);
        helper(p1, p3, map);
        helper(p1, p4, map);
        helper(p2, p3, map);
        helper(p2, p4, map);
        helper(p3, p4, map);
        Iterator<Integer> it = map.values().iterator(); // Because the TreeMap sorts keys in ascending order and we know that the sides are shorter than the diagonals, we know that the first key should have the value 4, and the second key should have the value 2.
        return map.size() == 2 && it.next() == 4 && it.next() == 2;
    }

    public static void helper(int[] p1, int[] p2, Map<Integer, Integer> map) {
        int temp = dist(p1, p2);
        map.put(temp, map.getOrDefault(temp, 0) + 1);
    }

    // shared function
    // Euclidian distance (without the square root) between two points.
    private static int dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
