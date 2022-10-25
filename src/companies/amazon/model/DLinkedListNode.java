package companies.amazon.model;

/**
 * Created by brianzhang on 7/23/18.
 */
public class DLinkedListNode {

    public int key;
    public int value;
    public DLinkedListNode left;
    public DLinkedListNode right;

    public DLinkedListNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}
