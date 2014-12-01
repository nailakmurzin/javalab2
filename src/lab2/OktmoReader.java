package lab2;

import lab2.Map.Place;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lab2.Map.*;

public class OktmoReader {

    protected String[] readPlacesAllMethods(String _fileName, OktmoData _data, Function<String, String[]> _switch) {
        int lineCount = 0;
        List<String> noSuitable = new ArrayList();
        BufferedReader bufer = null;

        try {
            bufer = new BufferedReader(new FileReader(_fileName));
            String text;
            while ((text = bufer.readLine()) != null) {
                lineCount++;
                Place place = Place.getPlace(_switch.apply(text));
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
            bufer = new BufferedReader(new FileReader(_fileName));
            String text;
            while ((text = bufer.readLine()) != null) {
                lineCount++;
                Region region = Region.getRegion(_switch.apply(text));
                District district = District.getDistrict(_switch.apply(text));
                Settlement settlement = Settlement.getSettlement(_switch.apply(text));
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
