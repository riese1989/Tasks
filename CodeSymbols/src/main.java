public class main {
    public static void main(String[] args) {
        //65-90 97-122
        int code;
        for (code = 65; code <= 122; code++) {
            System.out.println((char)code + " " + code);
            if (code == 90) {
                code = 96;
            }
        }

    }
}
