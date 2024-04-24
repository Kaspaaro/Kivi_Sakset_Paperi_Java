import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Kaspar Tullus
 */

/** PelaajaTest - Testaa pelaajan toimintaa (Junit 5,Mockito)*/
public class PelaajaTest {
    private Peli peli;
    private Pelaaja p1, p2, pelaaja;

    /**
     * Aloitellaan alustamalla mock-pelaajat,yksi ei mockattu-pelaaja ja peli
     * */

    @BeforeEach
    public void setUp() {
        p1 = Mockito.mock(Pelaaja.class);
        p2 = Mockito.mock(Pelaaja.class);
        pelaaja = new Pelaaja();
        peli = new Peli(p1, p2);
    }

    /**
     * Testaa pelaaja ykkösen voittoja
     * */
    @Test
    public void testPelaajaYksiVoittaa() {

        //Mockataan pelaajien valinnat ja voitot
        when(p1.getVoitot()).thenReturn(2, 3);
        when(p2.getVoitot()).thenReturn(2, 2);

        when(p1.pelaajanValinta()).thenReturn("kivi");
        when(p2.pelaajanValinta()).thenReturn("sakset");

        //Pelataan peliä mock pelaajilla
        peli.pelaa();

        //Tulokset, Pelaaja 1 voittaa
        assertEquals(3, p1.getVoitot());
        assertEquals(2, p2.getVoitot());
    }

    /**
     * Testaa pelaaja kakkosen voittoja
     * */
    @Test
    public void testPelaajaKaksiVoittaa() {
        //Mockataan pelaajien valinnat ja voitot
        when(p1.getVoitot()).thenReturn(2, 2);
        when(p2.getVoitot()).thenReturn(2, 3);

        when(p1.pelaajanValinta()).thenReturn("sakset");
        when(p2.pelaajanValinta()).thenReturn("kivi");

        //Pelataan peliä mock pelaajilla
        peli.pelaa();

        //Tulokset, Pelaaja 2 voittaa
        assertEquals(2, p1.getVoitot());
        assertEquals(3, p2.getVoitot());
    }

    /**
     * Testaa pelaajan voittojen laskemista
     * */
    @Test
    public void nostaVoittoja_nostaVoittojaYhdellä() {
        int initialVoitot = pelaaja.getVoitot();
        pelaaja.nostaVoittoja();
        assertEquals(initialVoitot + 1, pelaaja.getVoitot());
    }

    /**
     * Testaa Lasketut voitot
     * */
    @Test
    public void getVoitot_palauttaaLasketutVoitot() {
        pelaaja.nostaVoittoja();
        pelaaja.nostaVoittoja();
        assertEquals(2, pelaaja.getVoitot());
    }

    /**
     * Testaa pelaajan valintoja, onko kyseessä kivi, paperi vai sakset
     * */

    @Test
    public void pelaajanValinta_PalauttaValitunArvon() {
        String valinta = pelaaja.pelaajanValinta();
        assertTrue(valinta.equals("kivi") || valinta.equals("paperi") || valinta.equals("sakset"));
    }

    /**
     * Testaa montako vaihtoehtoja pelajalla on mistä voi valita.
     * */

    @Test
    public void pelaajanValinta_palauttaaErilaisiaVaihtoehtoja() {
        Set<String> choices = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            choices.add(pelaaja.pelaajanValinta());
        }
        assertEquals(3, choices.size());
    }
}
