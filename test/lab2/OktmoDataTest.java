/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nail
 */
public class OktmoDataTest {

    public OktmoDataTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {

    }

    /**
     * Test of toString method, of class OktmoData.
     */
    @Test
    public void testToString() {
        OktmoReader or = new OktmoReader();
        OktmoData data = new OktmoData();
        String[] readPlaces = or.readPlaces("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv", data);
        System.out.print(data);
    }

}
