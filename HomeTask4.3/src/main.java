import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countBox = scanner.nextInt();
        int capacityTracks = 12;
        int capacityContainers = 27;
        int countTracks = ((double) countBox % (capacityTracks * capacityContainers) == 0) ?
                countBox / (capacityTracks * capacityContainers) :
                countBox / (capacityTracks * capacityContainers) + 1;
        int remainingBox = countBox;
        int numberBox = 1;
        for (int i = 1; i <= countTracks; i++) {
            System.out.println("Грузовик " + i + ":\n");
            for (int j = 1; j <= capacityTracks; j++) {
                System.out.println("\tКонтейнер " + j + ":\n");
                for (int k = 1; k <= capacityContainers && remainingBox > 0; k++) {
                    System.out.println("\t\tЯщик " + numberBox);
                    remainingBox--;
                    numberBox++;
                }
                System.out.print("\n");
                if (remainingBox == 0) {
                    break;
                }
            }
            System.out.print("\n");
        }
    }
}
