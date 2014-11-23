package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OktmoMain {

    public static void main(String[] args) {
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] noSuitable = or.readPlaces("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        System.out.println(data);
        System.out.println(Arrays.toString(noSuitable));
    }

}
