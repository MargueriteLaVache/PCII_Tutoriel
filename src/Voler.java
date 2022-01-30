
class Voler extends Thread {

    public static int sleepTime = 20;
    Etat etat;

    public Voler(Etat e) {
        etat = e;
    }

    @Override
    public void run() {
        while (true) {
            Etat.moveDown();
            try { Thread.sleep(sleepTime); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}