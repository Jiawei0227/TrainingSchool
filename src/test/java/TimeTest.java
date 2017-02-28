import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;


/**
 * Created by Jerry Wang on 2017/2/20.
 */
public class TimeTest {

    @Test
    public void TimeTest(){
         Date date = new Date(System.currentTimeMillis());
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);

         calendar.add(Calendar.YEAR, +1);

         System.out.println(calendar.getTime());
    }
}
