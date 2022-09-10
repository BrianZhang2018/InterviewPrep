package salesforce;

// https://www.geeksforgeeks.org/maximum-value-k-such-that-array-has-at-least-k-elements-that-are-k/
public class MaxValueK {
    static int findMaximumNum(int arr[], int n) {

        for (int i = n; i >= 1; i--) {
            int count = 0;

            for (int j = 0; j < n; j++)
                if (i <= arr[j])
                    count++;

            if (count >= i)
                return i;
        }
        return 1;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 8, 10};
        int n = arr.length;
        System.out.println(findMaximumNum(arr, n));
    }
}