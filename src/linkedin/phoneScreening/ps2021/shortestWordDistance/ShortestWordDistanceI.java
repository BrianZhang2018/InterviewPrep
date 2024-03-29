package linkedin.phoneScreening.ps2021.shortestWordDistance;

public class ShortestWordDistanceI {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int index1 = -1, index2= -1, min = wordsDict.length;

        for(int i=0; i<wordsDict.length; i++) {
            if(word1.equals(wordsDict[i])) {
                index1 = i;
                if(index2!=-1) min = Math.min(min, index1-index2);
            }
            if(word2.equals(wordsDict[i])) {
                index2=i;
                if(index1 !=-1) min = Math.min(min, index2-index1);
            }
        }

        return min;
    }
}
