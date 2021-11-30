package square.onsite;

import java.util.*;

/**
 * https://leetcode.com/problems/bus-routes/
 *
 * For each of the bus stop, we maintain all the buses (bus routes) that go through it. To do that, we use a HashMap, where bus stop number is the key and all the buses (bus routes) that go through it are added to an ArrayList.

 * We use BFS, where we process elements in a level-wise manner. We add the Start bus stop in the queue. Next, when we enter the while loop, we add all the bus stops that are reachable by all the bus routes that go via the Start.
 * Thus, if we have the input as [[1, 2, 7], [3, 6, 7]] and Start as 6, then upon processing bus stop 6, we would add bus stops 3 and 7.
 * With this approach, all the bus stops at a given level, are "equal distance" from the start node, in terms of number of buses that need to be changed.

 * To avoid loops, we also maintain a HashSet that stores the buses that we have already visited.

 * Note that while in this approach, we use each stop for doing BFS, one could also consider each bus (route) also for BFS.
 *
 * Created by brianzhang on 4/13/21.
 */
public class BusRoutes {

    public static void main(String[] args) {
        int[][] routes = {{1,2,7},{3,6,7}};
        System.out.println(numBusesToDestination(routes,1, 6));
        System.out.println(Arrays.toString(sumZero(7)));
    }

    public static int[] sumZero(int n) {
        int[] res = new int[n];
        int left = 0, right = n - 1, start = 1;
        while (left < right) {
            res[left++] = start;
            res[right--] = -start;
            start++;
        }
        return res;
    }

    public static int numBusesToDestination(int[][] routes, int start, int target) {
        if (start == target) return 0;

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < routes.length; i++){
            for (int j : routes[i]) {
                graph.putIfAbsent(j, new HashSet<>());
                graph.get(j).add(i);
            }
        }

        if(!graph.containsKey(start) || !graph.containsKey(target)) return -1;

        Set<Integer> used = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            while(size-- > 0){
                for (int busRoute : graph.get(q.poll()))
                    if (used.add(busRoute)) {    // skip visited bus route
                        for (int busStop : routes[busRoute]) {
                            if (busStop == target) {
                                return step;
                            }
                            q.offer(busStop);
                        }
                    }
            }
        }

        return -1;
    }
}
