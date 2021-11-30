package box;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;
/**
 * Created by brianzhang on 9/27/20.
 */
public class TraverseFolder {


    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main (String args[]) throws IOException {
        displayIt(new File("/Users/brianzhang/workspace/InterviewPrep/src/box/numberTest"));
        for(int i : getTopK(3)) System.out.println(i);
    }

    public static void displayIt(File node) throws IOException {
        System.out.println(node.getAbsoluteFile());

        if(node.isDirectory()){
            String[] subNote = node.list();
            for(String filename : subNote){
                displayIt(new File(node, filename));
            }
        }else{
           String content = new String(Files.readAllBytes(Paths.get(node.getAbsolutePath())));
           for(Integer i : extractNumber(content)){
               pq.offer(i);
           }
        }
    }

    public static List<Integer> getTopK(int k){
        List<Integer> res = new ArrayList<>();
        while(k-->0){
            res.add(pq.poll());
        }
        return res;
    }

    public static List<Integer> extractNumber(String str){
        List<Integer> res = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(str);
        while(m.find()) {
            res.add(Integer.valueOf(m.group()));
        }
        return res;
    }
}
