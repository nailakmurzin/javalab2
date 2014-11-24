package lab2;

import java.util.*;

public class OktmoData {

    private final List<Place> places;

    private final HashSet<String> allStatuses;

    public OktmoData() {
        this.places = new ArrayList<>();
        allStatuses = new HashSet<>();
    }

    public void addPlace(Place _place) {
        places.add(_place);
        allStatuses.add(_place.getStatus());
    }

    public List<Place> getPlaces() {
        return places;
    }

    public String[] getStatues() {
        return allStatuses.toArray(new String[allStatuses.size()]);
    }

    public int size() {
        return places.size();
    }

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
