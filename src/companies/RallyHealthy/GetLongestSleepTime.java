package companies.RallyHealthy;

import java.time.*;
import java.util.*;

/**
 * Rally Healthy - OA
 * James has all week meeting, please find a longest sleep time between the meetings.
 *
 * Created by brianzhang on 3/22/20.
 */
public class GetLongestSleepTime {

    public static void main(String[] args) {

        String input = "Sun 10:00-20:00\nFri 05:00-10:00\nFri 16:30-23:50\nSat 10:00-24:00\nSun 01:00-04:00\nSat 02:00-06:00\nTue 03:30-18:15\nTue 19:00-20:00\nWed 04:25-15:14\nWed 15:14-22:40\nThu 00:00-23:59\nMon 05:00-13:00\nMon 15:00-21:00";
        String[] strs = input.split("\n");
        System.out.println(strs.length);

        Map<String, DayOfWeek> dofMap = new HashMap<>();
        dofMap.put("Sun", DayOfWeek.SUNDAY);
        dofMap.put("Sat", DayOfWeek.SATURDAY);
        dofMap.put("Fri", DayOfWeek.FRIDAY);
        dofMap.put("Thu", DayOfWeek.THURSDAY);
        dofMap.put("Wed", DayOfWeek.WEDNESDAY);
        dofMap.put("Tue", DayOfWeek.TUESDAY);
        dofMap.put("Mon", DayOfWeek.MONDAY);

        List<MeetingTime> list = new ArrayList<>();

        for (String t : strs) {
            String[] mt = t.split(" ");
            String[] set = mt[1].split("-");
            LocalTime start = LocalTime.parse(set[0]);
            if (set[1].equals("24:00")) { // the hour range is 0-23
                set[1] = "00:00";
            }
            LocalTime end = LocalTime.parse(set[1]);
            list.add(new MeetingTime(dofMap.get(mt[0]), start, end));
        }

        Comparator<MeetingTime> dateComparator = (s1, s2) -> {
            if (s1.dayOfWeek.compareTo(s2.dayOfWeek) == 0) {
                return s1.start.compareTo(s2.start);
            } else {
                return s1.dayOfWeek.compareTo(s2.dayOfWeek);
            }
        };
        Collections.sort(list, dateComparator);

        int res = 0;
        for (int i = 1; i < list.size(); i++) {
            MeetingTime pre = list.get(i - 1);
            LocalDateTime from = LocalDateTime.of(LocalDate.now().plusDays(pre.end.getHour() != 0 ? pre.dayOfWeek.getValue() : pre.dayOfWeek.getValue() + 1), pre.end);
            MeetingTime curr = list.get(i);
            LocalDateTime to = LocalDateTime.of(LocalDate.now().plusDays(curr.dayOfWeek.getValue()), curr.start);
            Duration duration = Duration.between(from, to);
            System.out.println(from.toString());
            System.out.println(to.toString());
            System.out.println((int) duration.toMinutes());
            res = Math.max(res, (int) duration.toMinutes());

            //handle the edge case - sunday midnight
            if (i == list.size() - 1 && curr.end.getHour() != 0) {
                LocalDateTime last = LocalDateTime.of(LocalDate.now().plusDays(curr.end.getHour() != 0 ? curr.dayOfWeek.getValue() : curr.dayOfWeek.getValue() + 1), curr.end);
                LocalDateTime sunDayMidNight = LocalDateTime.of(LocalDate.now().plusDays(curr.dayOfWeek.getValue() + 1), LocalTime.parse("00:00"));
                Duration duration1 = Duration.between(last, sunDayMidNight);

                System.out.println(last.toString());
                System.out.println(sunDayMidNight.toString());
                System.out.println((int) duration1.toMinutes());

                res = Math.max(res, (int) duration1.toMinutes());
            }
        }

        System.out.println("the longest sleep time is: " + res + " minutes");
    }
}

class MeetingTime {
    DayOfWeek dayOfWeek;
    LocalTime start;
    LocalTime end;

    public MeetingTime(DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        this.dayOfWeek = dayOfWeek;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return this.dayOfWeek.toString() + "   " + this.start.toString() + "   " + this.end.toString();
    }
}
