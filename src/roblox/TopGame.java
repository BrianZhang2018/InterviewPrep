package roblox;

/*
We want to know what the Top Game is, defined by: The Top Game is the game users spent the most time in.
Each line of the file has the following information (comma separated):
- timestamp in seconds (long)
- user id (string)
- game id (int)
- action (string, either "join" or "quit")
e.g.
[
"1000000000,user1,1001,join", // user 1 joined game 1001
"1000000005,user2,1002,join", // user 2 joined game 1002
"1000000010,user1,1001,quit", // user 1 quit game 1001 after 10 seconds
"1000000020,user2,1002,quit", // user 2 quit game 1002 after 15 seconds
];
In this log,
The total time spent in game 1001 is 10 seconds.
The total time spent in game 1002 is 15 seconds.
Hence game 1002 is the Top Game. -> 1002

This file could be missing some lines of data (e.g. the user joined, but then the app crashed).
If data for a session (join to quit) is incomplete, please discard the session.
To recover some data, we attempt to estimate session length with this rule:
  If a user joined a game but did not leave, assume they spent the minimum of
    - time spent before they joined a different game; and
    - average time spent across the same user's gaming sessions (from join to leave)
    e.g.
    "1500000000,user1,1001,join"
    "1500000010,user1,1002,join"
    "1500000015,user1,1002,quit"
    The user spent 5 seconds in game 2, so we assume they spent 5 seconds in game 1.
Write a function that returns the top game ID, given an array of strings representing
each line in the log file.
*/

import java.util.*;

public class TopGame {

    public static void main(String[] args) {

       String res = getTopGame(new String[] {
                "1000000000,user1,1001,join",
                "1000000005,user2,1002,join",
                "1000000006,user1,1001,join",
                "1000000020,user1,1001,quit",
                "1000000020,user2,1002,quit",
                "1000000020,user2,1002,quit"
        });

        System.out.println(res);
    }

    public static String getTopGame(String[] records) {
        // userId+"quit" : joinTime_gameId
        Map<String, String> track = new HashMap<>();
        // gameId : time sum
        Map<String, Long> gameTimeCount = new TreeMap<>();
        // follow-up question
        // userId : collection of gameTime spent
        Map<String, List<Long>> userGameTimeCount = new HashMap<>();
        // userId+","+gameId : count
        Map<String, Integer> missing = new HashMap<>();

        long maxTimeSpent = 0;
        String topGame = null;

        for(String r : records) {
            String[] strs = r.split(",");
            String currTimeStamp = strs[0], userId = strs[1], gameId = strs[2], status=strs[3];
            if(status.equals("join")) {

                String key = userId + "quit";
                if(track.containsKey(key)) { // missing quit event
                    missing.put(userId+","+gameId, missing.getOrDefault(userId, 0) + 1);
                }
                track.put(key, currTimeStamp+","+gameId); // add a new event or override the previously crash join

            }else if(status.equals("quit")) {

                if(!track.containsKey(userId + "quit")) { // missing enter event
                    missing.put(userId+","+gameId, missing.getOrDefault(userId, 0) +1);
                }else{
                    String[] joinTime_GameId = track.get(userId+"quit").split(",");
                    long gameTimeSpent = gameTimeCount.getOrDefault(gameId, 0l) + Long.valueOf(currTimeStamp) - Long.valueOf(joinTime_GameId[0]);
                    gameTimeCount.put(gameId, gameTimeSpent);
                    if(gameTimeSpent > maxTimeSpent) {
                        topGame = gameId;
                        maxTimeSpent = gameTimeSpent;
                    }

                    // count each user session time
                    userGameTimeCount.putIfAbsent(userId, new ArrayList<>());
                    userGameTimeCount.get(userId).add(gameTimeSpent);

                    // clear the join event
                    track.remove(userId+"quit");
                }

            }
        }

        for(Map.Entry<String, Integer> entry : missing.entrySet()) {
            String[] userId_gameId = entry.getKey().split(",");
            long missingSpentTime = getAverageTime(userGameTimeCount.get(userId_gameId[0])) * entry.getValue();
            long totalSpentTime = gameTimeCount.getOrDefault(userId_gameId[1], 0l) + missingSpentTime;
            gameTimeCount.put(userId_gameId[1], totalSpentTime);
            if(totalSpentTime > maxTimeSpent) {
                topGame = userId_gameId[1];
                maxTimeSpent = totalSpentTime;
            }
        }

        return topGame;
    }

    private static long getAverageTime(List<Long> list) {
        int sum = 0;
        for(long i : list) {
            sum+=i;
        }
        return sum/list.size();
    }

}