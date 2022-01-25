package tableau;

import java.util.*;

/**
 * 可以用堆做到 O(N log k) 的时间复杂度.
 * 初始将所有数组的首个元素入堆, 并记录入堆的元素是属于哪个数组的.
 * 每次取出堆顶元素, 并放入该元素所在数组的下一个元素.
 */
class ArrayBox implements Comparable<ArrayBox> {
    int[] arr;
    int index;
    public ArrayBox(int[] arr, int index) {
        this.arr = arr;
        this.index = index;
    }
    @Override
    public int compareTo(ArrayBox o) {
        return this.arr[this.index] - o.arr[o.index];
    }
}

public class MergeKSortedArray {

    public static int[] mergeKSortedArray(int[][] arr) {
        PriorityQueue<ArrayBox> queue = new PriorityQueue<ArrayBox>();
        int total=0;
        //add arrays to heap
        for (int i = 0; i < arr.length; i++) {
            queue.add(new ArrayBox(arr[i], 0));
            total = total + arr[i].length;
        }

        int m=0;
        int result[] = new int[total];
        while(!queue.isEmpty()){
            ArrayBox ab = queue.poll();
            result[m++]=ab.arr[ab.index];
            if(ab.index < ab.arr.length-1){
                queue.add(new ArrayBox(ab.arr, ab.index+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 3, 5, 7 };
        int[] arr2 = { 2, 4, 6, 8 };
        int[] arr3 = { 0, 9, 10, 11 };

        int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
        System.out.println(Arrays.toString(result));
    }
}