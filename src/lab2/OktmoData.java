package lab2;

import lab2.Map.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OktmoData {

    private final List<Place> places;
    private final List<Region> regions;
    private final List<District> districts;
    private final List<Settlement> settlements;

    private final HashSet<String> allPlaceStatuses;

    public OktmoData() {
        this.places = new ArrayList<>();
        this.regions = new ArrayList<>();
        this.districts = new ArrayList<>();
        this.settlements = new ArrayList<>();

        allPlaceStatuses = new HashSet<>();

    }

    //////////////////////////////Place    
    public void addPlace(Place _place) {
        places.add(_place);
        allPlaceStatuses.add(_place.getStatus());
    }

    public void addPlaceAll(Iterable<Place> _places) {
        for (Place p : _places) {
            this.addPlace(p);
        }
    }

    public List<Place> getPlaces() {
        return places;
    }

    public static List<Place> getPlacesApplyingForName_Regex(String _namePattern, int flag, Iterable<Place> _places) {
        List<Place> list = new ArrayList<>();
        Pattern p = Pattern.compile(_namePattern, flag);
        if (_namePattern.length() > 0) {
            for (Place pl : _places) {
                Matcher m = p.matcher(pl.getName());
                if (m.matches()) {
                    list.add(pl);
                }
            }
        }
        return list;
    }

    public static List<Place> getPlacesApplyingForStatus_Regex(String _namePattern, int flag, Iterable<Place> _places) {
        List<Place> list = new ArrayList<>();
        Pattern p = Pattern.compile(_namePattern, flag);
        if (_namePattern.length() > 0) {
            for (Place pl : _places) {
                Matcher m = p.matcher(pl.getStatus());
                if (m.matches()) {
                    list.add(pl);
                }
            }
        }
        return list;
    }

    public String[] getPlaceStatues() {
        return allPlaceStatuses.toArray(new String[allPlaceStatuses.size()]);
    }

    public int placesSize() {
        return places.size();
    }

    public void associatePlaces() {
        List<Place> pl = new ArrayList<>();
        pl.addAll(this.places);
        for (Settlement s : this.settlements) {
            Iterator<Place> i = pl.iterator();
            while (i.hasNext()) {
                Place p = i.next();
                if (s.add(p)) {
                    i.remove();
                }
            }
        }
    }

    /////////////////////////Regions
    public void addRegion(Region _region) {
        this.regions.add(_region);
    }

    public List<Region> getRegions() {
        return regions;
    }

    ////////////////////////Districts
    public void addDistrict(District _district) {
        this.districts.add(_district);
    }

    public List<District> getDistricts() {
        return districts;
    }

    ///////////////////////Settlements
    public void addSettlement(Settlement _settlement) {
        this.settlements.add(_settlement);
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }

    //////////////////////////////////
    public Region findRegion(Place p) {
        for (Region r : this.regions) {
            if (r.isPlaceInRegion(p)) {
                return r;
            }
        }
        return null;
    }

    public District findDistrict(Place p) {
        for (District d : this.districts) {
            if (d.isPlaceInDistrict(p)) {
                return d;
            }
        }
        return null;
    }

    public Settlement findSettlements(Place p) {
        for (Settlement s : this.settlements) {
            if (s.isPlaceInSettlement(p)) {
                return s;
            }
        }
        return null;
    }

    //////////////////////////////////
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder(50);
        for (Place s : places) {
            text.append(s);
            text.append('\n');
        }
        return text.toString();
    }

}
