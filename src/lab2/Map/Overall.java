package lab2.Map;

public class Overall {

    private long code = 0;
    private String name = "";

    public Overall(long _code, String _name) {
        code = _code;
        name = _name;
    }

    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Long getCode(String... _arr) {
        if (_arr.length < 3) {
            return null;
        }
        String number = _arr[0].replace(" ", "");

        if (number.length() == 0 || _arr[2].isEmpty()) {
            return null;
        }
        Long digit = null;
        try {
            digit = Long.parseLong(number);
        } catch (Exception ex) {
            digit = null;
        }
        return digit;
    }

    @Override
    public String toString() {
        return "|" + getCode() + "|" + getName() + "|";
    }
}
