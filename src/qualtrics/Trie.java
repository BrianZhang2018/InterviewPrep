package qualtrics;

/**
 * Created by brianzhang on 1/17/21.
 */
public class Trie {
    Trie[] children = new Trie[26];
    char c;

    public Trie(char c){
        this.c = c;
    }

    public Trie build(String str){
        Trie root = new Trie(' ');

        for(char c : str.toCharArray()){
            if(root.children[c] == null){
                root.children[c] = new Trie(c);
            }

            root = root.children[c];
        }

        return root;
    }



}


