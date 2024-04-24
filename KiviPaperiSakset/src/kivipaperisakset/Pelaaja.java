
package kivipaperisakset;

/**
 *
 * @author Ira Dook
 */
public class Pelaaja {
    private int voitot;

    public String pelaajanValinta() {
        return new String[]{"kivi", "paperi", "sakset"}[(int) (Math.random() * 3)];
    }

    public void incrementVoitot() {
        voitot++;
    }

    public int getVoitot() {
        return voitot;
    }
}
