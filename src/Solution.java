import java.util.*;

public class Solution {

    // ********************newspaper************************
    public static String[][] newspaper(String[][] words, int width) {
        // prepossess
        List<StringBuilder> lst = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            lst.add(new StringBuilder());
            for (int j = 0; j < words[i].length; j++) {
                String word = words[i][j];
                // new
                if (lst.get(lst.size() - 1).length() + 1 + word.length() > width) {
                    lst.add(new StringBuilder());
                }

                StringBuilder sb = lst.get(lst.size() - 1);
                if (sb.length() != 0)
                    sb.append(" ");
                sb.append(words[i][j]);
            }
        }

        // construct new words
        String[][] res = new String[2 + lst.size()][1];

        // add space
        for (int i = 0; i < res.length; i++) {
            if (i == 0 || i == res.length - 1) {
                res[i][0] = getLine(width);
            } else {
                res[i][0] = constructWords(lst.get(i - 1), width);
            }
        }

        return res;
    }

    private static String getLine(int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private static String constructWords(StringBuilder sb, int width) {
        StringBuilder res = new StringBuilder();
        // first
        for (int i = 0; i < (width - sb.length()) / 2; i++) {
            res.append(" ");
        }
        res.append(sb.toString());
        for (int i = 0; i < (width - sb.length() + 1) / 2; i++) {
            res.append(" ");
        }
        return res.toString();
    }

    public static void testNewspaper() {
        String[][] input = new String[][] {
                {"hello", "world"},
                {"how", "are", "you"}
        };

        String[][] res = newspaper(input, 9);
        for (String[] str : res) {
            System.out.println(str[0]);
        }
    }

    // ********************Time******************************
    public static String getTime(String[] times, String target) {
        for (int i = 0; i < times.length; i++) {
            String res = getDiff(times[i], target);
            if (res != "-1") {
                return res;
            }
        }
        return "-1";
    }

    private static String getDiff(String t1, String t2) {
        String[] strs1 = t1.split(":");
        String[] strs2 = t2.split(":");

        int h1 = Integer.valueOf(strs1[0]);
        int h2 = Integer.valueOf(strs2[0]);

        if (h1 < h2) return "-1";

        int m1 = Integer.valueOf(strs1[1]);
        int m2 = Integer.valueOf(strs2[1]);

        if (m1 >= m2) {
            return getTimeFormat(h1 - h2, m1 - m2);
        } else {
            if (h1 == h2) return "-1";
            return getTimeFormat(h1 - h2 - 1, m1 + 60 - m2);
        }
    }

    private static String getTimeFormat(int h, int m) {
        String strH;
        String strM;
        if (h < 10) strH = "0" + Integer.toString(h);
        else strH = Integer.toString(h);
        if (m < 10) strM = "0" + Integer.toString(m);
        else strM = Integer.toString(m);
        return strH + ":" + strM;
    }

    public static void testGetDiff() {
        String[] times = new String[] {
                "01:22",
                "22:13",
                "24:00",
                "33:22"
        };
        String target = "52:50";
        System.out.println(getTime(times, target));
    }

    // *******************Even or Odd********************************
    public static String getEvenOrOdd(int[] nums) {
        int sumEven = 0;
        int sumOdd = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                sumEven += i;
            } else {
                sumOdd += i;
            }
        }
        if (sumEven > sumOdd) return "even";
        else if (sumEven < sumOdd) return "odd";
        else return "equal";
    }

    public static void testGetEvenOrOdd() {
        int[] nums = new int[]{
                1, 2, 3, 4, 5
        };
        System.out.println(getEvenOrOdd(nums));
    }

    //*******************station****************
    public static String getTicket(String[] stationsA, String[] stationsB, String[] stationsC, String ori, String des) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String station : stationsA) {
            map.put(station, 1);
        }
        for (String station : stationsB) {
            map.put(station, 2);
        }
        for (String station : stationsC) {
            map.put(station, 4);
        }

        int sum = map.get(ori) + map.get(des);
        if (sum < 5) {
            return "AB";
        } else if (sum == 5) {
            return "ABC";
        } else {
            return "BC";
        }
    }

    public static void testGetTicket() {
//        System.out.printf(getTicket());
    }


}