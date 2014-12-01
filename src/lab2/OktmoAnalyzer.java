package lab2;

import java.util.*;
import lab2.Map.*;

public class OktmoAnalyzer {

    private final OktmoData _data;

    public OktmoAnalyzer(OktmoData data) {
        this._data = data;
    }

    public Iterable<Settlement> findSettlementWithMaxPlaces() {
        Map<Settlement, Integer> rMap = new TreeMap<>();
        List<Map.Entry<Settlement, Integer>> list = new ArrayList<>(rMap.entrySet());
        List<Settlement> rList = new ArrayList<>();

        for (Settlement r : this._data.getSettlements()) {
            rMap.put(r, r.countPlaces());
        }

        Collections.sort(list, (Map.Entry<Settlement, Integer> e1, Map.Entry<Settlement, Integer> e2) -> {
            return e1.getValue().compareTo(e2.getValue());
        });
        for (Map.Entry<Settlement, Integer> i : list) {
            rList.add(i.getKey());
        }
        return rList;
    }

    public Iterable<District> findDistrictWithMaxPlaces() {
        Map<District, Integer> dMap = new TreeMap<>();
        List<Map.Entry<District, Integer>> list = new ArrayList<>(dMap.entrySet());
        List<District> dList = new ArrayList<>();

        for (District d : this._data.getDistricts()) {
            dMap.put(d, d.countPlaces());
        }

        Collections.sort(list, (Map.Entry<District, Integer> e1, Map.Entry<District, Integer> e2) -> {
            return e1.getValue().compareTo(e2.getValue());
        });
        for (Map.Entry<District, Integer> i : list) {
            dList.add(i.getKey());
        }
        return dList;
    }

    public Iterable<Region> findRegionWithMaxPlaces() {
        Map<Region, Integer> rMap = new TreeMap<>();
        List<Map.Entry<Region, Integer>> list = new ArrayList<>(rMap.entrySet());
        List<Region> rList = new ArrayList<>();

        for (Region r : this._data.getRegions()) {
            rMap.put(r, r.countPlaces());
        }

        Collections.sort(list, (Map.Entry<Region, Integer> e1, Map.Entry<Region, Integer> e2) -> {
            return e1.getValue().compareTo(e2.getValue());
        });
        for (Map.Entry<Region, Integer> i : list) {
            rList.add(i.getKey());
        }
        return rList;
    }
}
