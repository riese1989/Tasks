import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Введите ФИО");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int indexStart = str.indexOf(" ");
        int indexEnd = str.lastIndexOf(" ");
        String family = str.substring(0,indexStart).trim().toLowerCase();
        String correctFamily = family.replaceFirst(family.substring(0,1).trim(), family.substring(0,1).trim().toUpperCase());
        System.out.println("Фамилия: " + correctFamily);
        String name = str.substring(indexStart, indexEnd).trim().toLowerCase();
        String correctName = name.replaceFirst(name.substring(0,1).trim(),name.substring(0,1).trim().toUpperCase());
        System.out.println("Имя: " + correctName);
        String surname = str.substring(indexEnd).trim();
        String correctSurname = surname.replaceFirst(surname.substring(0,1).trim(), surname.substring(0,1).trim().toUpperCase());
        System.out.println("Отчество: " + correctSurname);
    }
}
