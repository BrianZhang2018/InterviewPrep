package bloomberg.myPhonescreening;

/**
 * 给一个digit string， 把它解码成字母a-z，A-Z, 根据ASCII 值
 * e.g.  11266  -> pB
 *
 * Created by brianzhang on 5/18/20.
 */
public class DecodeDigitString {
    public static void main(String[] args) {
        System.out.println(decode("71198211"));
    }

    public static String decode(String encoded) {
        String reversed = reverse(encoded);
        char[] cs = reversed.toCharArray();
        String rs = new String(cs);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<cs.length; i++){
            int start = i;
            if(cs[i] == '1'){
                i +=2;
            }else{
                i +=1;
            }
            String temp = rs.substring(start, i+1);
            int d = Integer.valueOf(temp);
            sb.append((char)d);
        }

        return sb.toString();
    }

    public static String reverse(String str){
        char[] cs = str.toCharArray();
        int l = 0, r=cs.length-1;
        while (l < r) {
            char temp = cs[l];
            cs[l] = cs[r];
            cs[r] = temp;
            l++;
            r--;
        }
        return String.valueOf(cs);
    }
}