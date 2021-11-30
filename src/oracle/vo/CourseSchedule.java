package oracle.vo;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * freq 2
 *
 * print out the order
 * Created by brianzhang on 12/18/20.
 */
public class CourseSchedule {

    public static void main(String[] args) {
        System.out.println(canFinish(4, new int[][]{{1, 0}, {3, 1}, {2, 0}, {1, 2}, {3, 2}}));
        System.out.println("Order is: " + Arrays.toString(findOrder(4, new int[][]{{1, 0}, {3, 1}, {2, 0}, {1, 2}, {3, 2}})));
        // System.out.println(Arrays.toString(findCycle(4, new int[][]{{0, 1}, {2, 0}, {0, 3}, {3, 2}}).toArray()));
        System.out.println("Cycle is: " + Arrays.toString(findCycle(4, new int[][]{{2, 0}, {3, 2}, {1, 3}, {2, 1}}).toArray()));
    }

    // CourseI
    public static boolean canFinish(int n, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[n]; // describe the adjacent relationship (you can use map here also)
        int[] inDegree = new int[n];    //入度
        List<Integer> preCourses = new ArrayList(); // you can also use a queue here

        for (int i = 0; i < n; ++i) graph[i] = new ArrayList<>();  //initiate the graph

        for (int[] e : prerequisites) {
            graph[e[1]].add(e[0]);  // e[1] is the prerequisite course of e[0], 收集所有依赖e[1]作为前驱的课程
            inDegree[e[0]]++;       // e[1]->e[0], so inDegree[e[0]]++, e[0]有多少前驱课程
        }
        // find the start node -> inDegree = 0, 意味着它是起点
        for (int i = 0; i < n; ++i)
            if (inDegree[i] == 0)
                preCourses.add(i); //把入度为零的vertex放入前导课list

        int index = 0;
        while (index < preCourses.size()) {
            for (int i : graph[preCourses.get(index)]) {
                if (--inDegree[i] == 0)     // "--inDegree" when the prerequisite course has done
                    preCourses.add(i);
            }
            index++;
        }

        return preCourses.size() == n;
    }

    // CourseII - print the order of courses
    public static int[] findOrder(int n, int[][] prerequisites) {
        List<Integer>[] dag = new ArrayList[n];
        int[] inDegree = new int[n];
        List<Integer> bfs = new ArrayList<>();

        for (int i = 0; i < n; ++i)
            dag[i] = new ArrayList<>();

        for (int[] e : prerequisites) {
            dag[e[1]].add(e[0]);
            inDegree[e[0]]++;
        }
        for (int i = 0; i < n; ++i) {
            if (inDegree[i] == 0) {
                bfs.add(i);
            }
        }

        for (int i = 0; i < bfs.size(); ++i) {
            for (int j : dag[bfs.get(i)]) {
                if (--inDegree[j] == 0) {
                    bfs.add(j);
                }
            }
        }

        if (bfs.size() == n) {
            int[] order = new int[n];
            for (int i=0; i<bfs.size(); i++) order[i]=bfs.get(i);
            return order;
        } else {
            return new int[]{};
        }
    }

    // find cycle: start from each point to find the cycle, if traverse back to the start point which means it's a cycle
    public static List<Integer> findCycle(int n, int[][] preRequisites) {
        List<Integer>[] adjacent = new ArrayList[n];
        int[] degree = new int[n];

        for (int i = 0; i < n; ++i)
            adjacent[i] = new ArrayList<>();

        for (int[] e : preRequisites) {
            adjacent[e[1]].add(e[0]);
            degree[e[0]]++;
        }

        int start = 0;
        while (start < n) {  // start from each point to find the cycle
            int[] temp = degree;
            Queue<Integer> bfs=new LinkedList();
            List<Integer> res=new ArrayList();
            bfs.add(start);
            res.add(start);

            while (!bfs.isEmpty()) {
                List<Integer> adjacentList = adjacent[bfs.poll()];
                for (int i : adjacentList) {
                    if (i == start) {   // detected cycle since traversed back to the start point
                        return res;
                    }
                    if (--temp[i] == 0) {
                        bfs.add(i);
                        res.add(i);
                    }
                }
            }
            start++;
        }

        return new ArrayList<>();
    }
}
