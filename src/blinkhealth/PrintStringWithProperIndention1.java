package blinkhealth;

/**
 * Prefix tree - trie
 *
 * Created by brianzhang on 5/10/21.
 */
public class PrintStringWithProperIndention1 {

    public static void main(String[] args) {
        String[] input = new String[]{"/home/anti-depressants/xanax", "/drugs/topical", "/home/heart/lipitor", "/home/heart/atorvastatin", "/drugs/routes/oral/tablets", "/drugs/routes/nasal/flonase", "/home/heart/lipitor", "/home/blood/sweat/tears", "/drugs/nasal/flonase"};
        solution(input);
    }

    public static void solution(String[] arr){
        // build out the trie tree
        Trie head = new Trie("");
        for(String s : arr){
            Trie root = head;
            String[] strs = s.split("/");
            for(int i=1; i<strs.length; i++){
                int j = strs[i].charAt(0) - 'a';
                if(root.children[j] == null) {
                    root.children[j] = new Trie(strs[i]);
                }else{
                    if(!root.children[j].str.equals(strs[i])){
                        root.children[j] = new Trie(strs[i]);
                    }
                }

                root = root.children[j];
            }
        }

        printTrie(head.children, 0);
    }

    private static void printTrie(Trie[] children, int indention){

        for(int i=0; i<children.length; i++){
            if(children[i] != null){
                for(int j=0;j<indention;j++) System.out.print(" ");

                System.out.println(children[i].str);
                printTrie(children[i].children, indention + 1);
            }
        }
    }
}
