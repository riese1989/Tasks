public class main {
    public static void main(String[] args) {
      String[][] cross = new String[7][7];
      for (int i = 0; i < 7; i++)   {
          for (int j = 0; j < 7; j++)   {
              if (j == i || j == 6 - i) {
                  cross[i][j] = "X";
              }
              else  {
                  cross[i][j] = " ";
              }
              System.out.print(cross[i][j]);
          }
          System.out.println("");
      }
    }
}
