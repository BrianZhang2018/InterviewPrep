package datadog;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=744203&page=1&extra=#pid15467358
 *
 * 1. build graph
 * 2. bfs
 * Created by brianzhang on 5/11/21.
 */
public class DurationOfFinishAllRelatedJobs {

    public static void main(String[] args) {
        String[][] input = new String[][]{{"1", "1", "2,3"},{"2", "2", "4,5"},{"3", "3", "6,7"}};
        System.out.println(solutionBFS(input, "1"));
        System.out.println(solutionDFS(input, "1"));
    }

    // BFS solution
    public static int solutionBFS(String[][] jobs, String jobId){
        Map<String, Map<String, String>> map = new HashMap<>(); // parent job : (duration, children jobs)
        for(String[] job : jobs){
            map.putIfAbsent(job[0], new HashMap<>());
            map.get(job[0]).put(job[1], job[2]);
        }

        int duration = 0;
        Queue<String> q = new LinkedList<>();
        q.add(jobId);
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                String jd = q.poll();
                if(map.containsKey(jd)){
                    for(Map.Entry<String, String> entry : map.get(jd).entrySet()){
                        duration += Integer.valueOf(entry.getKey());
                        q.addAll(Arrays.asList(entry.getValue().split(",")));
                    }
                }
            }
        }

        return duration;
    }

    // DFS solution
    public static int solutionDFS(String[][] jobs, String jobId){
        Map<String, Map<String, String>> map = new HashMap<>();
        for(String[] job : jobs){
            map.putIfAbsent(job[0], new HashMap<>());
            map.get(job[0]).put(job[1], job[2]);
        }

        return  dfs(jobId, map);
    }

    public static int dfs(String jobId, Map<String, Map<String, String>> map){

        if(map.containsKey(jobId)){
            for(Map.Entry<String, String> entry : map.get(jobId).entrySet()){
                int childrenDuration = 0;
                for(String jd : entry.getValue().split(",")){
                    childrenDuration += dfs(jd, map);
                }

                return Integer.valueOf(entry.getKey()) + childrenDuration;
            }
        }

        return 0;
    }
}
