package lab2.Map;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Region extends Overall {

    private final Map<Integer, District> districts = new TreeMap<>();

    public Region(long _code, String _name) {
        super(_code, _name);
    }

    public static Region getRegion(String... _arr) {
        Long longDigit = Overall.getCode(_arr);
        if (longDigit == null) {
            return null;
        }
        return (longDigit % 1000000) == 0 ? new Region(longDigit, _arr[2]) : null;
    }

    public boolean isPlaceInRegion(Place _p) {
        return _p.getCode() / 1000000000 == this.getCode() / 1000000;
    }

    public Map<Integer, District> getDistricts() {
        return this.districts;
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
