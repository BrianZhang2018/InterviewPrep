package companies.amazon.OA;

import java.util.*;

public class ShezhaoAmazonOA {
    // https://leetcode.com/company/amazon/
    //1. MST
    //2. reorder log
    //3. 组装零件/文件合并
    //4. Amazon Fresh送货(k closest points to origin)'
    //5. 走迷宫(0,1,9 的2D Array， BFS)
    //6. 无人机送货/前后台进程 (Two Sum Closest)
    //7. 选合适的音乐/卡车装货物 (Two Sum)
    //8. Partition Label (LC 原题) ???
 
 
    // 1. MST 城市建路题 minimum spanning binaryTree，
    // https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=498030
    // 就是给的road是重复的
    // 题目意思是有一定数量的城市，城市之间已经有了一些道路。还有一些可以供选择的道路来建设。每个新建的道路有cost。问如果要连接所有的城市，新建路的最小的cost是多少。举个栗子：
    //Input 如下：
    //numTotalAvailableCities = 6
    //numTotalAvailableRoads = 3
    //roadsAvailable = [[1, 4], [4, 5], [2, 3]]
    //numNewRoadsConstruct = 4
    //costNewRoadsConstruct = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
    //Output 应该是： 7

    public static void main(String[] args) {
        int n = 6; // numTotalAvailableCities
        int numTotalAvailableRoads = 3;
        List<List<Integer>> roadsAvailable = new ArrayList<>();
        roadsAvailable.add(Arrays.asList(new Integer[]{1,4}));
        roadsAvailable.add(Arrays.asList(new Integer[]{4,5}));
        roadsAvailable.add(Arrays.asList(new Integer[]{2,3}));

        int numNewRoadsConstruct = 4;
        List<List<Integer>> costNewRoadsConstruct = new ArrayList<>();
        costNewRoadsConstruct.add(Arrays.asList(new Integer[]{1,2,5}));
        costNewRoadsConstruct.add(Arrays.asList(new Integer[]{1,3,10}));
        costNewRoadsConstruct.add(Arrays.asList(new Integer[]{1,6,2}));
        costNewRoadsConstruct.add(Arrays.asList(new Integer[]{5,6,5}));

        ShezhaoAmazonOA test = new ShezhaoAmazonOA();
        System.out.println(test.getMinimumCostConstruct(n, numTotalAvailableRoads, roadsAvailable, numNewRoadsConstruct, costNewRoadsConstruct));
    }
    // union-find solution without "weight" compare with KrushkalMinimumSpanningTree.java
    public int getMinimumCostConstruct(int n, int numTotalAvaiableRoads, List<List<Integer>> roadsAvailable,
                                       int numNewRoadsContruct,  List<List<Integer>> costNewRoadsConstruct) {
        if (n < 2) {
            return 0;
        }
        // Initialize available roads - O(Nn)
        UnionFind uf = new UnionFind(n + 1);
        int groupCount = n;
        for (List<Integer> road: roadsAvailable) {
            int i = road.get(0);
            int j = road.get(1);
            groupCount -= uf.union(i, j)? 1: 0;
        }
        // Initialize new roads - O(MlogM)
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.val , b.val));
        for (List<Integer> road: costNewRoadsConstruct) {
            heap.offer(new Node(road.get(0), road.get(1), road.get(2)));
        }
        // construct roads - O(Mn)
        int cost = 0;
        while (!heap.isEmpty() && groupCount > 1) {
            Node road = heap.poll();
            if (uf.union(road.x, road.y)) {
                groupCount--;
                cost += road.val;
            }
        }
        return groupCount > 1? -1: cost;
    }
 
    class UnionFind {
        int[] parents;
        public UnionFind(int size) {
            parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }
 
        private int root(int i) {
            while (parents[i] != i) {
                i = parents[i];
            }
            return i;
        }
 
        public boolean find(int i, int j) { // O(n)
            return root(i) == root(j);
        }
 
        public boolean union(int i, int j) { // O(n)
            if (find(i, j)) {
                return false;
            } else {
                parents[root(i)] = j; // parents[root(j)] = i, also work here. Here can be improved with "weight" so that append the small binaryTree to big binaryTree when do union
                return true;
            }
        }
    }
 
    // 2. reorder log，
    // LC 937。 https://leetcode.com/problems/reorder-log-files/
    // Reorder the logs so that all of the letter-logs come before any digit-log.
    // The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
    // The digit-logs should be put in their original order.
    // Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
    // Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new FileComparator());
        return logs;
 
    }
    class FileComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String[] aa = a.split(" ", 2);
            String[] bb = b.split(" ", 2);
            String contentA = aa[1];
            String contentB = bb[1];
            boolean AisDigitFile = isDigitFile(contentA);
            boolean BisDigitFile = isDigitFile(contentB);
            if (AisDigitFile && BisDigitFile) {
                return 0;
            } else if (AisDigitFile) {
                return 1;
            } else if (BisDigitFile) {
                return -1;
            } else {
                int compareContent = contentA.compareTo(contentB);
                if (compareContent == 0) {
                    return aa[0].compareTo(bb[0]);
                } else {
                    return compareContent;
                }
            }
        }
 
        private boolean isDigitFile(String content) {
            return Character.isDigit(content.charAt(0));
        }
    }
 
 
    // 3. 零件装配/合并part
    // 给一个array表示零件的size，每次可以把两个组装在一起。每次组装的cost是两个size之和。就是数字可能重复
    // 新组装出来零件的size也是这两个小零件size之和。问把所有零件组装成一整个的min cost。
    // time: O(nlogn), space: O(n), In Java programming, Java Priority Queue is implemented using Heap 
    // Data Structures and Heap has O(log(n)) time complexity to insert and delete element.
    public int minSum(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int x: array) {
            heap.offer(x);
        }
        int cost = 0;
        while (!heap.isEmpty()) { // keep it as least 2
            int cur = heap.poll() + heap.poll();
            cost += cur;
            if (heap.isEmpty()) {
                break;
            } else {
                heap.offer(cur);
            }
        }
        return cost;
    }
 

    // 4. 卡车送货的题 (k closest points to origin)
    // N个地点List<List<Integer>> 地点的坐标, M代表需要送的数量
    // output：一个List<List<Integer>> 代表送货的地点坐标x,y, 其实就是让你计算距离卡车最近的M个地点.
    // eg. N = 3, M = 2, List<List<Ingeter>> 是 [[2,3][3,4],[1,-3]]  output: [[2,3],[1,-3]]
    public List<List<Integer>> kClosest(List<List<Integer>> points, int K) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Node> heap = new PriorityQueue<>(K, Collections.<Node>reverseOrder());
        for (List<Integer> point: points) {
            int dis = (int)(Math.pow(point.get(0), 2) + Math.pow(point.get(1), 2));
            if (heap.size() < K) {
                heap.offer(new Node(point.get(0), point.get(1), dis));
            } else if (dis < heap.peek().val) {
                heap.poll();
                heap.offer(new Node(point.get(0), point.get(1), dis));
            }
        }
        while (!heap.isEmpty()) {
            Node node = heap.poll();
            List<Integer> cur = new ArrayList<>();
            cur.add(node.x);
            cur.add(node.y);
            res.add(cur);
        }
        return res;
    }
 
 
    // 5. robot removes 障碍
    // 9 is obstacle, 1 flat lot can move, 0 trenches can't move. Find the minimum val to reach obstacle in order to remove it
    // e.g.
    //[1,1,1]
    //[1,0,0]
    //[1,9,1]
    //Output: 3
    public int minSteps(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix[0][0] == 0) {
            return -1;
        }
        if (matrix[0][0] == 9) {
            return 0;
        }
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 0, 0));
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            for (Node nei: getNeighbors(queue.poll(), matrix, visited)) {
                if (matrix[nei.x][nei.y] == 9) {
                    return nei.val;
                }
                queue.offer(nei);
                visited[nei.x][nei.y] = true;
            }
        }
        return -1;
    }
    private List<Node> getNeighbors(Node node, int[][] matrix, boolean[][] visited) {
        List<Node> neis = new ArrayList<>();
        int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] d: dir) {
            Node nei = new Node(node.x + d[0], node.y + d[1], node.val + 1);
            if (nei.x < 0 || nei.x >= matrix.length || nei.y < 0 || nei.y >= matrix[0].length
                    || visited[nei.x][nei.y] || matrix[nei.x][nei.y] == 0) {
                continue;
            }
            neis.add(nei);
        }
        return neis;
    }

    class Node implements Comparable<Node> {
        int x;
        int y;
        int val;
        public Node(int i, int j, int s) {
            x = i;
            y = j;
            val = s;
        }
 
        @Override
        public int compareTo(Node another) {
            if (val == another.val) {
                return 0;
            }
            return val < another.val ? -1: 1;
        }
    }
 
    // 6. (Two Sum Closest)
    // Two sum 的变种，回答位置，多个答案，选出包含最大数字的答案。
    // - 给两个array和一个K，每个array里面各选一个数，要求sum<=K的最大组
    // - 给一个去程route的list和一个回城route的list，还有一个来回路程的上限max。求来回路程最接近于max的route combination。
    // foreground and background program / forward and return flight 那道题暴力解的
    public List<Integer> solution(int target, List<Integer> foregroundSizes, List<Integer> backgroundSizes) { // O(n^2)
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);
        int max = 0;
        for (int i = 0; i < foregroundSizes.size(); i++) {
            for (int j = 0; j < backgroundSizes.size(); j++) {
                int cur = foregroundSizes.get(i) + backgroundSizes.get(j);
                if (cur <= target && cur > max) {
                    max = cur;
                    res.set(0, i);
                    res.set(1, j);
                }
            }
        }
        return max == 0 ? new ArrayList<>() : res;
    }
 
 
    // 7. 卡车装箱 (TWO SUM)
    // e.g. truck capacity - 90 / reserved capacity - 30 / paCkage capacities - [10, 25, 35, 60]
    // 要求返回 [1, 2] (25和35的索引)
    public List<Integer> solution(int trunkSpace, List<Integer> itemSpaces) { // O(n)
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < itemSpaces.size(); i++) {
            Integer index = map.get(trunkSpace - itemSpaces.get(i));
            if (index != null) {
                res.add(index);
                res.add(i);
                break;
            } else {
                map.put(itemSpaces.get(i), i);
            }
        }
        return res;
    }
 

    // 8. Partition label
    //https://leetcode.com/problems/partition-labels/
    public List<Integer> partitionLabels2(String S) { // O(n)
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i) {
            last[S.charAt(i) - 'a'] = i;
        }
        int start = 0;
        int end = 0;
        List<Integer> res = new ArrayList();
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }
 
}


