package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.transformation.SortedList;

public class OktmoMain {

    private static OktmoData read() {
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        or.readPlaces("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        return data;
    }

    public static long readPlace() {
        long start;
        System.out.printf("//// indexOf ");
        start = System.nanoTime();
        OktmoReader or2 = new OktmoReader();
        OktmoData data2 = new OktmoData();
        or2.readPlaces("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data2);
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

    public static void speedTest() {
        long min1 = -1;
        for (int i = 0; i < 10; i++) {
            long buf = readPlace();
            min1 = (min1 < 0 ? buf : min1);
            min1 = (min1 > buf ? buf : min1);
        }
        long min2 = -1;
        for (int i = 0; i < 10; i++) {
            long buf = readPlace_Split();
            min2 = (min2 < 0 ? buf : min2);
            min2 = (min2 > buf ? buf : min2);
        }
        long min3 = -1;
        for (int i = 0; i < 10; i++) {
            long buf = readPlace();
            min3 = (min3 < 0 ? buf : min3);
            min3 = (min3 > buf ? buf : min3);
        }
        long min4 = -1;
        for (int i = 0; i < 10; i++) {
            long buf = readPlace_Split();
            min4 = (min4 < 0 ? buf : min4);
            min4 = (min4 > buf ? buf : min4);
        }
        System.out.println(min1);
        System.out.println(min2);
        System.out.println(min3);
        System.out.println(min4);

        String s = "4adsasafd4; 3sadfasdfds4; 2sadfasfd2; 1sadfsadsa1";

        long start1 = System.nanoTime();
        s.split(";");
        long time1 = System.nanoTime() - start1;

        long start2 = System.nanoTime();
        OktmoReader.devidedIntoWords(s, ";");
        long time2 = System.nanoTime() - start2;

        System.out.println(time1);
        System.out.println(time2);

        String s2 = "4adsasafd4; 3sadfasdfds4; 2sadfasfd2; 1sadfsadsa1";

        long start3 = System.nanoTime();
        OktmoReader.devidedIntoWords(s2, ";");
        long time3 = System.nanoTime() - start3;

        long start4 = System.nanoTime();
        s2.split(";");
        long time4 = System.nanoTime() - start4;

        long start5 = System.nanoTime();
        OktmoReader.devidedIntoWords_StringToken(s2, ";");
        long time5 = System.nanoTime() - start5;

        System.out.println(time3 + " indexOf");
        System.out.println(time4 + " split");
        System.out.println(time5 + " stringToken");
    }

    public static void readTest() {
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] noSuitable = or.readPlaces("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);

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
        read().getPlaces().forEach((Place s) -> {
            Pattern p = Pattern.compile("^.{0,3}ово$");
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
        regexPlace();

    }

}
