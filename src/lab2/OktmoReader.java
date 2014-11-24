package lab2;

import com.sun.javafx.scene.layout.region.Margins.Converter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;

public class OktmoReader {

    protected String[] getHeadTail(String _text, String _separator) {
        int indexOf = _text.indexOf(_separator);
        if (indexOf < 0) {
            return new String[]{_text};
        }
        if (indexOf == 0) {
            return new String[]{""};
        }
        return new String[]{
            _text.substring(0, indexOf),
            _text.substring(indexOf + 1)
        };
    }

    ///////////////////////////////////
    public static String[] devidedIntoWords(String _text, String _separator) {
        List<String> s = new ArrayList();
        int oldIndexOf;
        int indexOf = -1;
        do {
            oldIndexOf = indexOf + 1;
            indexOf = _text.indexOf(";", oldIndexOf);
            if (indexOf > 0) {
                s.add(_text.substring(oldIndexOf, indexOf));
            } else if (oldIndexOf > 0) {
                s.add(_text.substring(oldIndexOf));
                break;
            } else {
                break;
            }
        } while (true);
        if (s.isEmpty()) {
            s.add(_text);
        }
        return s.toArray(new String[s.size()]);
    }

    public static String[] devidedIntoWords_StringToken(String _text, String _separator) {
        List<String> s = new ArrayList();
        StringTokenizer st = new StringTokenizer(_text, _separator, true);
        while (st.hasMoreTokens()) {
            String value = st.nextToken();
            if (_separator.equals(value)) {
                value = null;
            } else if (st.hasMoreTokens()) {
                st.nextToken();
            }
            s.add(value);
        }
        return s.toArray(new String[s.size()]);
    }

    public static String[] devidedIntoWords_Regex(String _text, String _separator) {
        List<String> s = new ArrayList();
        if(_text.matches("^[А-Я].+")){
            
        }
        return s.toArray(new String[s.size()]);
    }

    ///////////////////////////////////
    public Place getPlace(String[] arr) {
        if (arr.length > 0) {
            String number = arr[0].replace(" ", "");
            if (number.length() > 0 && arr.length >= 2) {
                if (arr[2].matches("^[А-Я].+")) {
                    return null;
                }
//                if (arr[2].startsWith("Населенные")) {                    
//                    return null;
//                }
                String[] del = getHeadTail(arr[2], " ");
                long LongDigit = Long.parseLong(number);

                if (del.length > 1) {
                    return new Place(LongDigit, del[1], del[0]);
                } else {
                    //return new Place(LongDigit, del[0], "");
                    return null;
                }
            }
        }
        return null;
    }

    //////////////////////////////////
    public Place getPlaceFromString(String _text) {
        return getPlace(OktmoReader.devidedIntoWords(_text, ";"));
    }

    public Place getPlaceFromString_Split(String _text) {
        return getPlace(_text.split(";"));
    }

    public Place getPlaceFromString_StringToken(String _text) {
        return getPlace(devidedIntoWords_StringToken(_text, ";"));
    }

    public Place getPlaceFromString_Regex(String _text) {
        return getPlace(devidedIntoWords_Regex(_text, ";"));
    }

    //////////////////////////////////
    public String[] readPlaces(String _fileName, OktmoData data) {
        return this.readPlacesAllMethods(_fileName, data, this::getPlaceFromString);
    }

    public String[] readPlaces_Split(String _fileName, OktmoData data) {
        return this.readPlacesAllMethods(_fileName, data, this::getPlaceFromString_Split);
    }

    public String[] readPlaces_StringToken(String _fileName, OktmoData data) {
        return this.readPlacesAllMethods(_fileName, data, this::getPlaceFromString_StringToken);
    }

    public String[] readPlaces_Regex(String _fileName, OktmoData data) {
        return this.readPlacesAllMethods(_fileName, data, this::getPlaceFromString_Regex);
    }

    private String[] readPlacesAllMethods(String _fileName, OktmoData data, Function<String, Place> converter) {
        int lineCount = 0;
        List<String> noSuitable = new ArrayList();
        BufferedReader bufer = null;

        try {
            bufer = new BufferedReader(new FileReader(_fileName));
            String text;
            while ((text = bufer.readLine()) != null) {
                lineCount++;
                Place place = converter.apply(text);
                if (place != null) {
                    data.addPlace(place);
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

}
