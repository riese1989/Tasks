package general;

import app.general.Operations;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class OperationsTest {

  @Test
  public void compare_date_test_current_date() {
    Date date = new Date();
    boolean result = Operations.compareDate(date);
    Assert.assertTrue(result);
  }

  @Test
  public void compare_date_test_other_date() {
    Date date = new Date();
    date.setTime(616754892123L);
    boolean result = Operations.compareDate(date);
    Assert.assertFalse(result);
  }

  @Test
  public void is_now_date_test()  {
    Date date = new Date();
    boolean result = Operations.isNowDate(date);
    Assert.assertTrue(result);
  }

  @Test
  public void is_now_date_test_not_now() {
    Date date = new Date();
    date.setTime(616754892123L);
    boolean result = Operations.isNowDate(date);
    Assert.assertFalse(result);
  }
}
