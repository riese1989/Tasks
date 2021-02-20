package General;

import Tasks.TaskStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class Operations {

  public static boolean compareDate(Date dateResolved) {
    Calendar calNow = dateToCalendar(new Date());
    Calendar calResolved = dateToCalendar(dateResolved);
    if ((calNow.get(Calendar.DAY_OF_MONTH) == calResolved.get(Calendar.DAY_OF_MONTH)) &&
        (calNow.get(Calendar.MONTH) == calResolved.get(Calendar.MONTH)) &&
        (calNow.get(Calendar.YEAR) == calResolved.get(Calendar.YEAR))) {
      return true;
    }
    return false;
  }

  private static Calendar dateToCalendar(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  public static String scanLine() {
    Scanner scanner = new Scanner(System.in);
    String string = scanner.nextLine();
    log(string, "", "ETasks");
    return string;
  }

  public static void log(String message, String number, String nameLogger) {
    Logger logger = LogManager.getLogger(nameLogger);
    logger.info(message + number);
  }

  //сканнер числа ввода из коммандной строки
  public static Integer scanInteger(String message) {
    String string;
    for (; ; ) {
      System.out.println(message);
      string = scanLine();
      if (string.matches("[-+]?\\d+")) {
        return Integer.parseInt(string);
      }
      System.out.println("Вы ввели не число");
    }
  }

  public static boolean isNowDate(Date date) {
    long dayInMill = 24 * 60 * 60 * 1000;
    long startDay = ((new Date()).getTime()) / dayInMill * dayInMill;
    long endDay = startDay + dayInMill;
    long dateTime = date.getTime();
    return dateTime >= startDay && dateTime <= endDay;
  }

  public static TaskStatus convertToStatus(String status) {
    switch (status) {
      case "DONE": {
        return TaskStatus.DONE;
      }
      case "NOTE_DONE": {
        return TaskStatus.NOTE_DONE;
      }
      case "WAITING": {
        return TaskStatus.WAITING;
      }
      case "NOT_US": {
        return TaskStatus.NOT_US;
      }
      case "TASK": {
        return TaskStatus.TASK;
      }
    }
    return TaskStatus.NOTE_DONE;
  }

//    public static String translate(String string)   {
//        string.replace("ЗНО", "ZNO").replace("ИНЦ","INC");
//        return string;
//    }

  public static String statusToString(TaskStatus status) {
    switch (status) {
      case DONE: {
        return "DONE";
      }
      case NOTE_DONE: {
        return "NOTE_DONE";
      }
      case NOT_US: {
        return "NOT_US";
      }
      case WAITING: {
        return "WAITING";
      }
      case TASK: {
        return "TASK";
      }
    }
    return "";
  }
}
