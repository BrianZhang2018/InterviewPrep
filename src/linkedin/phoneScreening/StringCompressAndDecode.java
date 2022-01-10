package linkedin.phoneScreening;

/**
 * Created by brianzhang on 11/8/20.
 */
public class StringCompressAndDecode {

    public static void main(String[] args) {
        System.out.println(compress("aaabbbc"));
        System.out.println(decode("A1b2c3"));
        char[] cs = new char[26];
        cs[1]++;
        cs[1]++;
        System.out.println(cs[1]);
    }

    public static String compress(String s){
        StringBuilder sb = new StringBuilder();
        int left = 0;
        char[] cs = s.toCharArray();

        while(left < s.length()){
            int count = 0;
            char c = cs[left];
            while(left < s.length() && c == cs[left]){
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
        int i = 0; int repeat=0;
        while(i < s.length()){
            if(Character.isLetter(cs[i])){
                if(repeat>=1){
                    char c = sb.charAt(sb.length()-1);
                    while(repeat-- > 1){
                        sb.append(c);
                    }
                }
                sb.append(cs[i]);
            }else{
                repeat = repeat*10 + cs[i] - '0';
            }
            i++;
        }

        if(repeat!=0){
            char c = sb.charAt(sb.length()-1);
            while(repeat-- > 1){
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
