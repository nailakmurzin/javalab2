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
public class OktmoReaderTest {
    
    public OktmoReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getHeadTail method, of class OktmoReader.
     */
    @Test
    public void testGetHeadTail() {
        OktmoReader or = new OktmoReader();
        OktmoData data = or.readPlaces("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv");
    }
    
}
