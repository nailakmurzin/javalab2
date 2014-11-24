package lab2;

import java.util.Comparator;

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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        Place entry = (Place) o;
        if (entry == null) {
            throw new ClassCastException();
        }
        return this == entry || (this.code == entry.getCode() && this.name.equals(entry.getName()) && this.status.equals(entry.getStatus()));
    }

    public static class SortedByName implements Comparator<Place> {

        @Override
        public int compare(Place o1, Place o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class SortedByCode implements Comparator<Place> {

        @Override
        public int compare(Place o1, Place o2) {
            if (o1.getCode() > o2.getCode()) {
                return 1;
            } else {
                return o1.getCode() == o2.getCode() ? 0 : -1;
            }
        }

    }

    public static class SortedByStatus implements Comparator<Place> {

        @Override
        public int compare(Place o1, Place o2) {
            if (o1 == null || o2 == null) {
                throw new NullPointerException();
            }
            return o1.getStatus().compareTo(o2.getStatus());
        }

    }

}
