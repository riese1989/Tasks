package Prompt;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static General.Operations.scanLine;
import static Menu.Menu.menu;
import static Prompt.AnalizPrompt.analiz;

public class MenuPrompt {
    public static void menuPrompt() throws IOException, ParseException {
        System.out.println("==================");
        boolean flag = true;
        while (flag)    {
            System.out.println("Меню подсказок");
            System.out.println("1. Анализ - разработка АР - Оценка");
            System.out.println("q: Вернуться к общему меню");
            String command = scanLine();
            switch (command)    {
                case "1":   {
                    analiz();
                }
                case "q":   {
                    menu();
                    break;
                }
                default:    {
                    flag = false;
                    System.out.println("Неправильная комманда");
                }
            }
    }
    }
}
