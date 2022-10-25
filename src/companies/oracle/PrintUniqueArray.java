package companies.oracle;

import java.util.HashSet;

/**
 * https://www.geeksforgeeks.org/print-distinct-elements-given-integer-array/
 *
 * Created by brianzhang on 3/17/20.
 */
public class PrintUniqueArray {

    // Driver program
    public static void main (String[] args)
    {
        int arr[] = {6, 10, 5, 4, 9, 120, 4, 6, 10};
        int n = arr.length;
        printDistinct1(arr, n);
        printDistinct(arr, n);

    }

    static void printDistinct(int arr[], int n)
    {
        // Pick all elements one by one
        for (int i = 0; i < n; i++)
        {
            // Check if the picked element is already printed
            int j=0;
            while(j<i){
                if (arr[i] == arr[j])
                    break;
                j++;
            }
            // If not printed earlier, then print it
            if (i == j)
                System.out.print( arr[i] + " ");
        }
    }

    static void printDistinct1(int arr[], int n)
    {
        HashSet<Integer> set = new HashSet<>();

        // Pick all elements one by one
        for (int i = 0; i < n; i++)
        {
            set.add(arr[i]);
        }

        System.out.println(set.toString());
    }
}
