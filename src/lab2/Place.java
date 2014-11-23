package lab2;

public class Place {

    private long code = 0;
    private String name = "";
    private String status = "";

    public Place(long _code, String _name, String _status) {
        code = _code;
        name = _name;
        status = _status;
    }

    @Override
    public String toString() {
        return "|" + code + "|" + name + "|" + status + "|";
    }

}
