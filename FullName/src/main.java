import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Введите ФИО");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int indexStart = str.indexOf(" ");
        int indexEnd = str.lastIndexOf(" ");
        System.out.println("Фамилия: " + str.substring(0,indexStart).trim());
        System.out.println("Имя: " + str.substring(indexStart,indexEnd).trim());
        System.out.println("Отчество: " + str.substring(indexEnd).trim());
    }
}
