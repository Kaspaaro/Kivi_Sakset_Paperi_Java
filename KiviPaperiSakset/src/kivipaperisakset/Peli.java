package kivipaperisakset;

/**
 *
 * @author Ira Dook
 */
public class Peli {
    private Pelaaja p1, p2;
    private int tasapelit, pelatutPelit;

    public Peli() {
        p1 = new Pelaaja();
        p2 = new Pelaaja();
    }

    public void pelaa() {
        while (p1.getVoitot() < 3 && p2.getVoitot() < 3) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pelatutPelit++;
            PeliTulokset tulos = playRound(p1.pelaajanValinta(), p2.pelaajanValinta());
            if (tulos == PeliTulokset.PELAAJA1_VOITOT) p1.incrementVoitot();
            else if (tulos == PeliTulokset.PELAAJA2_VOITOT) p2.incrementVoitot();
            else tasapelit++;
            printStatus();
        }
        if (p1.getVoitot() == 3 || p2.getVoitot() == 3) {
            System.out.printf("Pelaaja %d voitti pelin!\n", p1.getVoitot() == 3 ? 1 : 2);
        }
    }

    private PeliTulokset playRound(String p1Valinta, String p2Valinta) {
        if (p1Valinta.equals(p2Valinta)) return PeliTulokset.TASAPELI;
        if ((p1Valinta.equals("kivi") && p2Valinta.equals("sakset")) ||
                (p1Valinta.equals("paperi") && p2Valinta.equals("kivi")) ||
                (p1Valinta.equals("sakset") && p2Valinta.equals("paperi"))) return PeliTulokset.PELAAJA1_VOITOT;
        return PeliTulokset.PELAAJA2_VOITOT;
    }

    private void printStatus() {
        System.out.printf("Erä: %d\nTasapelien lukumäärä: %d\nPelaaja 1 voittoja: %d\nPelaaja 2 voittoja: %d\n",
                pelatutPelit, tasapelit, p1.getVoitot(), p2.getVoitot());
    }
}
