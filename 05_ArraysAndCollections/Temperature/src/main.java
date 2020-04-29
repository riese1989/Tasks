public class main {
    public static void main(String[] args) {
        Double[] temperature = new Double[30];
        Double sumTemperature = 0.0;
        Integer countHealthy = 0;
        for (int i  = 0; i < temperature.length; i++)   {
            temperature[i] = 32 + 8 * Math.random();
            if (temperature[i] >= 36.2 && temperature[i] <= 36.9)   {
                countHealthy++;
            }
            sumTemperature += temperature[i];
        }
        System.out.println("Число здоровых " + countHealthy);
        System.out.println("Средняя температура " + sumTemperature / temperature.length);
    }
}
