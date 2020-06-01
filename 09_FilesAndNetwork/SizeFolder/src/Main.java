import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static String path;
    public static void main(String[] args) {
        Double fullSize = 0.0;
        String formatPrint = "#0.00";
        for (;;) {
            System.out.println("Please enter folder's path");
            Scanner scanner = new Scanner(System.in);
            path = scanner.nextLine();
            File folder = new File(path);
            if (folder.isDirectory()) {
                System.out.println(fullSize);
                fullSize = sizeFolder(folder);
                System.out.println(fullSize + "B");
                System.out.println(new DecimalFormat(formatPrint).format(fullSize/1024) + "KB");
                System.out.println(new DecimalFormat(formatPrint).format(fullSize/1024/1024) + "MB");
                System.out.println(new DecimalFormat(formatPrint).format(fullSize/1024/1024/1024) + "GB");
            } else {
                System.out.println(path + "is not folder!");
            }
        }
    }
    public static Double sizeFolder (File folder)  {
        Double fullSize = 0.0;
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                fullSize += sizeFolder(file);
            }
            else {
                System.out.println(file.getAbsolutePath().replace(path,""));
                fullSize += file.length();
            }
        }
        return fullSize;
    }
}
