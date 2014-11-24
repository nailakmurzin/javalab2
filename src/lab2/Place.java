package lab2;

public class Place implements Comparable {

    private long code = 0;
    private String name = "";
    private String status = "";

    public Place(long _code, String _name, String _status) {
        code = _code;
        name = _name;
        status = _status;
    }

    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "|" + code + "|" + name + "|" + status + "|";
    }

    @Override
    public int compareTo(Object o) {
        Place entry = (Place) o;
        if (entry == null) {
            throw new ClassCastException();
        }

        int result = this.name.compareTo(entry.getName());
        if (result != 0) {
            return result;
        }
        if (this.code > entry.getCode()) {
            return 1;
        } else if (this.code < entry.getCode()) {
            return -1;
        }
        result = this.status.compareTo(entry.getStatus());
        if (result != 0) {
            return result;
        }
        return 0;
    }

}
