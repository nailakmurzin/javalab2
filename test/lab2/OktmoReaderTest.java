/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    public void getPlaceFromString() throws UnsupportedEncodingException {
        PrintWriter console = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] readPlaces = or.readPlaces("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        System.out.print(data.size());
        Place[] places = data.getPlaces().toArray(new Place[data.size()]);
        if (places.length > 9) {
            System.out.print(places[9]);
        }
        System.out.print(places[places.length - 1]);

    }

}
