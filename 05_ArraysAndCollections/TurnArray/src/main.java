public class main {
    public static void main(String[] args) {
        String color = "Каждый охотник желает знать, где сидит фазан";
        String[] colorWords = color.split(",?\\s");
        String[] colorWordsTurn = new String[colorWords.length];
        int j = colorWords.length-1;
        for (int i = 0; i < colorWords.length; i++, j--)    {
            colorWordsTurn[j] = colorWords[i];
        }
        colorWords = colorWordsTurn;
        for (String colorWord : colorWords) {
            System.out.println(colorWord);
        }
    }
}
