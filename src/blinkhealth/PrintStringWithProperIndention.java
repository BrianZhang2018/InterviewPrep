package blinkhealth;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=642813&page=1&extra=#pid15459974
 *
 * Created by brianzhang on 5/9/21.
 */
public class PrintStringWithProperIndention {

    public static void main(String[] args) {
        System.out.println(" ".charAt(0) - 'a');
        PrintStringWithProperIndention test = new PrintStringWithProperIndention();
        test.solution(new String[]{"/home/a/b/c/d", "/secondhome/a/b/e", "/home/a/b/f/g"});
    }

    public void solution(String[] array){
        // build out the Trie tree
        Trie head = new Trie("");
        for(String str : array){
            Trie root = head;
            String[] sa = str.split("/");
            for(int i=1; i<sa.length; i++){
                int index = sa[i].charAt(0) - 'a';
                if(root.children[index] == null) {
                    Trie t = new Trie(sa[i]);
                    root.children[index] = t;
                }
                root= root.children[index];
            }
        }

        printTrieDfs(head.children, 0);
    }

    // wawawa, had a little struggle when write this recursion method, shoot.
    public void printTrieDfs(Trie[] nodes, int indention){
        for(int i=0; i<nodes.length; i++){
            if(nodes[i] != null){
                for(int j=0; j<indention; j++)
                    System.out.print(" ");
                System.out.println(nodes[i].str);
                printTrieDfs(nodes[i].children, indention + 1);
            }
        }
    }
}

class Trie {
    Trie[] children = new Trie[26];
    boolean isRoot;
    String str;
    String value;

    public Trie(String str){
        this.str = str;
    }
}
