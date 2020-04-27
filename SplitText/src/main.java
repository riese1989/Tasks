public class main {
    public static void main(String[] args) {
        String text = "There's no denying that Fox has been one of the biggest surprises among all NHL rookies and likely will be a part of the foundation on the Rangers defense for many years. However, the one rookie who could make an even bigger impact next season is Shesterkin, a fourth-round pick (No. 118) in the 2014 NHL Draft. The 24-year-old is 10-2-0 and has the third-lowest goals-against average (2.52) and second highest save percentage (.932) among rookie goalies who have started at least 10 games.\n" +
                "\n";
        String regex = "[^A-Za-z']";
        String[] newText = text.replaceAll(regex," ").split("\\s+");
        for (String word:newText)   {
            System.out.println(word);
        }
    }
}
