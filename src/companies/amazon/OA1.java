package companies.amazon;

import java.util.*;

/**
 * Created by brianzhang on 7/1/18.
 */
public class OA1 {

    public List<String> reorderLines(int logFileSize, List<String> logLines) {
        List<String[]> wordList = new ArrayList<>();

        List<String[]> digitList = new ArrayList<>();
        int count = 0;
        for (String line : logLines) {
            if (count > logFileSize) {
                break;
            }
            String[] log = line.split(" ", 2);
            if (Character.isDigit(log[1].toCharArray()[0])) {
                digitList.add(log);
            } else {
                wordList.add(log);
            }
        }
        Collections.sort(wordList, (a, b) -> {
            int result = a[1].compareToIgnoreCase(b[1]);
            if (result == 0) {
                return a[0].compareTo(b[0]);
            } else {
                return result;
            }
        });
        List<String> result = new ArrayList<>();
        for (String[] log : wordList) {
            result.add(String.join(" ", log));
        }
        for (String[] log : digitList) {
            result.add(String.join(" ", log));
        }
        return result;
    }

    public List<String> mostFrequentlyUsedWords(String text, List<String> excludes) {
//String[] splitedText = text.split("\\p{Punct}|\\s");
        String[] splitedText = text.split("\\W");
        Set<String> excludesSet = new HashSet<>();
        for (String word : excludes) {
            excludesSet.add(word.toLowerCase());
        }
        Map<String, Integer> countMap = new HashMap<>();
        for (String s : splitedText) {
            String lowerCase = s.toLowerCase();
            if (!lowerCase.trim().isEmpty() && !excludesSet.contains(lowerCase)) {
                countMap.put(lowerCase, countMap.getOrDefault(lowerCase, 0) + 1);
        }
        }
        List<String> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            int value = entry.getValue();
            String key = entry.getKey();
            if (value > max) {
                max = value;
                result.clear();
                result.add(key);
            } else if (value == max) {
                result.add(key);
            }
        }

        return result;


    }
}
