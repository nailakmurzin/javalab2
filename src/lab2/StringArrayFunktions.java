package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringArrayFunktions {

    private static final String regex = "(\\d+[ ]\\d+[ ]\\d+[ ]\\d+);([\\d]+);(.+)";

    public static String[] getHeadTail(String _text, String _separator) {
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

    protected static String[] devidedIntoWords_IndexOf(String _text) {
        List<String> s = new ArrayList();
        int oldIndexOf;
        int indexOf = -1;
        do {
            oldIndexOf = indexOf + 1;
            indexOf = _text.indexOf(";", oldIndexOf);
            if (indexOf > 0) {
                s.add(_text.substring(oldIndexOf, indexOf));
            } else {
                break;
            }
        } while (true);
        s.add(_text.substring(oldIndexOf));//for last string
        return s.toArray(new String[s.size()]);
    }

    protected static String[] devidedIntoWords_Split(String _text) {
        return _text.split(";");
    }

    protected static String[] devidedIntoWords_Regex(String _text) {
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
}
