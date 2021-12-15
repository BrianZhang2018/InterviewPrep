package apple.my2021;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Assignment:
 * Given a list of availability intervals within a single day for an interviewer and a candidate
 * return the list of overlapping time intervals.
 *
 * Input:
 * candidate availability: ["06:00", "07:00"],
 *             ["12:00", "14:00"],
 *             ["19:00", "20:00"]
 *
 * interviewer availability: ["10:00", "11:00"],
 *             ["12:00", "12:30"],
 *             ["18:30", "19:30"],
 *             ["23:00", "23:30"]
 *
 * Expected Output: ["12:00", "12:30"],
 *             ["19:30", "20:00"]
 */
public class FindOverlapTime {

    public static void main(String[] args) {
        testComparableForIntervals();

        testIntersectionOfTwoIntervals();

        testIntersectionOfTwoIntervalsContained();

        testIntersectionOfTwoIntervalsEmpty();

        positiveTest();

        negativeTest();

        multiTest();
    }

    // ------------------ TESTS -----------------

//    private static void testIsIntersecting() {
//        Interval first = new Interval("07:00", "10:00");
//        Interval second = new Interval("09:00", "11:00");
//
//        boolean expected = true;
//        boolean actual = first.isIntersecting(second);
//        System.out.println("==== test testIsIntersecting() >>>>>>>>");
//        if (actual == expected) {
//            System.out.println("PASSED - received expected value");
//        } else {
//            System.out.println("FAILED - expecting: " + expected + "\n but received: " + actual);
//        }
//        System.out.println("");
//    }

    private static void testIntersectionOfTwoIntervals() {
        Interval first = new Interval("07:00", "10:00");
        Interval second = new Interval("09:00", "11:00");

        Interval expected = new Interval("09:00", "10:00");
        Interval actual = first.intersectionWith(second);
        System.out.println("==== test testIntersectionOfTwoIntervals() >>>>>>>>");
        if (actual.equals(expected)) {
            System.out.println("PASSED - received expected interval");
        } else {
            System.out.println("FAILED - expecting: " + expected.toString() + "\n but received: " + actual.toString());
        }
        System.out.println("");
    }

    private static void testIntersectionOfTwoIntervalsContained() {
        Interval first = new Interval("07:00", "10:00");
        Interval second = new Interval("08:00", "09:00");

        Interval expected = second;
       // Interval actual = first.intersectionWith(second);
        Interval actual = second.intersectionWith(first);
        System.out.println("==== test testIntersectionOfTwoIntervalsContained() >>>>>>>>");
        if (actual.equals(second)) {
            System.out.println("PASSED - received expected interval");
        } else {
            System.out.println("FAILED - expecting: " + expected.toString() + "\n but received: " + actual.toString());
        }
        System.out.println("");
    }

    private static void testIntersectionOfTwoIntervalsEmpty() {
        Interval first = new Interval("07:00", "10:00");
        Interval second = new Interval("10:00", "11:00");

        Interval expected = second;
        Interval actual = first.intersectionWith(second);
        System.out.println("==== test testIntersectionOfTwoIntervalsEmpty() >>>>>>>>");
        if (actual.isEmpty()) {
            System.out.println("PASSED - received expected interval");
        } else {
            System.out.println("FAILED - expecting: " + expected.toString() + "\n but received: " + actual.toString());
        }
        System.out.println("");
    }

    private static void testComparableForIntervals() {
        List<Interval> unsortedList = Arrays.asList(new Interval("07:00", "08:00"), new Interval("01:00", "02:00"));

        List<Interval> actual = unsortedList.stream().sorted().collect(Collectors.toList());

        List<Interval> expected = Arrays.asList(new Interval("01:00", "02:00"), new Interval("07:00", "08:00"));
        System.out.println("==== test positiveTest() >>>>>>>>");
        if (actual.equals(expected)) {
            System.out.println("PASSED - received expected sorted list.");
        } else {
            System.out.println("FAILED - expecting: " + expected.toString() + "\n but received: " + actual.toString());
        }
        System.out.println("");
    }

    private static void positiveTest() {
        // given input parameters
        final List<Interval> interviewer = Arrays.asList(new Interval("06:00", "07:00"));
        final List<Interval> candidate = Arrays.asList(new Interval("06:00", "07:00"));

        // when schedule is created
        Set<Interval> schedule = findMutualAvailability(interviewer, candidate);

        // this is the expected outcome
        Set<Interval> expectedList = new HashSet<>();
        expectedList.add(new Interval("06:00", "07:00"));
        System.out.println("==== test positiveTest() >>>>>>>>");
        if (expectedList.equals(schedule)) {
            System.out.println("SUCCESS - expected schedule received");
        } else {
            System.out.println("FAILURE - expected list: " + expectedList.toString() +
                    "\n but got: " + schedule.toString());
        }
        System.out.println("");
    }

    private static void negativeTest() {
        // given input parameters
        List<Interval> interviewer = Arrays.asList(new Interval("06:00", "07:00"));
        List<Interval> candidate = Arrays.asList(new Interval("10:00", "11:00"));

        // when schedule is created
        Set<Interval> schedule = findMutualAvailability(interviewer, candidate);

        // this is the expected outcome
        System.out.println("==== test negativeTest() >>>>>>>>");
        if (schedule.equals(Collections.emptySet())) {
            System.out.println("PASSED - expected empty list");
        } else {
            System.out.println("FAILED - expected empty list, received: " + schedule.toString());
        }
        System.out.println("");
    }

    private static void multiTest() {
        // given input parameters
        List<Interval> interviewer = Arrays.asList(
                new Interval("06:00", "07:00"),
                new Interval("12:00", "14:00"),
                new Interval("19:00", "20:00")
        );
        List<Interval> candidate = Arrays.asList(
                new Interval("10:00", "11:00"),
                new Interval("12:00", "12:30"),
                new Interval("19:30", "21:00"),
                new Interval("23:00", "23:30")
        );

        // when schedule is created
        Set<Interval> schedule = findMutualAvailability(interviewer, candidate);

        Set<Interval> expectedSchedule = new HashSet<>();
        expectedSchedule.add( new Interval("12:00", "12:30"));
        expectedSchedule.add(  new Interval("19:30", "20:00"));

        // this is the expected outcome
        System.out.println("==== test multiTest() >>>>>>>>");
        if (schedule.equals(expectedSchedule)) {
            System.out.println("PASSED - expected list returned");
        } else {
            System.out.println("FAILED - expected collection: " + expectedSchedule.toString() + "\n " +
                    "but received: " + schedule.toString());
        }
        System.out.println("");
    }



    // ------------------------- IMPLEMENTATION ------------------------

    /**
     * Find the time intervals when both the interviewer and the candidate are available.
     * @param candidateAvailability list of availability intervals for candidate
     * @param interviewerAvailability list of availability intervals for interviewer
     * @return a set of overlapping intervals
     */
    private static Set<Interval> findMutualAvailability(List<Interval> candidateAvailability, List<Interval> interviewerAvailability) {
        // TODO add code here
        Set<Interval> res = new HashSet<Interval>();

        if(candidateAvailability == null || candidateAvailability.size() == 0 || interviewerAvailability == null || interviewerAvailability.size() == 0) return res;


        for(Interval c : candidateAvailability) {
            for(Interval i : interviewerAvailability) {
                Interval r = c.intersectionWith(i);
                if(r != null) res.add(r);
            }
        }

        return res;
    }

    /** represents a time interval */
    public static class Interval implements Comparable<Interval> {

        public static final Interval EMPTY = new Interval("00:00", "00:00");

        public final LocalTime from;
        public final LocalTime to;

        /** example: new Interval("6:00", "7:00") */
        public Interval(String from, String to) {
            this.from = LocalTime.parse(from);
            this.to = LocalTime.parse(to);
            validate();
        }

        public Interval(LocalTime from, LocalTime to) {
            this.from = from;
            this.to = to;
            validate();
        }

        private void validate() {
            if (this.to.isBefore(this.from)) {
                throw new IllegalArgumentException("to/from is out of expected order");
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Interval interval = (Interval) o;
            return from.equals(interval.from)
                    && to.equals(interval.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from,
                    to);
        }

        @Override
        public String toString() {
            return "Interval{"
                    + "from=" + from
                    + ", to=" + to
                    + "}";
        }

        @Override
        public int compareTo(Interval o) {
            return this.from.compareTo(o.from);
        }

        public boolean isEmpty() {
            return from.equals(to);
        }


        /**
            逻辑是: B_start_time  <=  A_start_time <= B_start_time
                  B_start_time  <=  A_start_time <= B_start_time
         */
        public Interval intersectionWith(Interval other) {
            // TODO - implement me

            if((this.from.compareTo(other.from) >=0 && this.from.compareTo(other.to) <= 0 ||
                    this.to.compareTo(other.from) >=0 && this.to.compareTo(other.to) <= 0)

                    ||
                    // alternatively, if we check both this.compare.Other and other.Compare.this, we can remove below condition
                    (other.from.compareTo(this.from) >=0 && other.from.compareTo(this.to) <= 0 ||
                            other.to.compareTo(this.from) >=0 && other.to.compareTo(this.to) <= 0)) {

                LocalTime start = this.from.isBefore(other.from) ? other.from : this.from;
                LocalTime end = this.to.isAfter(other.to) ? other.to : this.to;
                return new Interval(start, end);
            }

            return null;
        }
    }
}


