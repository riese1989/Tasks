package Inic;

import static General.Operations.scanInteger;

public class OperationsIniciators {
    public static void top() {
        IniciatorComparator iniciatorComparator = new IniciatorComparator();
        Iniciator.listIniciators.sort(iniciatorComparator);
        Integer limit = 0;
        while (true)    {
            limit = scanInteger("������� �����");
            if (limit > 0)  {
                break;
            }
            System.out.println("����� ������ ���� ������ 0");
        }
        System.out.println("���-" + limit + " �����������");
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
