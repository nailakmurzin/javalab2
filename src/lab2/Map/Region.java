package lab2.Map;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Region extends ObjectOnMap {

    private final Map<Integer, District> districts = new TreeMap<>();

    public static Region getRegion(long _code, String _str) {
        return (_code % 1000000) == 0 ? new Region(_code, _str) : null;
    }

    public Region(long _code, String _name) {
        super(_code, _name);
    }

    public boolean isPlaceInRegion(Place _p) {
        return _p.getCode() / 1000000000 == this.getCode() / 1000000;
    }

    public Map<Integer, District> getDistricts() {
        return this.districts;
    }

    public int countPlaces() {
        int count = 0;
        for (Map.Entry<Integer, District> entry : this.getDistricts().entrySet()) {
            District d = entry.getValue();
            count += d.countPlaces();
        }
        return count;
    }

    public boolean add(District _d) {
        int digit = (int) ((_d.getCode() / 1000) % (this.getCode() / 1000));
        if (!isDistrictInRegion(_d) || this.districts.containsKey(digit) || this.districts.containsValue(_d)) {
            return false;
        }
        this.districts.put(digit, _d);
        return true;
    }

    public District getDistrict(int _code) {
        if (this.districts.containsKey(_code)) {
            return this.districts.get(_code);
        }
        return null;
    }

    public boolean isDistrictInRegion(District p) {
        return p.getCode() / 1000000 == this.getCode() / 1000000;
    }

}
