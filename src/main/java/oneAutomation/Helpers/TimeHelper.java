package oneAutomation.Helpers;

/**
 * Created by Ati on 21-02-2016.
 */
public class TimeHelper {
    public static void pause(double secs){
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < (start + (secs*1000))){

        }
    }

}
