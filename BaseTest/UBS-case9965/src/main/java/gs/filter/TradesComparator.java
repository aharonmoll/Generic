package gs.filter;

import com.gigaspaces.document.SpaceDocument;

import java.util.Comparator;

/**
 * Created by aharon on 9/4/16.
 */
public class TradesComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        Integer p1 = ((SpaceDocument) o1).getProperty("id");
        Integer p2 = ((SpaceDocument) o2).getProperty("id");
        if (p1 > p2) {
            return 1;
        } else if (p1 < p2){
            return -1;
        } else {
            return 0;
        }
    }
}
