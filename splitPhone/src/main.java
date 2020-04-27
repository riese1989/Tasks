import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Введите номер телефона");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine();
        String regex = "[^0-9]";
        String correctPhone = phone.replaceAll(regex,"");
        System.out.println(correctPhone);
    }
}
