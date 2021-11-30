package apple.myPhoneScreening;

/**
 * Created by brianzhang on 5/7/21.
 */

class Solution {
    /**
     * This interface represents a number from a set of non-negative
     * integers (equal to or greater than zero). Given a single number N,
     * one can advance to the previous N-1 (assuming N > 0) or to the next
     * N+1 number. There is no previous number for zero.
     */
    public interface Num {
        /**
         * Returns the next number.
         * @return the next number.
         */
        Num next();

        /**
         * Returns the previous number.
         * @return the previous number; null if this represents zero.
         */
        Num prev();

        /**
         * Returns true if this represents zero.
         * @return true if this represents zero.
         */
        default boolean isZero() {
            return prev() == null;
        }
    }

    /////////////////////////////////////////////////////////////////
    //
    // TASK: Implement the following arithmetic operations using Num
    // interface:
    //
    // - addition       (+): add
    // - subtraction    (-): sub
    // - multiplication (*): mul
    // - division       (/): div
    //
    // Edit the code below.

    // 2 + 3 = 5
    // 2 + 3 = 5 = 2 + 1 + 1 + 1 = (((2 + 1) + 1) + 1)
    // 3 + 2 = 5
    // 4 + 1 = 5
    // 5 + 0 = 5
    /////////////////////////////////////////////////////////////////

    /**
     * Returns a + b
     * @param a a number.
     * @param b a number.
     * @return a + b
     */
    public static Num add(Num a, Num b) {
        Num tmp = b, res = a;
        while(!tmp.isZero()){
            res = res.next();
            tmp = tmp.prev();
        }

        return res;
    }

    /**
     * Returns a - b; zero if b > a.
     * @param a a number.
     * @param b a number.
     * @return a - b
     */
    public static Num sub(Num a, Num b) {
        Num tmp = b, res = a;
        while(!tmp.isZero()){
            if(res.isZero()) return res;

            res = res.prev();
            tmp = tmp.prev();
        }

        return res;
    }

    /**
     * Returns a * b
     * @param a a number.
     * @param b a number.
     * @return a * b
     */
    public static Num mul(Num a, Num b) {
        if(b.isZero()) return b;

        if(b.prev().isZero()) return a;

        Num res = add(a, a);
        Num tmp = b.prev().prev();

        while(!tmp.isZero()){
            res = add(res, a);
            tmp = tmp.prev();
        }
        return res;
    }

    /**
     * Returns a / b; zero if b > a.
     * @param a a number.
     * @param b a number.
     * @return a / b
     * @throws IllegalArgumentException if b is zero.
     */
    public static Num div(Num a, Num b) {

        if(b.isZero()) throw new IllegalArgumentException();

        if(b.prev().isZero()) return a;

        if(compare(b, a)) return new NumImpl(null);

        int cnt = 0;
        Num m=a, n=b;
        while(!m.isZero() && compare(m,n)){
            cnt++;
            m = sub(m, n);
            if(m.isZero()) break;
        }

        Num res = new NumImpl(null);
        while(cnt-- > 0){
            res = res.next();
        }

        return res;
    }

    private static boolean compare(Num a, Num b){ // check whether a smaller than b
        Num m=a, n=b;
        while(!n.isZero()){
            if(m.isZero() && !n.isZero()) return true;
            m = m.prev();
            n = n.prev();
        }

        return true;
    }

    /////////////////////////////////////////////////////////////////
    // Tests - do not edit below this line.
    /////////////////////////////////////////////////////////////////

    static final class NumImpl implements Num {
        private final NumImpl prev;
        private NumImpl next;

        private final int debug;

        NumImpl(final NumImpl prev) {
            this.prev = prev;
            this.debug = prev == null ? 0 : prev.debug + 1;
        }

        @Override
        public Num next() {
            if (next == null) {
                next = new NumImpl(this);
            }
            return next;
        }

        @Override
        public Num prev() {
            return prev;
        }

        @Override
        public String toString() {
            return String.valueOf(debug);
        }
    }

    static final Num ZERO = new NumImpl(null);

    static void check(final String expr, final String expected, final Num n) {
        if (n == null || !n.toString().equals(expected)) {
            System.out.println("FAIL: " + expr + " == " + n);
        } else {
            System.out.println("PASS: " + expr + " == " + n);
        }
    }

    public static void main(String[] args) {
        final Num one = ZERO.next();
        final Num two = one.next();

        System.out.println("add() tests:");
        final Num three = add(one, two);
        check("1 + 2", "3", three);
        check("2 + 1", "3", add(two, one));
        check("3 + 0", "3", add(three, ZERO));
        check("0 + 3", "3", add(ZERO, three));
        check("0 + 0", "0", add(ZERO, ZERO));
        check("1 + (1 + (1 + 0))", "3", add(one, add(one, add(one, ZERO))));
        final Num five = add(two, three);
        check("2 + 3", "5", five);
        System.out.println("");

        System.out.println("sub() tests:");
        final Num four = sub(five, one);
        check("5 - 1", "4", four);
        check("5 - (5 - 2)", "2", sub(five, sub(five, two)));
        check("5 - 0", "5", sub(five, ZERO));
        check("0 - 5", "0", sub(ZERO, five));
        check("2 - 2", "0", sub(two, two));
        check("4 - 5", "0", sub(four, five));
        System.out.println("");

        System.out.println("mul() tests:");
        final Num six = mul(two, three);
        check("2 * 3", "6", six);
        check("3 * (2 * 1)", "6", mul(three, mul(two, one)));
        check("1 * 0", "0", mul(one, ZERO));
        check("0 * 1", "0", mul(ZERO, one));
        check("0 * 0", "0", mul(ZERO, ZERO));
        final Num twelve = mul(six, two);
        check("6 * 2", "12", twelve);
        System.out.println("");

        System.out.println("div() tests:");
        check("12 / 2", "6", div(twelve, two));
        check("12 / (12 / 4)", "4", div(twelve, div(twelve, four)));
        check("0 / 12", "0", div(ZERO, twelve));
        check("5 / 1", "5", div(five, one));
        check("3 / 3", "1", div(three, three));
        check("2 / 5", "0", div(two, five));
        check("5 / 2", "2", div(five, two));

        try {
            div(one, ZERO);
            System.out.println("FAIL: 1 / 0: Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: 1 / 0: " + e.getMessage());
        }
        System.out.println("");
    }
}

