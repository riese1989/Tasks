package Inic;

import static General.Operations.scanInteger;

public class OperationsIniciators {
    public static void top() {
        IniciatorComparator iniciatorComparator = new IniciatorComparator();
        Iniciator.listIniciators.sort(iniciatorComparator);
        Integer limit = 0;
        while (true)    {
            limit = scanInteger("Введите лимит");
            if (limit > 0)  {
                break;
            }
            System.out.println("Лимит должен быть больше 0");
        }
        System.out.println("Топ-" + limit + " инициаторов");
        Integer i = 1;
        for (Iniciator iniciator : Iniciator.listIniciators)    {
            if (iniciator.getName().equals("")) {
                continue;
            }
            System.out.println(i + " " + iniciator.getName() + " " + iniciator.getCountTasks());
            i++;
            if (i > limit)    {
                break;
            }
        }
    }
}
