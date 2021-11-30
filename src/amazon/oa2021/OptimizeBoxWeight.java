package amazon.oa2021;

import java.util.Arrays;

/**
 * https://aonecode.com/amazon-online-assessment-optimizing-box-weight
 *
 * 计算一下sum, then 排一下. then 一个一个加起来，当正好超过二分之一的总和的话，说明你找到了那个点。然后把那个点的数值和之后的数值组成一个array返回就可以了。
 * Created by brianzhang on 3/7/21.
 */
public class OptimizeBoxWeight {

    public static void main(String[] args) {
        for(int i : optimizeBoxWeight(new int[]{5, 3, 2, 4, 1, 2}))
            System.out.println(i);

        System.out.println(Math.ceil(0.25));
        System.out.println(Math.ceil(1.0 * 0.5 / 2));
    }

    public static int[] optimizeBoxWeight(final int[] arr) {
        int total = Arrays.stream(arr).sum();
        Arrays.sort(arr);
        int sum = 0;
        int index = 0;
        for (; index < arr.length && 2 * sum <= total; ++index) {
            sum += arr[index];
        }
        --index;
        int[] result = new int[arr.length - index];
        for (int i = 0; i < arr.length - index; ++i) {
            result[i] = arr[index + i];
        }
        return result;
    }
}
