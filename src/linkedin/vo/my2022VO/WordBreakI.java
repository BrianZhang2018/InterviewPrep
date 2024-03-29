package linkedin.vo.my2022VO;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 *
 * time complexity: 2^n, https://leetcode.com/problems/word-break/discuss/169383/solved-The-Time-Complexity-of-The-Brute-Force-Method-Should-Be-O(2n)-and-Prove-It-Below
 * Created by brianzhang on 1/9/19.
 */
public class WordBreakI {
    public static void main(String[] args) {
        //System.out.println(wordBreak("leetco", new HashSet<>(Arrays.asList("leet", "co"))));
        System.out.println(wordBreakDFS("goalspecial",  new HashSet<>(Arrays.asList("go","goal","goals","special"))));
        res.forEach(s -> System.out.println(s));
        // System.out.println(wordBreakDFS("catsandog", new HashSet<>(Arrays.asList("cats","dog","sand","and","cat"))));
    }

    // Solution-1: DP递推, O(n^2) ("n square" Or "n to the power 2") - Bottom-Up solution
    public static boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return false;
        int n = s.length();
        boolean[] dp = new boolean[n]; // dp[i] represents whether s[0...i] can be formed by dict

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (dict.contains(s.substring(j, i + 1)) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break; // remove this, also works
                }
            }
        }
        return dp[n - 1];
    }

    // Solution-2: DFS (记忆化递归), time complexity: n^2, Top-Down solution
    public static boolean wordBreakDFS(String s, Set<String> wordDict) {
        //return wordBreakDFSMemo(s, wordDict, new HashMap());
        return wordBreakDFSMemo1(s, 0, wordDict, new HashMap());
    }

    static List<String> res = new ArrayList<>(); // can add this to print out the result
    public static boolean wordBreakDFSMemo(String s, Set<String> dict, Map<String, Boolean> memo) {
        if (memo.containsKey(s)) return memo.get(s);

        if (dict.contains(s)) {
            res.add(s);
            memo.put(s, true);
            return true;
        }
        for (int i = 1; i < s.length(); i++) {
            // For '&&' condition, if 'dict.contains(s.substring(0, i))' is false, won't run the 'wordBreak(s.substring(i), dict, memo)'
            if (dict.contains(s.substring(0, i)) && wordBreakDFSMemo(s.substring(i), dict, memo)) {
                res.add(s.substring(0, i));
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        return false;
    }

    // Good example to demonstrate how memo works which record the sub-result status
    public static boolean wordBreakDFSMemo1(String s, int start, Set<String> dict,
                                            Map<String, Boolean> memo) {
        if(start == s.length()) return true;
        if (memo.containsKey(s)) return memo.get(s);

        if (dict.contains(s)) {
            memo.put(s, true);
            return true;
        }
        for (int i = start; i < s.length(); i++) {
            String str = s.substring(start, i + 1);
            if (dict.contains(str) && wordBreakDFSMemo1(s, i + 1, dict, memo)) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s.substring(start), false); // memorize the substring status, not original string
        return false;
    }

    // Solution-3: BFS, https://leetcode.com/problems/word-break/discuss/43797/A-solution-using-BFS
    public static boolean wordBreakBFSMemo(String s, Set<String> wordDict) {
        if (wordDict.contains(s)) return true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        // use a set to record checked index to avoid repeated work. This is the key to reduce the running time to O(N^2).
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            for (int i = curIdx+1; i <= s.length(); i++) {
                if (visited.contains(i)) continue;
                if (wordDict.contains(s.substring(curIdx, i))) {
                    if (i == s.length()) return true;
                    queue.offer(i);
                    visited.add(i);
                }
            }
        }
        return false;
    }

    // best performance solution from leetcode
    class Solution {
        // DFS
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] visited = new boolean[s.length() + 1];
            return dfs(s, 0, wordDict, visited);
        }

        private boolean dfs(String s, int start, List<String> wordDict,
                            boolean[] visited) {
            for (String word : wordDict) {
                int nextStart = start + word.length();
                if (nextStart > s.length() || visited[nextStart]) continue;

                if (s.indexOf(word, start) == start) {
                    if (nextStart == s.length() || dfs(s, nextStart, wordDict, visited)) {
                        return true;
                    }
                    visited[nextStart] = true;
                }
            }
            return false;
        }
    }
}
