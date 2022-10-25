package companies.teradata;

import java.util.Arrays;

/**
 * 2 servers,
 * S1:j1, j2
 * S2: j3, j4

 * s1: 6min
 * s2: 12min
 * Difference: 6min

 * s1: j1, j2, j3 : 8min
 * s2: j4: 10min
 * Difference: 2min

 * n number of jobs: [1, 5, 2, 10]
                     j1  j2  j3  j4

 * input: we have 2 servers, n number of jobs

 * result: Array list of jobs for each server
 * [j1, j2, j3] [j4]

 * 1, 20, 20

 * Created by brianzhang on 7/9/20.
 */
public class ServerScheduler {

    public String[] scheduler(int[] jobs, int numsOfServers) {
        if (jobs == null || jobs.length == 0) return null;

        String[] servers = new String[numsOfServers];
        Arrays.sort(jobs);

        int sum = 0, maxSingerServerUnits;
        for (int i : jobs) sum += i;

        maxSingerServerUnits = sum / numsOfServers;

        int currSum = 0, index = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = jobs.length - 1; i >= 0; i--) {
            currSum += jobs[i];
            sb.append(String.valueOf(jobs[i]));

            if (currSum > maxSingerServerUnits && i >= 0) {
                servers[index++] = sb.toString();
                sb = new StringBuilder();
                currSum = 0;
            }
        }

        return servers;
    }
}
// 弄清楚问题

// 从数据处理入手
// 慢慢的加上思路

