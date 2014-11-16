package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OktmoReader {

    public void readPlaces(String fileName, OktmoData data) {
        BufferedReader br = null;
        int lineCount = 0;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String s;
            while ((s = br.readLine()) != null) {
                lineCount++;
                System.out.println(s);
                if (lineCount == 20) {
                    break; // пока частично
                }
            }
        } catch (IOException ex) {
            System.out.println("Reading error in line " + lineCount);
            //ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Can not close");
            }
        }
    }
}
