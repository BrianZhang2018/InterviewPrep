package wayfair.karat;

import java.util.*;

/*
We are working on a security system for a badged-access room in our company's building.

Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:

1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)

2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)

Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.

badge_records_1 = [
  ["Martha",   "exit"],
  ["Paul",     "enter"],
  ["Martha",   "enter"],
  ["Steve",    "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "enter"],
  ["Paul",     "enter"],
  ["Curtis",   "exit"],
  ["Curtis",   "enter"],
  ["Paul",     "exit"],
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "exit"],
  ["Paul",     "enter"],
  ["Paul",     "enter"],
  ["Martha",   "exit"],
  ["Paul",     "enter"],
  ["Paul",     "enter"],
  ["Paul",     "exit"],
  ["Paul",     "exit"]
]

Expected output: ["Paul", "Curtis", "Steve"], ["Martha", "Curtis", "Paul"]

Other test cases:

badge_records_2 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
]

Expected output: [], []

badge_records_3 = [
  ["Paul", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
]

Expected output: ["Paul"], ["Paul"]

badge_records_4 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
  ["Paul", "enter"],
]

Expected output: ["Paul"], ["Paul"]

n: length of the badge records array
*/

public class Solution {
    public static void main(String[] argv) {
        String badgeRecords1[][] = new String[][] {
                {"Martha",   "exit"},
                {"Paul",     "enter"},
                {"Martha",   "enter"},
                {"Steve",    "enter"},
                {"Martha",   "exit"},
                {"Jennifer", "enter"},
                {"Paul",     "enter"},
                {"Curtis",   "exit"},
                {"Curtis",   "enter"},
                {"Paul",     "exit"},
                {"Martha",   "enter"},
                {"Martha",   "exit"},
                {"Jennifer", "exit"},
                {"Paul",     "enter"},
                {"Paul",     "enter"},
                {"Martha",   "exit"},
                {"Paul",     "enter"},
                {"Paul",     "enter"},
                {"Paul",     "exit"},
                {"Paul",     "exit"}
        };

        String badgeRecords2[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "exit"},
        };

        String badgeRecords3[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
        };

        String badgeRecords4[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Dong", "exit"},
                {"Dong", "enter"},
        };

        for(List<String> l : solution(badgeRecords3))
            System.out.println(Arrays.toString(l.toArray()));
    }

    public static List<List<String>> solution(String[][] input) {
        if(input == null || input.length == 0) return null;

        Stack<String> stack = new Stack();
        Set<String> missingEnter = new HashSet();
        Set<String> missingExit = new HashSet();

        for(String[] s : input) {
            if(s[1].equals("exit")) {
                if(stack.contains(s[0] + ":" +s[1])) {
                    stack.remove(s[0] + ":" + "exit");
                }else{
                    missingEnter.add(s[0]);
                }
            }else if(s[1].equals("enter")) {
                if(stack.contains(s[0] + ":exit")) {
                    missingExit.add(s[0]);
                }else{
                    stack.push(s[0] + ":exit");
                }
            }
        }

        if(!stack.isEmpty()) {
            for(String i : stack) {
                missingExit.add(i.split(":")[0]);
            }
        }

        return Arrays.asList(new ArrayList(missingExit), new ArrayList(missingEnter));
    }
}

