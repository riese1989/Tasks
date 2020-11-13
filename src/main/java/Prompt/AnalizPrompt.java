package Prompt;

import static General.Operations.scanLine;

public class AnalizPrompt {
    public static void analiz() {
        System.out.println("Введите тип RFA");
        String rfa = scanLine();
        System.out.println("Введите ТС");
        String ts = scanLine();
        if (ts.equals("КЦ") && !rfa.equals("Стандартный Change"))   {
            System.out.println("Только через разработка АР. Нужно нажать на Передать на разработку АР");
        }
        else {
            System.out.println("Можно сразу в оценку");
        }
    }
}
