package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OktmoReader {

    private static final String regex = "(\\d+[ ]\\d+[ ]\\d+[ ]\\d+);([\\d]+);([А-Яа-я0-9-+?\\/ ]+)";

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
    public static String[] devidedIntoWords_IndexOf(String _text) {
        List<String> s = new ArrayList();
        int oldIndexOf;
        int indexOf = -1;
        do {
            oldIndexOf = indexOf + 1;
            indexOf = _text.indexOf(";", oldIndexOf);
            if (indexOf > 0) {
                s.add(_text.substring(oldIndexOf, indexOf));
            } else if (oldIndexOf > 0/*last*/) {
                s.add(_text.substring(oldIndexOf));
                break;
            } else {
                //first
                break;
            }
        } while (true);
        return s.toArray(new String[s.size()]);
    }

    public static String[] devidedIntoWords_Split(String _text) {
        return _text.split(";");
    }

    public static String[] devidedIntoWords_Regex(String _text) {
        List<String> s = new ArrayList();
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(_text);
        if (match.matches()) {
            for (int i = 1; i <= match.groupCount(); i++) {
                s.add(match.group(i));
            }
        }
        return s.toArray(new String[s.size()]);
    }

    ///////////////////////////////////
    public Place getPlace(String[] arr) {
        if (arr.length != 3) {
            return null;
        }
        String number = arr[0].replace(" ", "");

        if (number.length() == 0 || arr[2].matches("^[А-Я].+")) {
            return null;
        }
        long longDigit = Long.parseLong(number);
        String[] del = getHeadTail(arr[2], " ");

        return del.length > 1 ? new Place(longDigit, del[1], del[0]) : null;
    }

    //////////////////////////////////
    public String[] readPlaces_IndexOf(String _fileName, OktmoData _data) {
        return this.readPlacesAllMethods(_fileName, _data, OktmoReader::devidedIntoWords_IndexOf);
    }

    public String[] readPlaces_Split(String _fileName, OktmoData _data) {
        return this.readPlacesAllMethods(_fileName, _data, OktmoReader::devidedIntoWords_Split);
    }

    public String[] readPlaces_Regex(String _fileName, OktmoData _data) {
        return this.readPlacesAllMethods(_fileName, _data, OktmoReader::devidedIntoWords_Regex);
    }

    //////////////////////////////////
    private String[] readPlacesAllMethods(String _fileName, OktmoData _data, Function<String, String[]> _switch) {
        int lineCount = 0;
        List<String> noSuitable = new ArrayList();
        BufferedReader bufer = null;

        try {
            bufer = new BufferedReader(new FileReader(_fileName));
            String text;
            while ((text = bufer.readLine()) != null) {
                lineCount++;
                Place place = getPlace(_switch.apply(text));
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

}
