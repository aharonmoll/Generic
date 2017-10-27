package gigaspaces.geo;

import com.j_spaces.core.geospatial.shapes.Circle;
import com.j_spaces.core.geospatial.shapes.Point;
import com.j_spaces.core.geospatial.shapes.Rectangle;
import com.j_spaces.core.geospatial.shapes.Shape;

/**
 * Created by aharon on 8/31/15.
 */
public class MyPojo {
    @SpaceIndex
    String name;

    //Lucene Query or Space Query or both ?

    @SpaceIndex(type=”geo”)

    Point startingPoint;

    Point endingPoint;


    public static void main(String[] args) {
        Circle circle = factory.Circle(new Coordinate(0, 0), 5);

        myQuery1 = new SQLQuery<MyPojo>(MyPojo.class, "startingPoint geoIntersects ? and name = ?")
                .setParameter(1, circle)
                .setParameter(2, "myname");

        gigaSpace.readMultiple(myQuery1);



        myQuery2 = new SQLQuery<MyPojo>(MyPojo.class, "endingPoint geoIntersects ?")
                .setParameter(1, circle);

        gigaSpace.readMultiple(myQuery2);



        //Will start with name and then filter by intersect
        myQuery3 = new SQLQuery<MyPojo>(MyPojo.class, "endingPoint geoIntersects ? and name = ?")
                .setParameter(1, circle)
                .setParameter(2, "myname");

        gigaSpace.readMultiple(myQuery3);
    }
}

