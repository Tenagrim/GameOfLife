public class Main {
    public static void main(String[] args) {
        Form f = new Form("Game of Life");
        new Thread(f).start();
//        f.run();
    }
}
