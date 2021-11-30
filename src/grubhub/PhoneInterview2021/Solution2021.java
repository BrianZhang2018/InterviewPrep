package grubhub.PhoneInterview2021;

/* This program evaluates possible lost orders when application or system errors occur.

   Every customer interaction is associated with a user 'session', which has a string identifier that
   does not contain any commas.

   As a customer interacts with GrubHub to place an order, the session goes through a series of 'states'.
   Each state is identified by a string.  Some (but not all) state values are "login", "address",
   "restaurants", "items", "cart", "placed", and more.  There is no specific ordering of the states,
   and states can be visited multiple times.

   An interaction is said to be 'converted' if it reaches the "restaurants" state, and 'lost' if it does not.

   Application and system errors can cause the customer not to be able to place an order.  Sometimes an error
   will occur that might keep the customer from reaching the "restaurants" state initially, but sometimes a
   retry will succeed.

   We have a (modest sized - 10,000 records) file of session identifiers for those user sessions that have
   experienced an application or system error.  There may be duplicates in the file.

   We have a (very large - 10,000,000 records) file  of states that each user session has reached, where each
   record consists of the concatenation of the session ID, a comma, and the state.  There may be duplicates.
   This list will normally be very much larger (millions) than the errorSession list (thousands).

   Objective: Write the code that produces a (println) report that tells us how many of the error sessions have
   been converted, how many have been lost, and how many we do not have state information about.

   In real life, these files would be read into the program; in this exercise, they will be provided as arrays.

   Step 1: create a class, called MockFile,  that wraps a String array, and supports:
    - constructor from an array of strings
    - getNext, which produces the next String value, or null if there are no more

   Step 2: use the MockFile class to achieve the objective.
 */

import java.util.*;

// 2021-02-08
public class Solution2021 {

    static String[] errorSessionsArray  = new String[] {
            "session 1", "session 2", "session 3", "session 5", "session 1"};
    static String[] sessionStatesArray = new String[] {
            "session 1,login", "session 1,address", "session 1,restaurant", "session 1,address",
            "session 2,login", "session 2,address", "session 2,items",
            "session 4,items", "session 4,cart",
            "session 6,login", "session 6, restaurant", "session 6, restaurant"
    };

    private static void analyzeAndReport(String[] errorSessionArray, String[] sessionStatesArray) {
        MockFile mf = new MockFile(errorSessionArray);
        HashSet<String> sessionSet = new HashSet<>();
        while(mf.hasNext()){
            String str = mf.getNext();
            sessionSet.add(str);
        }

        MockFile mf1 = new MockFile(sessionStatesArray);
        Set<String> restaurant = new HashSet<>();
        Set<String> appearedSessions = new HashSet<>();

        while(mf1.hasNext()){
            String[] str = mf1.getNext().split(",");
            String session = str[0], state = str[1];

            if (!sessionSet.contains(session)) continue;

            appearedSessions.add(session);
            if(state.equals("restaurant")){
                restaurant.add(session);
            }
        }

        int reachedRestaurantSessions = restaurant.size();
        int nonReachedRestaurantSessions = appearedSessions.size()-restaurant.size();
        int noInfoSessions = sessionSet.size() - nonReachedRestaurantSessions - reachedRestaurantSessions;

        System.out.println(reachedRestaurantSessions + "   " + nonReachedRestaurantSessions + "   " + noInfoSessions);
    }

    public static void main(String[] args) {
        analyzeAndReport(errorSessionsArray, sessionStatesArray);
    }

    //add class definitions here
    static class MockFile{
        private String[] array;
        private int count = 0;
        public MockFile(String[] array){
            this.array = array;
        }

        public boolean hasNext()
        {
            if(count<array.length){
                return true;
            }else{
                return false;
            }
        }

        public String getNext(){
            return array[count++];
        }
    }
}
