package wayfair;

/**
 * Refer the solution from LargestRectangleInHistogram
 */
public class MaximalRectangle {
    public static void main(String[] args){
        char[][] matrix = new char[][]{{'0', '1'}, {'1', '0'}};
        MaximalRectangle test = new MaximalRectangle();
        System.out.println(test.maximalRectangle(matrix));
    }
    
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length ==0)
            return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        //record the column heights in every level
        int[] heights = new int[n];
        int max = 0;
        for(int i=0; i<m; i++){
            for(int j=0;j<n; j++){
                if(matrix[i][j] == '1'){
                    heights[j]++;
                }else{
                    //reset to '0' if '1' is not continuous in the same column
                    heights[j] =0;
                }
            }
            max = Math.max(max, getLargestRectangleInHistogram(heights));
        }
        
        return max;
    }
    
    public int getLargestRectangleInHistogram(int heights[]){
        int n = heights.length;
        int res = 0;
        for(int i=0; i<n; i++){
            if(i+1 <n && heights[i]<=heights[i+1])
                continue;
            
            int minVal = heights[i];
            for(int j = i; j>=0; j--){
                minVal = Math.min(minVal, heights[j]);
                res = Math.max(res, (i-j+1) * minVal);
            }
        }
        
        return res;
    }

}