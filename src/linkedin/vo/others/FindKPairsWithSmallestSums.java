package linkedin.vo.others;

import java.util.*;
/**
 * the question can convert to find K smallest sum with two numbers from each array
 */
public class FindKPairsWithSmallestSums {

    public static void main(String[] args) {
       for(List<Integer> res :  kSmallestPairs1(new int[]{5,7,9},new int[]{1,2,3},  2))
           System.out.println(Arrays.asList(res));

        for(List<Integer> res :  kSmallestPairs1(new int[]{1,2,3},new int[]{5,7,9},  2))
            System.out.println(Arrays.asList(res));
    }

    // optimized solution - similar with Merge sorted array
    public static List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        List<List<Integer>> res = new ArrayList<>();

        if(nums1.length==0 || nums2.length==0 || k==0)
            return res;

        for(int i=0; i<nums1.length && i<k; i++) // i<k, since both array are sorted, so the K smallest sum pair must happen in first K num
            que.offer(new int[]{nums1[i], nums2[0], 0}); // the 0 is the index of Nums2 array. the logic here is  (loop nums1 array, nums2[0], 0)

        while(k-- > 0 && !que.isEmpty()){
            int[] cur = que.poll();
            res.add(Arrays.asList(cur[0], cur[1]));

            if(cur[2] == nums2.length-1) continue;// curr[2] is the index of nums2 array

            que.offer(new int[]{cur[0], nums2[cur[2]+1], cur[2]+1}); // opposite way of line-26, (nums1[0], loop nums2 array)
        }
        return res;
    }

    // intuition solution
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<int[]>();
        if(nums1 == null || nums1.length ==0 || nums2==null || nums2.length==0){
            return res;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                new Comparator<int[]>(){
                    public int compare(int[] a, int[] b){
                        return a[0]+a[1] - (b[0]+b[1]);
                    }
                }
        );
        for(int i=0; i<nums1.length; i++){
            for(int j=0; j<nums2.length; j++){
                pq.add(new int[]{nums1[i], nums2[j]});
            }
        }
        int size = k<=pq.size()? k : pq.size();
        for(int i=0; i< size; i++){
            res.add(pq.poll());
        }

        return res;
    }
}
