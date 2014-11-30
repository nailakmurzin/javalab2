package lab2.Map;

public class StringFunktions {

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
}
