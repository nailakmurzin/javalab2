package lab2.Map;

public abstract class ObjectOnMap {

    private long code = 0;
    private String name = "";

    public ObjectOnMap(long _code, String _name) {
        code = _code;
        name = _name;
    }

    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "|" + getCode() + "|" + getName() + "|";
    }
}
