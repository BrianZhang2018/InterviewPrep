package linkedin.phoneScreening.ps2021;

public class IsNumeric {
    public static void main(String[] args)
    {
        String input = "-100.";
        System.out.println(isValidNumeric(input));
        double s = 1000.;
        System.out.println(s);
    }

    public static boolean isValidNumeric(String str)
    {
        str = str.trim(); // trims the white spaces.
        if (str.length() == 0) return false;

        // if string is of length 1 and the only character is not a digit
        if (str.length() == 1 && !Character.isDigit(str.charAt(0))) return false;

        // If the 1st char is not '+', '-', '.' or digit
        if (str.charAt(0) != '+' && str.charAt(0) != '-' && str.charAt(0) != '.' && !Character.isDigit(str.charAt(0)))
            return false;

        String signs = "+-.";
        boolean lastIsSign = signs.indexOf(str.charAt(0)) >= 0 ? true : false;
        boolean dotExist =  str.charAt(0) == '.' ? true : false;;

        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if(lastIsSign && c =='.') {
                return false;
            }else if(c == '.'){
                if(dotExist) return false;

                dotExist=true;
                lastIsSign = true;
            }else if(!Character.isDigit(c)){
                return false;
            }else{
                lastIsSign = false;
            }
        }

        return true;
    }
}
