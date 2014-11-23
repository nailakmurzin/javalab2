package lab2;

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

public class OktmoReader {

    public String[] getHeadTail(String _text, String _separator) {
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

    public String[] devidedIntoWords(String _text, String _separator) {
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
            }
        } while (indexOf > 0);
        if (s.isEmpty()) {
            s.add(_text);
        }
        return s.toArray(new String[s.size()]);
    }

    private Place getPlaceFromString(String _text) {
        //String[] arr = _text.split(";");
        String[] arr = this.devidedIntoWords(_text, ";");
        if (arr.length > 0) {
            String number = arr[0].replace(" ", "");
            if (number.length() > 0 && arr.length >= 2) {
                String[] del = getHeadTail(arr[2], " ");
                long LongDigit = Long.parseLong(number);
                if (del.length > 1) {
                    return new Place(LongDigit, del[0], del[1]);
                } else {
                    return new Place(LongDigit, del[0], "");
                }
            }
        }

        return null;
    }

    public String[] readPlaces(String _fileName, OktmoData data) {
        int lineCount = 0;
        List<String> noSuitable = new ArrayList();
        BufferedReader bufer = null;

        try {
            bufer = new BufferedReader(new FileReader(_fileName));
            String text;
            while ((text = bufer.readLine()) != null) {
                lineCount++;
                Place place = this.getPlaceFromString(text);
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
