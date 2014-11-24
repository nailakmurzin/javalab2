package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OktmoMain {

    private static OktmoData read() {
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        or.readPlaces_IndexOf("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        return data;
    }

    public static long readPlace() {
        long start;
        System.out.printf("//// indexOf ");
        start = System.nanoTime();
        OktmoReader or2 = new OktmoReader();
        OktmoData data2 = new OktmoData();
        or2.readPlaces_IndexOf("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data2);
        long time2 = System.nanoTime() - start;
        System.out.println(" time: of " + time2);
        return time2;
    }

    public static long readPlace_Split() {
        long start;
        System.out.printf("//// split ");
        start = System.nanoTime();
        OktmoReader or1 = new OktmoReader();
        OktmoData data1 = new OktmoData();
        or1.readPlaces_Split("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data1);
        long time1 = System.nanoTime() - start;
        System.out.println(" time: of " + time1);
        return time1;
    }

    public static void readTest() {
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] noSuitable = or.readPlaces_IndexOf("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);

        //System.out.println(data);
        //System.out.println("1/////////////");
        for (String s : noSuitable) {
            //System.out.println(s);
        }
        System.out.println("2/////////////");
        for (String s : data.getStatues()) {
            System.out.println(s);
        }
    }

    public static void sortPlace() {
        OktmoData data = read();

        System.out.println("2/////////////");
        ArrayList<String> list = new ArrayList<>();
        data.getPlaces().stream().forEach((p) -> {
            list.add(p.getName());
        });
        Collections.sort(list);
        list.stream().forEach((s) -> {
            System.out.println(s);
        });
        ArrayList<Place> sortedPlaces = new ArrayList<>();
        sortedPlaces.addAll(data.getPlaces());
        Collections.sort(sortedPlaces, new Place.SortedByName());
        sortedPlaces.stream().forEach((p) -> {
            System.out.println(p);
        });
    }

    public static void regexPlace() {
        ArrayList<String> list = new ArrayList<>();
        Pattern p = Pattern.compile("^.{0,3}ово$");
        read().getPlaces().forEach((Place s) -> {
            Matcher m = p.matcher(s.getName());
            if (m.matches()) {
                list.add(s.getName());
            }
        });

        list.stream().forEach((s) -> {
            System.out.println(s);
        });

    }

    public static void main(String[] args) {
        //readTest();
        //speedTest();
        //sortPlace();
        //regexPlace();

    }

}
