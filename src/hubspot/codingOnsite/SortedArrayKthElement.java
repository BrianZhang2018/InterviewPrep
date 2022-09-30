package hubspot.codingOnsite;

public class SortedArrayKthElement {
    public static void main(String[] args) {
        int arr1[] = {2, 3, 6, 7, 9};
        int arr2[] = {1, 4, 8, 10};
        int k = 5;
        System.out.print(helper(arr1, arr2, k));
    }

    public static int helper(int[] arr1, int[] arr2, int k) {
        if (arr1.length + arr2.length < k) return -1;

        int[] sortedArr;
        if (arr1.length + arr2.length < k) {
            sortedArr = new int[arr1.length + arr2.length];
        } else {
            sortedArr = new int[k];
        }

        int p1 = 0, p2 = 0, count = 0;
        while (p1 < arr1.length || p2 < arr2.length) {
            if (p1 == arr1.length) {
                sortedArr[count++] = arr2[p2++];
            } else if (p2 == arr2.length) {
                sortedArr[count++] = arr1[p1++];
            } else if (arr1[p1] <= arr2[p2]) {
                sortedArr[count++] = arr1[p1++];
            } else {
                sortedArr[count++] = arr2[p2++];
            }

            if (count == k) return sortedArr[count - 1];
        }

        //if(sortedArr.length == k) return sortedArr[sortedArr.length-1];
        return -1;
    }
}
