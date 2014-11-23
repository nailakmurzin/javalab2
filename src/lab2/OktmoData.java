package lab2;

import java.util.*;

public class OktmoData {

    private final List<Place> places;

    public OktmoData() {
        this.places = new ArrayList();
    }

    public void addPlace(Place _place) {
        places.add(_place);
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
