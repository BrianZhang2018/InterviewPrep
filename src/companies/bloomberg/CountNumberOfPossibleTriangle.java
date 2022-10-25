package companies.bloomberg;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/find-number-of-triangles-possible/
 * https://www.1point3acres.com/bbs/thread-637744-1-1.html
 *
 * Bloomberg onsite
 *
 * Created by brianzhang on 6/3/20.
 */
public class CountNumberOfPossibleTriangle {
    public static void main(String[] args) {
        int arr[] = { 10, 21, 22, 100, 101, 200, 300 };
        int size = arr.length;

        System.out.println( "Total number of triangles possible is "+ findNumberOfTriangles(arr, size));
        System.out.println( "Total number of triangles possible is "+ findNumberOfTrianglesTwoPointer(arr, size));
    }

    //Brute force
    static int findNumberOfTriangles(int arr[], int n){
        int count = 0;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                for(int k=j+1; k<n; k++){
                    if(arr[i]+arr[j]>arr[k] && arr[j]+arr[k]>arr[i]&&arr[k]+arr[i]>arr[j])
                        count++;
                }
            }
        }
        return count;
    }

    // Two pointer
    static int findNumberOfTrianglesTwoPointer(int arr[], int n){
        Arrays.sort(arr);
        int count = 0;
        for(int i=n-1; i>=1; i--){
            int l=0, r = i-1;
            while(l<r){
                if(arr[l] + arr[r] > arr[i]){
                    count += r-l;
                    r--;
                }else{
                    l++;
                }
            }
        }
        return count;
    }
}
