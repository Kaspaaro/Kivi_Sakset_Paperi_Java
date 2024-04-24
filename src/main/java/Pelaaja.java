/**
 *
 * @author Ira Dook
 * Muokattu/Korjattu, Kaspar Tullus
 */

/**täälä lasketaan kaikki yhden pelaajan voitot ja pelaajan valinnat*/
public class Pelaaja {
    private int voitot;

    /**Lasketaan Math.random() * 3 avulla pelaajan valinta "kivi,sakset,paperi"*/
    public String pelaajanValinta() {
        return new String[]{"kivi", "paperi", "sakset"}[(int) (Math.random() * 3)];
    }

    public void nostaVoittoja() {
        voitot++;
    }

    public int getVoitot() {
        return voitot;
    }
}
