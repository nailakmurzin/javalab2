package lab2.Map;

import java.util.Map;
import java.util.TreeMap;

public class District extends Overall {

    private final Map<Integer, Settlement> settlements = new TreeMap<>();

    public District(long _code, String _name) {
        super(_code, _name);
    }

    public static District getDistrict(String... _arr) {
        Long longDigit = Overall.getCode(_arr);
        if (longDigit == null) {
            return null;
        }
        return (longDigit % 1000) == 0 ? new District(longDigit, _arr[2]) : null;
    }

    public boolean isPlaceInDistrict(Place _p) {
        return _p.getCode() / 1000000 == this.getCode() / 1000;
    }

    public Map<Integer, Settlement> getSettlements() {
        return this.settlements;
    }

    public boolean isSettlementInDistrict(Settlement _s) {
        return _s.getCode() / 1000 == this.getCode() / 1000;
    }

    public boolean add(Settlement _s) {
        int digit = (int) (_s.getCode() % (this.getCode()));
        if (!isSettlementInDistrict(_s) || this.settlements.containsKey(digit) || this.settlements.containsValue(_s)) {
            return false;
        }
        this.settlements.put(digit, _s);
        return true;
    }

    public Settlement getSettlement(int _code) {
        if (this.settlements.containsKey(_code)) {
            return this.settlements.get(_code);
        }
        return null;
    }
}
