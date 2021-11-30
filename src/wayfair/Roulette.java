package wayfair;

import java.util.Random;

/**
 * Created by brianzhang on 10/27/19.
 */
public class Roulette {

    public static void main(String[] args) {
        Roulette test = new Roulette();
        System.out.println(test.playGame());
    }

    public int playGame() {

        int money = 50;
        int wonTimes = 0;
        int lostTimes = 0;

        int time = 0, counter = 0;
        String currColor = "";
        String myBet = "";
        while (time <= 100) {
            time++;
            String color = randomColor();
            if (counter == 0) {
                counter++;
                currColor = color;
                continue;
            }

            if (!currColor.equals(color) && counter<4) {
                counter = 0;
                currColor = "";
                continue;
            }

            if (counter == 4) {
                myBet = color.equals("black") ? "red" : "black";
                money -= 10;
            }

            if (counter == 5) {
                if (myBet.equals(currColor)) {
                    money += 10;
                    if (money >= 100) {
                        wonTimes++;
                        money = 50;
                    }
                } else {
                    if (money <= 0) {
                        lostTimes = lostTimes+ 1;
                        money = 50;
                    }
                }

                counter = 0;
                myBet = "";
                continue;
            }

            counter++;
        }

        return wonTimes;
    }

    public String randomColor() {
        Random random = new Random();
        int color = random.nextInt(101);

        if (color % 2 == 0)
            return "black";
        else
            return "red";
    }
}
