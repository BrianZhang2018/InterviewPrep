package linkedin.phoneScreening.ps2021;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) return true;

        int i=0;
        while(i<flowerbed.length) {
            if(flowerbed[i] == 0 && (i==0 || flowerbed[i-1]==0) && (i== flowerbed.length-1 || flowerbed[i+1] == 0)){
                if(--n == 0) return true;
                flowerbed[i] = 1;
            }
            i++;
        }

        return false;
    }
}
