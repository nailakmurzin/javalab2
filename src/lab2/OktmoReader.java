package lab2;

import lab2.Map.Place;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lab2.Map.*;

public class OktmoReader {

    public long getCode(String str) {
        long digit = -1;
        String number = str.replace(" ", "");
        if (number.isEmpty()) {
            return digit;
        }

        try {
            digit = Long.parseLong(number);
        } catch (Exception ex) {
            
        }
        return digit;
    }

    protected String[] readPlacesAllMethods(String _fileName, OktmoData _data, Function<String, String[]> _switch) {
        int lineCount = 0;
        List<String> noSuitable = new ArrayList();
        BufferedReader bufer = null;

        try {
            bufer = new BufferedReader(new InputStreamReader(new FileInputStream(_fileName), "utf8"));
            String text;
            while ((text = bufer.readLine()) != null) {

                lineCount++;
                String[] a = _switch.apply(text);

                long code = -1;
                if (a.length < 3 || a[2].isEmpty()) {
                    noSuitable.add(text);
                    continue;
                }
                code = getCode(a[0]);
                if (code == -1) {
                    noSuitable.add(text);
                    continue;
                }

                Place place = Place.getPlace(code, a[2]);
                if (place != null) {
                    _data.addPlace(place);
                } else {
                    noSuitable.add(text);
                }
            }
        } catch (Exception ex) {
            System.out.println("Reading error in line " + lineCount + " " + Arrays.toString(ex.getStackTrace()));
        } finally {
            try {
                bufer.close();
            } catch (IOException ex) {
                System.out.println("Can not close! " + ex.getMessage());
            }
        }
        return noSuitable.toArray(new String[noSuitable.size()]);
    }

    protected String[] readRegionsDistinctsSettlementsAllMethods(String _fileName, OktmoData _data, Function<String, String[]> _switch) {
        int lineCount = 0;
        List<String> noSuitable = new ArrayList();
        BufferedReader bufer = null;

        try {
            bufer = new BufferedReader(new InputStreamReader(new FileInputStream(_fileName), "utf8"));
            String text;
            while ((text = bufer.readLine()) != null) {
                lineCount++;
                String[] a = _switch.apply(text);

                long code = -1;
                if (a.length < 3 || a[2].isEmpty()) {
                    noSuitable.add(text);
                    continue;
                }
                code = getCode(a[0]);
                if (code == -1) {
                    noSuitable.add(text);
                    continue;
                }

                Region region = Region.getRegion(code, a[2]);
                District district = District.getDistrict(code, a[2]);
                Settlement settlement = Settlement.getSettlement(code, a[2]);
                if (region != null) {
                    _data.addRegion(region);
                } else if (district != null) {
                    _data.addDistrict(district);
                } else if (settlement != null) {
                    _data.addSettlement(settlement);
                } else {
                    noSuitable.add(text);
                }
            }
        } catch (Exception ex) {
            System.out.println("Reading error in line " + lineCount + " " + Arrays.toString(ex.getStackTrace()));
        } finally {
            try {
                bufer.close();
            } catch (IOException ex) {
                System.out.println("Can not close! " + ex.getMessage());
            }
        }
        return noSuitable.toArray(new String[noSuitable.size()]);
    }

    //////////////////////////////////readPlaces
    public String[] readPlaces_IndexOf(String _fileName, OktmoData _data) {
        return this.readPlacesAllMethods(_fileName, _data, StringArrayFunktions::devidedIntoWords_IndexOf);
    }

    public String[] readPlaces_Split(String _fileName, OktmoData _data) {
        return this.readPlacesAllMethods(_fileName, _data, StringArrayFunktions::devidedIntoWords_Split);
    }

    public String[] readPlaces_Regex(String _fileName, OktmoData _data) {
        return this.readPlacesAllMethods(_fileName, _data, StringArrayFunktions::devidedIntoWords_Regex);
    }

    //////////////////////////////////////readRegionsDistinctsSettlements
    public String[] readRegionsDistinctsSettlements(String _fileName, OktmoData _data) {
        return this.readRegionsDistinctsSettlementsAllMethods(_fileName, _data, StringArrayFunktions::devidedIntoWords_IndexOf);
    }
}
