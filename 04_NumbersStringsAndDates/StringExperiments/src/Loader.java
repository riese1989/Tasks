
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int indexRublesEndVasya = text.indexOf(" рублей");
        int indexRublesStartVasya = text.substring(0,indexRublesEndVasya).lastIndexOf(" ");
        String casheVasya = text.substring(indexRublesStartVasya, indexRublesEndVasya).trim();
        int indexRublesEndMasha = text.lastIndexOf(" рублей");
        int indexRublesStartMasha = text.substring(0, indexRublesEndMasha).lastIndexOf(" ");
        String casheMasha = text.substring(indexRublesStartMasha, indexRublesEndMasha).trim();
        System.out.println(Integer.parseInt(casheVasya) + Integer.parseInt(casheMasha));
    }
}