import java.util.HashSet;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        boolean nextIteration = true;
        HashSet<String> listMails = new HashSet<>();
        String comandLine;
        Scanner scanner;
        while (nextIteration) {
            System.out.println("Введите команду");
            scanner = new Scanner(System.in);
            comandLine = scanner.nextLine();
            String[] splitComand = comandLine.split(" ", 2);
            switch (splitComand[0].toUpperCase()) {
                case "LIST": {
                    for (String listMail : listMails) {
                        System.out.println(listMail);
                    }
                    break;
                }
                case "ADD": {
                    if (listMails.contains(splitComand[1].toLowerCase())) {
                        System.out.println("Email " + splitComand[1].toUpperCase() + " уже добавлен");
                    } else {
                        listMails.add(splitComand[1].toLowerCase());
                    }
                    break;
                }
                case "EXIT": {
                    nextIteration = false;
                    break;
                }
            }
        }
        System.out.println("Программа завершена");

    }
}
