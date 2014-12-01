package lab2;

import lab2.Map.Place;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lab2.Map.District;
import lab2.Map.Region;
import lab2.Map.Settlement;

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
        for (String s : data.getPlaceStatues()) {
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

    public static void getPlace_Regex() {
        long start = System.nanoTime();
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] readPlaces = or.readPlaces_Regex("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        long time = System.nanoTime() - start;
        System.out.println(time + "//// Regex time = ");
        System.out.println("Size = " + data.placesSize());
        for (String s : readPlaces) {
            if (!s.contains("Населенные пункты") && !s.contains(";;")) {
                System.out.println(s);
            }
        }

    }

    public static void getPlace_IndexOf() {
        long start = System.nanoTime();
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] readPlaces = or.readPlaces_IndexOf("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        long time = System.nanoTime() - start;
        System.out.println(time + "//// indexOf time = ");
        System.out.println("Size = " + data.placesSize());
        for (String s : readPlaces) {
            if (!s.contains("Населенные пункты") && !s.contains(";;")) {
                System.out.println(s);
            }
        }

    }

    public static void getPlaceFirstChar() {
        OktmoData data = read();
        List<Place> list = OktmoData.getPlacesApplyingForName_Regex("^([а-я]).+\\1$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE, data.getPlaces());
        for (Place p : list) {
            System.out.println(p);
        }
    }

    public static void readRegionsDistinctsSettlements() {
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] readRegionsDistinctsSettlements = or.readRegionsDistinctsSettlements("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_1.csv", data);
//        for (String s : readRegionsDistinctsSettlements) {
//            System.out.println(s);
//        }
        /////////////Regions 000 000
//        System.out.println("//////getRegions().size() " + data.getRegions().size());
//        for (Region r : data.getRegions()) {
//            System.out.println(r);
//        }
//        /////////////Districts 000
//        System.out.println("//////Districts().size() " + data.getDistricts().size());
//        for (District d : data.getDistricts()) {
//            System.out.println(d);
//        }
//        /////////////Settlements *
//        System.out.println("//////getSettlements().size() " + data.getSettlements().size());
//        for (Settlement s : data.getSettlements()) {
//            System.out.println(s);
//        }
        String[] readPlaces = or.readPlaces_IndexOf("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);

        data.associatePlaces();
        for (Place p : data.getPlaces()) {
            District d = data.findDistrict(p);
            Region r = data.findRegion(p);
            Settlement s = data.findSettlements(p);
            if (d != null) {
                System.out.println(d + " District " + p);
            }
            if (r != null) {
                System.out.println(r + " Region " + p);
            }
            if (s != null) {
                System.out.println(s + " Settlement " + p);
            }
        }
        System.out.println();
    }

    public static void readRegionsDistinctsSettlements2() {
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] readRegionsDistinctsSettlements = or.readRegionsDistinctsSettlements("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_1.csv", data);
        String[] readPlaces = or.readPlaces_IndexOf("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);

        data.associatePlaces();
        
        System.out.println(data.getPlaces().size());
        System.out.println();
        System.out.println(data.getSettlements().size());
        System.out.println();
        System.out.println(data.getDistricts().size());
        System.out.println();
        System.out.println(data.getRegions().size());

        for (Region r : data.getRegions()) {
            System.out.println(r);
        }
        OktmoAnalyzer a = new OktmoAnalyzer(data);
        Settlement s = a.findMaxSettlement();
        System.out.println(s.countPlaces() + " "+ s);
    }

    public static void main(String[] args) {
        //readTest();
        //speedTest();
        //sortPlace();
        //regexPlace();

        //getPlace_Regex();
        //getPlace_IndexOf();
        //getPlaceFirstChar();
        //readRegionsDistinctsSettlements();
        readRegionsDistinctsSettlements2();

    }

}
