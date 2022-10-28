package airbnb;
import java.util.*;
/*
Given the following input:
Articles = [
  "This is a short article.",
  "Now for a longer article. This one has a lot of text.",
  "Another article with medium length."
]
width = 55
Output the following to the console:
+-------------------------------------------------------+
|This is a short article.                               |
+-------------------------------------------------------+
|Now for a longer article. This one has a lot of text.  |
+-------------------------------------------------------+
|Another article with medium length.                    |
+-------------------------------------------------------+

width = 12
+------------+
|This is a   |
|short       |
|article.    |
+------------+
|Now for a   |
|longer      |
|article.    |
|This one has|
|a lot of    |
|text.       |
+------------+
|Another     |
|article with|
|medium      |
|length.     |
+------------+
*/

public class FormatOutput {
    static class Article {
        String text;
        Article(String text) {
            this.text = text;
        }
    }
    static List<Article> articles = Arrays.asList(
            new Article("This is a short article."),
            new Article("Now for a longer article. This one has a lot of text."),
            new Article("Another article with medium length.")
    );
    static int width = 12;
    public static void main(String[] args) {
        printOut(articles);
    }

    public static void printOut(List<Article> input) {
        if(input == null || input.size() == 0) return;
        printSeparateLine(width);
        for(Article a : input) {
            String text = a.text;
            if(text.length() <= width) {
                printOutContent(text);
            } else {
                List<String> list = new ArrayList<>();
                for(String s : text.split(" ")) {
                    list.add(s);
                }
                helper(list);
            }
            printSeparateLine(width);
        }
    }

    public static void helper(List<String> list) {
        String output="";
        for(int i=0; i<list.size(); i++) {
            String str = list.get(i);
            if((output + str).length() <= width) {
                if((output + str).length() == width) {
                    output += str;
                } else {
                    output += str + " ";
                }
            } else {
                printOutContent(output);
                output = str + " ";
            }
        }
        if(!output.isEmpty()) {
            printOutContent(output);
        }
    }

/* no need dfs
    public static void dfs(List<String> list) {
        String output="";
        for(int i=0; i<list.size(); i++) {
            String str = list.get(i);
            if((output + str).length() <= width) {
                if((output + str).length() == width) {
                    output += str;
                } else {
                    output += str + " ";
                }
            } else {
                printOutContent(output);
                dfs(list.subList(i, list.size()));
                return;
            }
        }
        if(!output.isEmpty()) {
            printOutContent(output);
        }
    }
*/

    public static void printOutContent(String output) {
        System.out.print("|" + output);
        for (int j = 0; j < width - output.length(); j++) System.out.print(" ");
        System.out.println("|");
    }

    public static void printSeparateLine(int length) {
        char[] cs = new char[length];
        Arrays.fill(cs, '-');
        System.out.println("+" + new String(cs) + "+");
    }
}