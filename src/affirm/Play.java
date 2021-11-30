package affirm;

/**
 * Created by brianzhang on 7/20/20.
 */
public class Play {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(6);
        System.out.println(sb.toString());
    }


    public String decode(String str){
        StringBuilder sb = new StringBuilder();
        int k = 0;

        int index = 0;
        for(char c : str.toCharArray()){
            if(Character.isLetter(c)){
                if(k>=1){
                    char temp = sb.charAt(sb.length()-1);
                    for(int i=0; i<k-1; i++) sb.append(temp);

                    k=0;
                }

                sb.append(c);
            }else if(Character.isDigit(c)){
                k = k * 10 + c - '0';
            }else if(c == '/'){
                if(k>0){
                    for(int i=0; i<k-1; i++){
                        sb.append(sb.charAt(sb.length()-1));
                    }
                }

                char numChar = str.charAt(++index);
                sb.append(numChar);
                char repeats = str.charAt(++index);
                for(int i =0; i< repeats; i++){
                    sb.append(numChar);
                }
            }

            index++;
        }

        return sb.toString();
    }

    public String compress(String str){
        char[] charArr = str.toCharArray();
        int index = 0, count = 0;
        StringBuilder sb  =new StringBuilder();

        while(index<charArr.length){
            char curr = charArr[index];
            while(index < charArr.length  && charArr[index] == curr){
                index++; count++;
            }

            sb.append(curr);
            if(count > 1){
                sb.append(count);
            }

            count = 0;
        }

        return sb.toString();
    }
}
