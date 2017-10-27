/**
 * Created by aharon on 3/10/17.
 */
import java.lang.reflect.Method;


public class PlatformVersionExample {

    public static void getVersion(String fullClassName) {
        try {
            Class clazz = Class.forName(fullClassName);

            Method m = clazz.getDeclaredMethod("getVersion", null);
            System.out.println("Got method: " + m);
            Object o = m.invoke(null, null);
            System.out.println("Output: " + o);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

        System.out.println("Checking version 12...");
        getVersion("com.gigaspaces.internal.version.PlatformVersion");

        try {
            Thread.sleep(5000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Checking versions below 12...");
        getVersion("com.j_spaces.kernel.PlatformVersion");

    }
}