package lab2.Map;

import java.util.Map;
import java.util.TreeMap;

public class Settlement extends Overall {

    private final Map<Integer, Place> places = new TreeMap<>();

    public Settlement(long _code, String _name) {
        super(_code, _name);
    }

    public Map<Integer, Place> getPlaces() {
        return this.places;
    }

    public boolean add(Place p) {
        int digit = (int) (p.getCode() % (this.getCode() * 1000));
        if (!isPlaceInSettlement(p) || this.places.containsKey(digit) || this.places.containsValue(p)) {
            return false;
        }
        this.places.put(digit, p);
        return true;
    }

    public Place getPlace(int _code) {
        if (this.places.containsKey(_code)) {
            return this.places.get(_code);
        }
        return null;
    }

    public static Settlement getSettlement(String... arr) {
        Long longDigit = Overall.getCode(arr);
        if (longDigit == null) {
            return null;
        }
        return new Settlement(longDigit, arr[2]);
    }

    public boolean isPlaceInSettlement(Place p) {
        return p.getCode() / 1000 == this.getCode();
    }
}
