package app.Prompt;

import static app.General.Operations.scanLine;

public class AnalizPrompt {

  public static void analiz() {
    System.out.println("������� ��� RFA");
    String rfa = scanLine();
    System.out.println("������� ��");
    String ts = scanLine();
    if (ts.equals("��") && !rfa.equals("����������� Change")) {
      System.out.println("������ ����� ���������� ��. ����� ������ �� �������� �� ���������� ��");
    } else {
      System.out.println("����� ����� � ������");
    }
  }
}
