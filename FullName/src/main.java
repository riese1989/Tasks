import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Введите ФИО");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String regex = "[^A-Za-zА-Яа-я]";
        String[] splitStr = str.replaceAll(regex," ").split("\\s+");
        String family = splitStr[0].trim().toLowerCase();
        String correctFamily = family.replaceFirst(family.substring(0,1).trim(), family.substring(0,1).trim().toUpperCase());
        System.out.println("Фамилия: " + correctFamily);
        String name = splitStr[1].trim().toLowerCase();
        String correctName = name.replaceFirst(name.substring(0,1).trim(),name.substring(0,1).trim().toUpperCase());
        System.out.println("Имя: " + correctName);
        String surname = splitStr[2].trim();
        String correctSurname = surname.replaceFirst(surname.substring(0,1).trim(), surname.substring(0,1).trim().toUpperCase());
        System.out.println("Отчество: " + correctSurname);
    }
}
