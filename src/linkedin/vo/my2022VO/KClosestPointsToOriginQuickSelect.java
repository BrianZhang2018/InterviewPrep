package linkedin.vo.my2022VO;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin
 * QuickSort, tc: O(Nlog(N)) --> https://iq.opengenus.org/time-and-space-complexity-of-quick-sort/
 *
 * Created by brianzhang on 5/27/20.
 */
public class KClosestPointsToOriginQuickSelect {

    public static void main(String[] args) {
        KClosestPointsToOriginQuickSelect test = new KClosestPointsToOriginQuickSelect();
        for(int[] res : test.kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2))
            System.out.println(Arrays.toString(res));
    }

    public int[][] kClosest(int[][] points, int k) {
        if (k >= points.length) return points;
        quickSort(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private void quickSort(int[][] A, int start, int end, int k) {
        if (start >= end) return;

        int left = start, right = end;
        // key point 1: pivot is the value, not the index
        int pivot = distance(A[(start + end) / 2]);
        // key point 2: every time you compare left & right, it should be left <= right not left < right
        while (left <= right) {
            while (left <= right && distance(A[left]) < pivot) {
                left++;
            }
            while (left <= right && distance(A[right]) > pivot) {
                right--;
            }
            if (left <= right) {
                int[] temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }
        if(left > k){
            quickSort(A, start, right, k);
        }else if(left < k) {
            quickSort(A, left, end, k);
        }
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
