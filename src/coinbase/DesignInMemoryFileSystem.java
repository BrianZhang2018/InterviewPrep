package coinbase;

import java.time.Instant;
import java.util.*;

/**
 * https://leetcode.com/problems/design-in-memory-file-system/
 * CoinBase
 * Trie
 *
 * Created by brianzhang on 1/28/21.
 */
public class DesignInMemoryFileSystem {
    public static void main(String[] args) throws DirOrFileNotFound {
        DesignInMemoryFileSystem fs = new DesignInMemoryFileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        try{
            fs.addContentToFile("/a/b/c/d", "hello");
        }catch (DirOrFileNotFound ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(fs.ls("/"));

        try{
            System.out.println(fs.readContentFromFile("/a/b/c/d"));
        }catch (DirOrFileNotFound ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(Instant.now().getEpochSecond());
        System.out.println(System.currentTimeMillis() / 1000);
    }

    class TNode { // Tri Node, both directory and file are Node
        private TreeMap<String, TNode> children;
        private StringBuilder fileContent;
        private String name; // directory path or file name

        public TNode(String name) {
            children = new TreeMap<>();
            fileContent = new StringBuilder();
            this.name = name;
        }

        public String getContent(){
            return fileContent.toString();
        }

        public String getName(){
            return name;
        }

        public void addContent(String content){
            fileContent.append(content);
        }

        public boolean isFile(){
            return fileContent.length() > 0;
        }

        public List<String> getList() {
            List<String> list = new ArrayList<>();
            if(isFile()){
                list.add(getName());
            }else{
                list.addAll(children.keySet());
            }
            return list;
        }
    }

    private TNode root;

    public DesignInMemoryFileSystem() {
        root = new TNode("");
    }

    public List<String> ls(String path) throws DirOrFileNotFound {
        TNode target = findNode(path);
        if(target == null) {
            throw new DirOrFileNotFound("Path Not Found");
        }
        return target.getList();
    }

    public void mkdir(String path) {
        findNode(path);
    }

    public void addContentToFile(String filePath, String content) throws DirOrFileNotFound {
        String[] ps = filePath.split("/");
        TNode curr = root;
        for(int i=1; i<ps.length; i++) {
            if(!curr.children.containsKey(ps[i]) && i != ps.length-2) {
                throw new DirOrFileNotFound("Path not found");
            }
            curr = curr.children.get(ps[i]);
        }
        curr.addContent(content);
    }

    public String readContentFromFile(String filePath) throws DirOrFileNotFound{
        TNode target = findNode(filePath);
        if(target == null)  throw new DirOrFileNotFound("Path Not Found");

        return target.getContent();
    }

    private TNode findNode(String path) { // find path and file
        String[] paths = path.split("/");
        TNode cur = root;
        for(String p : paths){
            if(p.equals("")) continue; // skip the first "" in paths
            cur.children.putIfAbsent(p, new TNode(p));
            cur = cur.children.get(p);

            if(cur.isFile()) break; // if it's file, we stop as it's end of this path, we can't go further.
        }

        return cur;
    }
}

class DirOrFileNotFound extends Exception {
    public DirOrFileNotFound(String errorMsg) {
        super(errorMsg);
    }
}


/*
Post api/v1/file/mkdir
     {clientId, path}

Post api/v1/file/write
     {clientId, path, content}

Get api/v1/file/read?path=a%2Fb%2Fc
     {clientId, path}
 */