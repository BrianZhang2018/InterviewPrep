package microsoft.ms2021.hiringEvent;

import java.util.Arrays;

/**
 * Created by brianzhang on 2/23/21.
 */
class CountFrequencies
{
    public static void main(String[] args)
    {
        CountFrequencies count = new CountFrequencies();
        count(new String[]{"a", "b", "c", "a", "b", "b"});

        int arr[] = {1, 2, 2, 1, 4, 5};
        int n = arr.length;
        count.printfrequency(arr, n);
    }

    public static String count(String[] input){
        Arrays.sort(input);

        int left = 0, right = 0;
        int count = 0;
        while(right<input.length){
            String curr = input[right];
            if(input[left].equals(curr)){
                count++;
            }else{
                input[++left] = String.valueOf(count);
                count = 0;
                input[++left] = curr;
            }
            right++;
        }

        for(int i=0; i<=left ; i++) System.out.println(input[i]);
        return null;
    }

    // Function to find counts of all elements present in
    // arr[0..n-1]. The array elements must be range from
    // 1 to n
    void printfrequency(int arr[], int n)
    {
        // Subtract 1 from every element so that the elements
        // become in range from 0 to n-1
        //for (int j = 0; j < n; j++)
        //    arr[j] = arr[j] - 1;

        // Use every element arr[i] as index and add 'n' to
        // element present at arr[i]%n to keep track of count of
        // occurrences of arr[i]
        for (int i = 0; i < n; i++){
            arr[arr[i] % n] = arr[arr[i] % n] + n;
        }


        // To printTrieDfs counts, simply printTrieDfs the number of times n
        // was added at index corresponding to every element
        for (int i = 1; i < n; i++)
            System.out.println(i  + "->" + arr[i]/n);
    }

}