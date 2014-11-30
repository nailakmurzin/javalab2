/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import lab2.Map.Place;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nail
 */
public class OktmoReaderTest {

    public OktmoReaderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void getPlace_IndexOf() throws UnsupportedEncodingException {
        long start = System.nanoTime();
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] readPlaces = or.readPlaces_IndexOf("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        long time = System.nanoTime() - start;
        System.out.println(time + "//// indexOf time = ");
        System.out.println("Size = " + data.placesSize());
        Place[] places = data.getPlaces().toArray(new Place[data.placesSize()]);
        if (places.length > 9) {
            System.out.print(places[9]);
        }
        System.out.print(places[places.length - 1]);

    }

    @Test
    public void getPlace_Regex() {
        long start = System.nanoTime();
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] readPlaces = or.readPlaces_Regex("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        long time = System.nanoTime() - start;
        System.out.println(time + "//// Regex time = ");
        System.out.println("Size = " + data.placesSize());
    }

    @Test
    public void placeEquals() {
        OktmoReader or = new OktmoReader();
        OktmoData data1 = new OktmoData();
        String[] readPlaces1 = or.readPlaces_IndexOf("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data1);
        OktmoData data2 = new OktmoData();
        String[] readPlaces2 = or.readPlaces_Regex("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data2);

        Place[] places = data1.getPlaces().toArray(new Place[data1.getPlaces().size()]);
        for (int i = 0; i < data1.getPlaces().size(); i++) {
            for (int j = 0; j < data1.getPlaces().size(); j++) {
                if (i != j) {
                    assertFalse(places[i].equals(places[j]));
                } else {
                    assertTrue(places[i].equals(places[j]));
                }
            }
        }

        assertTrue(Arrays.equals(readPlaces1, readPlaces2));

    }
}
