package lab2.Map;

import java.util.Map;
import java.util.TreeMap;

public class District extends ObjectOnMap {

    private final Map<Integer, Settlement> settlements = new TreeMap<>();

    public static District getDistrict(String... _arr) {
        Long longDigit = District.getCode(_arr);
        if (longDigit == null) {
            return null;
        }
        return (longDigit % 1000) == 0 ? new District(longDigit, _arr[2]) : null;
    }

    public District(long _code, String _name) {
        super(_code, _name);
    }

    public Map<Integer, Settlement> getSettlements() {
        return this.settlements;
    }

    public int countPlaces() {
        int count = 0;
        for (Map.Entry<Integer, Settlement> entry : this.getSettlements().entrySet()) {
            Settlement s = entry.getValue();
            count += s.countPlaces();
        }
        return count;
    }

    public Settlement getSettlement(int _code) {
        if (this.settlements.containsKey(_code)) {
            return this.settlements.get(_code);
        }
        return null;
    }

    public boolean add(Settlement _s) {
        int digit = (int) (_s.getCode() % (this.getCode()));
        if (!isSettlementInDistrict(_s) || this.settlements.containsKey(digit) || this.settlements.containsValue(_s)) {
            return false;
        }
        this.settlements.put(digit, _s);
        return true;
    }

    public boolean isPlaceInDistrict(Place _p) {
        return _p.getCode() / 1000000 == this.getCode() / 1000;
    }

    public boolean isSettlementInDistrict(Settlement _s) {
        return _s.getCode() / 1000 == this.getCode() / 1000;
    }
}
