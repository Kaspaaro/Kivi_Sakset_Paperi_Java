/**
 * Peli-luokka, joka sisältää pelin logiikan.
 *
 * @author Ira Dook
 * * Muokattu/Korjattu, Kaspar Tullus
 */

public class Peli {
    private static final String KIVI = "kivi";
    private static final String PAPERI = "paperi";
    private static final String SAKSET = "sakset";

    private final Pelaaja p1;
    private final Pelaaja p2;

    private int tasaPelit;
    private int pelatutPelit;

    public Peli(Pelaaja p1, Pelaaja p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void pelaa() {
        while (p1.getVoitot() < 3 && p2.getVoitot() < 3) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Pelin odotuksessa tapahtui virhe: ", e);
            }

            pelatutPelit++;
            PeliTulokset result = pelaaYksiKierros(p1.pelaajanValinta(), p2.pelaajanValinta());
            if (result == PeliTulokset.PELAAJA1_VOITOT) p1.incrementVoitot();
            else if (result == PeliTulokset.PELAAJA2_VOITOT) p2.incrementVoitot();
            else tasaPelit++;
            printStatus();
        }
        if (p1.getVoitot() == 3 || p2.getVoitot() == 3) {
            System.out.printf("Pelaaja %d voitti pelin!\n", p1.getVoitot() == 3 ? 1 : 2);
        }
    }

    public PeliTulokset pelaaYksiKierros(String p1Choice, String p2Choice) {
        if (p1Choice.equals(p2Choice)) {return PeliTulokset.TASAPELI;}
        if ((p1Choice.equals(KIVI) && p2Choice.equals(SAKSET)) ||
                (p1Choice.equals(PAPERI) && p2Choice.equals(KIVI)) ||
                (p1Choice.equals(SAKSET) && p2Choice.equals(PAPERI)))
        {return PeliTulokset.PELAAJA1_VOITOT;}
        return PeliTulokset.PELAAJA2_VOITOT;
    }

    private void printStatus() {
        System.out.printf("Erä: %d\nTasapelit: %d\nPelaaja 1 Voittaa: %d\nPelaaja 2 Voittaa: %d\n",
                getPelatutPelit(), getTasapelit(), p1.getVoitot(), p2.getVoitot());
    }

    public int getTasapelit() {
        return tasaPelit;
    }

    public int getPelatutPelit() {
        return pelatutPelit;
    }
}
