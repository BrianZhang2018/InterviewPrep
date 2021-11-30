package square.myOnsite;

import java.util.*;
/**
 * Created by brianzhang on 4/20/21.
 */
public class MultiColorStrip {

    public static void main(String[] args) {
        SegmentStrip s1 = new SegmentStrip("blue", 0.12);
        SegmentStrip s2 = new SegmentStrip("red", 0.38);
        SegmentStrip s3 = new SegmentStrip("yellow", 0.50);

        SegmentStrip s4 = new SegmentStrip("blue", 0.12);
        SegmentStrip s5 = new SegmentStrip("pink", 0.38);
        SegmentStrip s6 = new SegmentStrip("white", 0.50);

        StripPaper sp = new StripPaper(Arrays.asList(s1, s2, s3));
        sp.printout();
        StripPaper sp1 = new StripPaper(Arrays.asList(s4, s5, s6));
        sp1.printout();

        System.out.println("=====================================");

        for(StripPaper sps : sp.swapStripPaper(sp1, 0.2)) {
            sps.printout();
        }
    }
}

class StripPaper {

    List<SegmentStrip> list = new ArrayList();

    public StripPaper(List<SegmentStrip> ss){
        this.list = ss;
    }

    public void printout(){
        if(list.size() != 0) {
            for(SegmentStrip ss : list) {
                System.out.println(ss.color + " , " + ss.size);
            }
            System.out.println();
        }
    }

    public List<StripPaper> swapStripPaper(StripPaper sp, double cutPoint) {
        List<SegmentStrip> left = new ArrayList<>();
        List<SegmentStrip> right = new ArrayList<>();
        List<SegmentStrip> left1 = new ArrayList<>();
        List<SegmentStrip> right1 = new ArrayList<>();

        cutPaper(this, cutPoint, left, right);
        cutPaper(sp, cutPoint, left1, right1);

        left.addAll(right1);
        left1.addAll(right);

        return Arrays.asList(new StripPaper(left), new StripPaper(left1));
    }


    private void cutPaper(StripPaper paper, double cutPoint ,List<SegmentStrip> left, List<SegmentStrip> right){
        double ac = 0;
        List<SegmentStrip> paperStrips = paper.list;
        for(int i=0; i<paperStrips.size(); i++) {
            SegmentStrip ss = paperStrips.get(i);
            ac += ss.size;
            if(ac >= cutPoint) {
                if(ac == cutPoint){
                    left.add(ss);
                    right.addAll(paperStrips.subList(i+1, paperStrips.size()));
                }else{
                    right.add(new SegmentStrip(ss.color, ac-cutPoint));
                    right.addAll(paperStrips.subList(i+1, paperStrips.size()));
                    left.add(new SegmentStrip(ss.color, ss.size-(ac-cutPoint)));
                }
                break;
            }

            left.add(paper.list.get(i));
        }
    }

}

class SegmentStrip {
    String color;
    double size;

    public SegmentStrip(String color, double size){
        this.color = color;
        this.size = size;
    }
}
