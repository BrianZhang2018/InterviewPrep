package companies.braze;

import java.util.*;

public class InitialPublicOffer{
    public static void main(String[] args) {

        List<List<Integer>> bids = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.addAll(Arrays.asList(new Integer[] {1,2,5,11}));
        List<Integer> l2 = new ArrayList<>();
        l2.addAll(Arrays.asList(new Integer[] {2,1,4,2}));
        List<Integer> l3 = new ArrayList<>();
        l3.addAll(Arrays.asList(new Integer[] {3,1,5,5}));
/*        List<Integer> l4 = new ArrayList<>();
        l4.addAll(Arrays.asList(new Integer[] {1,2,5,11}));*/
        bids.add(l1); bids.add(l2); bids.add(l3);

        InitialPublicOffer test = new InitialPublicOffer();
        List<Integer> res = test.getUnLotteryUsers(bids, 3);
        for (Integer i : res) {
            System.out.println(i);
        }
    }

    public List<Integer> getUnLotteryUsers(List<List<Integer>> bids, int totalShares){
        List<Bid> lBid = new ArrayList<>();
        for(List<Integer> bid : bids){
            lBid.add(new Bid(bid.get(0), bid.get(1), bid.get(2), bid.get(3)));
        }
        Collections.sort(lBid);

        // for(Bid bid : lBid){
        //     System.out.println(bid.toString());
        // }

        TreeMap<Integer, List<Bid>> map = new TreeMap<>(Collections.reverseOrder());
        for(Bid bid : lBid){
            List<Bid> l = map.getOrDefault(bid.p, new ArrayList<Bid>());
            l.add(bid);
            map.put(bid.p, l);
        }

        for(Map.Entry<Integer, List<Bid>> entry : map.entrySet()){
            if(totalShares <= 0)
                break;

            List<Bid> lb = entry.getValue();
            if(lb.size() > 1){
                while(true){
                    for(int i=0; i<lb.size(); i++){
                        Bid bid = lb.get(i);
                        bid.gotStock = true;
                        if(bid.sc != 0){
                            bid.sc--;
                            totalShares--;
                        }
                    }
                    if(totalShares <= 0)
                        break;
                    int p = 0;
                    for(Bid bid : lb){
                        p+=bid.sc;
                    }
                    if(p == 0)
                        break;
                }
            }else if(lb.size() == 1){
                totalShares -= lb.get(0).sc;
                lb.get(0).gotStock=true;
                lb.get(0).sc = 0;
            }
        }

        List<Integer> res = new ArrayList<>();
        for(Bid bid : lBid){
            if(!bid.gotStock){
                res.add(bid.u);
            }
        }
        return res;
    }
}

class Bid implements Comparable<Bid>{
    public int u;
    public int sc;
    public int p;
    public int t;
    public boolean gotStock = false;

    public Bid(int u, int sc, int p, int t){
        this.u = u;
        this.sc = sc;
        this.t = t;
        this.p = p;
    }

    public int getP() {
        return p;
    }

    public int getT() {
        return t;
    }

    //compare multiple fields in Java 8
    @Override
    public int compareTo(Bid other) {
        return Comparator.comparing(Bid::getP).reversed()
                .thenComparing(Bid::getT)
                .compare(this, other);
    }

    @Override
    public String toString(){
        return u + "  " + sc + "  " + p +"  " + t;
    }
}