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

    private final Pelaaja p1; //Pelaaja 1
    private final Pelaaja p2; //Pelaaja 2

    private int tasaPelit;
    private int pelatutPelit;

    public Peli(Pelaaja p1, Pelaaja p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**Käynnistää pelin ja pelaa niin kauan kunnes jompikumpi pelaajista saavuttaa 3 voittoa.*/
    public void pelaa() {
        while (p1.getVoitot() < 3 && p2.getVoitot() < 3) {
            odota(); //Odotetaan 1 sekunti
            pelatutPelit++; //Nostetaan pelattujen pelien määrää
            prosessoiKierros(); //Pelaa yhden kierroksen
            printStatus(); // Tulostaa pelin tilanteen
        }
        tulostaVoittaja(); //Tulostaa voittajan
    }

    /***Odottaa 1 sekunnin ennen seuraavaa kierrosta.*/
    private void odota() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Pelin odotuksessa tapahtui virhe: ", e);
        }
    }

    /**Prosessoi yhden kierroksen ja nostaa voittajan voittoja.*/
    private void prosessoiKierros() {
        PeliTulokset result = pelaaYksiKierros(p1.pelaajanValinta(), p2.pelaajanValinta());
        //Taskistetaan mitä result palautti ja nostetaan voittajan pisteitä
        if (result == PeliTulokset.PELAAJA1_VOITOT) p1.nostaVoittoja();
        else if (result == PeliTulokset.PELAAJA2_VOITOT) p2.nostaVoittoja();
        else tasaPelit++;
    }

    /**Tulostaa voittajan, jos jompikumpi pelaajista saavuttaa 3 voittoa.*/
    private void tulostaVoittaja() {
        if (p1.getVoitot() == 3 || p2.getVoitot() == 3) {
            System.out.printf("Pelaaja %d voitti pelin!\n", p1.getVoitot() == 3 ? 1 : 2);
        }
    }

    /**Pelaa yhden kierroksen ja palauttaa voittajan.*/
    public PeliTulokset pelaaYksiKierros(String p1Choice, String p2Choice) {
        if (p1Choice.equals(p2Choice)) {
            return PeliTulokset.TASAPELI;
            //Tarkistetaan vaihtoehdot ja palautetaan voittaja
        } else if (p1Choice.equals(KIVI) && p2Choice.equals(SAKSET) ||
                p1Choice.equals(PAPERI) && p2Choice.equals(KIVI) ||
                p1Choice.equals(SAKSET) && p2Choice.equals(PAPERI)) {
            return PeliTulokset.PELAAJA1_VOITOT;
        } else {
            return PeliTulokset.PELAAJA2_VOITOT;
        }
    }

    /**Tulostaa pelin tämän hetkisen tilanteen.*/
    private void printStatus() {
        System.out.printf("Erä: %d\nTasapelit: %d\nPelaaja 1 Voittaa: %d\nPelaaja 2 Voittaa: %d\n",
                getPelatutPelit(), getTasapelit(), p1.getVoitot(), p2.getVoitot());
    }

    /**Hakee pelin tasapeli-arvon käynnissä olevasta pelistä*/
    public int getTasapelit() {
        return tasaPelit;
    }

    /**Hakee pelin pelattujen pelien määrän*/
    public int getPelatutPelit() {
        return pelatutPelit;
    }
}
