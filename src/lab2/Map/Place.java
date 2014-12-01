package lab2.Map;

import lab2.StringArrayFunktions;
import java.util.Comparator;

public class Place extends ObjectOnMap implements Comparable {

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

    private String status = "";

    public static Place getPlace(String[] _arr) {

        Long longDigit = ObjectOnMap.getCode(_arr);
        if (longDigit == null || _arr[2].matches("^[А-Я].+")) {
            return null;
        }
        String[] del = StringArrayFunktions.getHeadTail(_arr[2], " ");

        return del.length > 1 ? new Place(longDigit, del[1], del[0]) : null;
    }

    public Place(long _code, String _name, String _status) {
        super(_code, _name);
        status = _status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public int compareTo(Object o) {
        Place entry = (Place) o;
        if (entry == null) {
            throw new ClassCastException();
        }

        int result = this.getName().compareTo(entry.getName());
        if (result != 0) {
            return result;
        }
        if (this.getCode() > entry.getCode()) {
            return 1;
        } else if (this.getCode() < entry.getCode()) {
            return -1;
        }
        result = this.getStatus().compareTo(entry.getStatus());
        if (result != 0) {
            return result;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        Place entry = (Place) o;
        if (entry == null) {
            throw new ClassCastException();
        }
        return this == entry || (this.getCode() == entry.getCode() && this.getName().equals(entry.getName()) && this.getStatus().equals(entry.getStatus()));
    }

    @Override
    public String toString() {
        return "|" + getCode() + "|" + getName() + "|" + getStatus() + "|";
    }

}
