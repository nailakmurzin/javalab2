package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class OktmoMain {

    public static void main(String[] args) {
        BufferedReader br = null;
        int lineCount = 0;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv"));
            String s;
            while ((s = br.readLine()) != null) {
                lineCount++;
                String[] arr = s.split(";");
                if(arr.length==0){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
                    //break;
                }
                System.out.println(lineCount + "\t " + Arrays.toString(arr));
                
            }
        } catch (IOException ex) {
            System.out.println("Reading error in line " + lineCount);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println("Can not close");
            }
        }
    }

}
