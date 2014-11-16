package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OktmoMain {

    public static void main(String[] args) {
        BufferedReader br = null;
        int lineCount = 0;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Nail\\Desktop\\JAVA\\lab2\\tom5_oktmo_2.csv"));
            String s;
            while ((s = br.readLine()) != null) {
                lineCount++;
                System.out.println(lineCount + "\t " + s);
//                if (lineCount == 20) {
//                    break; // пока частично
//                }
            }
        } catch (IOException ex) {
            System.out.println("Reading error in line " + lineCount);
            ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Can not close");
            }
        }
    }

}
