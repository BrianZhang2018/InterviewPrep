package opendoor;

import java.util.*;

/**
 * Created by brianzhang on 2/18/21.
 */
public class MyOA {

    public static void main(String[] args) {
        HashMap<String, String[]> babyNames = new HashMap<>();
        babyNames.put("2016-baby-center-girls",
                new String[] { "Sophia", "Emma", "Olivia", "Ava", "Mia", "Isabella", "Riley", "Aria", "Zoe", "Charlotte",
                        "Lily", "Layla", "Amelia", "Emily", "Madelyn", "Aubrey", "Adalyn", "Madison", "Chloe", "Harper", "Abigail",
                        "Aaliyah", "Avery", "Evelyn", "Kaylee", "Ella", "Ellie", "Scarlett", "Arianna", "Hailey", "Nora", "Addison",
                        "Brooklyn", "Hannah", "Mila", "Leah", "Elizabeth", "Sarah", "Eliana", "Mackenzie", "Peyton", "Maria",
                        "Grace", "Adeline", "Elena", "Anna", "Victoria", "Camilla", "Lillian", "Natalie" });
        babyNames.put("2016-baby-center-boys",
                new String[] { "Jackson", "Aiden", "Lucas", "Liam", "Noah", "Ethan", "Mason", "Caden", "Oliver", "Elijah",
                        "Grayson", "Jacob", "Michael", "Benjamin", "Carter", "James", "Jayden", "Logan", "Alexander", "Caleb",
                        "Ryan", "Luke", "Daniel", "Jack", "William", "Owen", "Gabriel", "Matthew", "Connor", "Jayce", "Isaac",
                        "Sebastian", "Henry", "Muhammad", "Cameron", "Wyatt", "Dylan", "Nathan", "Nicholas", "Julian", "Eli",
                        "Levi", "Isaiah", "Landon", "David", "Christian", "Andrew", "Brayden", "John", "Lincoln" });
        babyNames.put("2015-baby-center-girls",
                new String[] { "Sophia", "Emma", "Olivia", "Ava", "Mia", "Isabella", "Zoe", "Lily", "Emily", "Madison",
                        "Amelia", "Riley", "Madelyn", "Charlotte", "Chloe", "Aubrey", "Aria", "Layla", "Avery", "Abigail", "Harper",
                        "Kaylee", "Aaliyah", "Evelyn", "Adalyn", "Ella", "Arianna", "Hailey", "Ellie", "Nora", "Hannah", "Addison",
                        "Mackenzie", "Brooklyn", "Scarlett", "Anna", "Mila", "Audrey", "Isabelle", "Elizabeth", "Leah", "Sarah",
                        "Lillian", "Grace", "Natalie", "Kylie", "Lucy", "Makayla", "Maya", "Kaitlyn" });
        babyNames.put("2015-baby-center-boys",
                new String[] { "Jackson", "Aiden", "Liam", "Lucas", "Noah", "Mason", "Ethan", "Caden", "Logan", "Jacob",
                        "Jayden", "Oliver", "Elijah", "Alexander", "Michael", "Carter", "James", "Caleb", "Benjamin", "Jack",
                        "Luke", "Grayson", "William", "Ryan", "Connor", "Daniel", "Gabriel", "Owen", "Henry", "Matthew", "Isaac",
                        "Wyatt", "Jayce", "Cameron", "Landon", "Nicholas", "Dylan", "Nathan", "Muhammad", "Sebastian", "Eli",
                        "David", "Brayden", "Andrew", "Joshua", "Samuel", "Hunter", "Anthony", "Julian", "Dominic" });
        babyNames.put("2015-us-official-girls", new String[] { "Emma", "Olivia", "Sophia", "Ava", "Isabella", "Mia",
                "Abigail", "Emily", "Charlotte", "Harper" });
        babyNames.put("2015-us-official-boys", new String[] { "Noah", "Liam", "Mason", "Jacob", "William", "Ethan", "James",
                "Alexander", "Michael", "Benjamin" });

        MyOA s = new MyOA();
        s.getPreMap(babyNames);

        for(String str : s.solution("Sophia")) System.out.println(str);

    }

    private Map<String, TreeMap<Integer,List<String>>> preMap;

    public  void getPreMap(HashMap<String, String[]>  babyNames){
        preMap = new HashMap<>();
        for(Map.Entry<String,String[]> entry : babyNames.entrySet()){
            String key = entry.getKey();
            String[] babies = entry.getValue();

            for(int i=0; i<babies.length; i++){
                preMap.putIfAbsent(babies[i], new TreeMap<>());
                TreeMap<Integer, List<String>> tm = preMap.get(babies[i]);
                List<String> keys = tm.getOrDefault(i, new ArrayList<>());
                keys.add(key);
                tm.put(i, keys); // put it back

                /*if(tm.containsKey(i)){
                    tm.get(i).add(key);
                }else{
                    List<String> list = new ArrayList();
                    list.add(key);
                    tm.put(i, list);
                }*/
            }
        }
    }


    public List<String> solution(String name) {

        TreeMap<Integer, List<String>> result = preMap.get(name);
        List<String> response = new ArrayList<>();

        for(Map.Entry<Integer,List<String>> entry : result.entrySet()){
            for(String str : entry.getValue()){
                response.add("list: " + str + " ,rank" + entry.getKey());
            }
        }

        System.out.println(response.size());
        return response;
    }

}


/*
Your previous Plain Text content is preserved below:


 Feel free to use any language to write this program in.


 Baby Names
 ===========

 Build a baby names search engine.

 We collected baby names from various published lists and
 put them into a JSON object as follows:

 key: source list name
 (e.g. "2015-us-official-boys", "2015-baby-center-girls")

 value: a list of names in the order of popularity
 (e.g. [ "Sophia", "Emma", "Olivia", ... ])

 baby-names-data.json

 {
   "2016-baby-center-girls": [ "Sophia", "Emma", "Olivia", ... ],
   "2016-baby-center-boys": [ "Jackson", "Aiden", "Lucas", ...],
   "2015-baby-center-girls": [ "Sophia", "Emma", "Olivia", ... ],
   ...
 }

 Write a function that given a name, returns an ascending rank
 sorted list of names of all lists where the given name appears.

 For example, given "sophia", function returns:

  [
    {list: "2016-baby-center-girls", rank: 1},
    {list: "2015-baby-center-girls", rank: 1},
    {list: "2015-us-official-girls", rank: 3}
  ]


 Examples:

 Input: "sophia" Output:

 [
   { list: '2016-baby-center-girls', rank: 1 },
   { list: '2015-baby-center-girls', rank: 1 },
   { list: '2015-us-official-girls', rank: 3 }
 ]

 Input: "nicholas" Output:

 [
   { list: '2015-baby-center-boys', rank: 36 },
   { list: '2016-baby-center-boys', rank: 39 }
 ]



{
  "2016-baby-center-girls": [
      "Sophia", "Emma", "Olivia", "Ava", "Mia", "Isabella", "Riley", "Aria",
      "Zoe", "Charlotte", "Lily", "Layla", "Amelia", "Emily", "Madelyn",
      "Aubrey", "Adalyn", "Madison", "Chloe", "Harper", "Abigail", "Aaliyah",
      "Avery", "Evelyn", "Kaylee", "Ella", "Ellie", "Scarlett", "Arianna",
      "Hailey", "Nora", "Addison", "Brooklyn", "Hannah", "Mila", "Leah",
      "Elizabeth", "Sarah", "Eliana", "Mackenzie", "Peyton", "Maria", "Grace",
      "Adeline", "Elena", "Anna", "Victoria", "Camilla", "Lillian", "Natalie"
  ],
  "2016-baby-center-boys": [
      "Jackson", "Aiden", "Lucas", "Liam", "Noah", "Ethan", "Mason", "Caden",
      "Oliver", "Elijah", "Grayson", "Jacob", "Michael", "Benjamin", "Carter",
      "James", "Jayden", "Logan", "Alexander", "Caleb", "Ryan", "Luke",
      "Daniel", "Jack", "William", "Owen", "Gabriel", "Matthew", "Connor",
      "Jayce", "Isaac", "Sebastian", "Henry", "Muhammad", "Cameron", "Wyatt",
      "Dylan", "Nathan", "Nicholas", "Julian", "Eli", "Levi", "Isaiah",
      "Landon", "David", "Christian", "Andrew", "Brayden", "John", "Lincoln"
  ],
  "2015-baby-center-girls": [
      "Sophia", "Emma", "Olivia", "Ava", "Mia", "Isabella", "Zoe", "Lily",
      "Emily", "Madison", "Amelia", "Riley", "Madelyn", "Charlotte", "Chloe",
      "Aubrey", "Aria", "Layla", "Avery", "Abigail", "Harper", "Kaylee",
      "Aaliyah", "Evelyn", "Adalyn", "Ella", "Arianna", "Hailey", "Ellie",
      "Nora", "Hannah", "Addison", "Mackenzie", "Brooklyn", "Scarlett", "Anna",
      "Mila", "Audrey", "Isabelle", "Elizabeth", "Leah", "Sarah", "Lillian",
      "Grace", "Natalie", "Kylie", "Lucy", "Makayla", "Maya", "Kaitlyn"
  ],
  "2015-baby-center-boys": [
      "Jackson", "Aiden", "Liam", "Lucas", "Noah", "Mason", "Ethan", "Caden",
      "Logan", "Jacob", "Jayden", "Oliver", "Elijah", "Alexander", "Michael",
      "Carter", "James", "Caleb", "Benjamin", "Jack", "Luke", "Grayson",
      "William", "Ryan", "Connor", "Daniel", "Gabriel", "Owen", "Henry",
      "Matthew", "Isaac", "Wyatt", "Jayce", "Cameron", "Landon", "Nicholas",
      "Dylan", "Nathan", "Muhammad", "Sebastian", "Eli", "David", "Brayden",
      "Andrew", "Joshua", "Samuel", "Hunter", "Anthony", "Julian", "Dominic"
  ],
  "2015-us-official-girls": [
    "Emma", "Olivia", "Sophia", "Ava", "Isabella", "Mia", "Abigail",
    "Emily","Charlotte", "Harper"
  ],
  "2015-us-official-boys": [
    "Noah", "Liam", "Mason", "Jacob", "William", "Ethan",
    "James", "Alexander", "Michael", "Benjamin"
   ]
}

 */
