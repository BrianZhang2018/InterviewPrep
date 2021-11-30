package linkedin.phoneScreening;

/**
 * Created by brianzhang on 11/8/20.
 */
public class StringCompressAndDecode {

    public static void main(String[] args) {
        System.out.println(compress("aabbbc"));
        System.out.println(decode("A1b2c3"));
    }

    public static String compress(String s){

        StringBuilder sb = new StringBuilder();
        int left = 0;
        char[] cs = s.toCharArray();

        while(left < s.length()){
            int count = 0;
            char c = cs[left];
            while(left<s.length() && c == cs[left]){
                left++;
                count++;
            }

            sb.append(c);
            if(count > 1){
                sb.append((char) (count + '0'));
            }
        }

        return sb.toString();
    }

    public static String decode(String s){
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        int index = 0; int k=0;
        while(index < s.length()){
            if(Character.isLetter(cs[index])){
                if(k>=1){
                    char c = sb.charAt(sb.length()-1);
                    while(k-- > 1){
                        sb.append(c);
                    }
                }
                sb.append(cs[index]);
            }else{
                k = k*10 + cs[index] - '0';
            }
            index++;
        }

        if(k!=0){
            char c = sb.charAt(sb.length()-1);
            while(k-- >1){
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
