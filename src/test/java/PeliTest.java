
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Kaspar Tullus
 *
 * */

public class PeliTest {
    private Peli peli;
    private Pelaaja p1;
    private Pelaaja p2;

    @BeforeEach
    public void setUp() {
        p1 = Mockito.mock(Pelaaja.class);
        p2 = Mockito.mock(Pelaaja.class);
        peli = new Peli(p1, p2);
    }

    /** Testaa pelaaYksiKierros-metodia
     * @param p1Valinta pelaajan "Yhden" valinta, esim. kivi
     * @param p2Valinta pelaajan "Kahden" valinta, esim. sakset
     * @param expectedOutcome odotettu tulos, esim. PELAAJA1_VOITOT
     */
    @ParameterizedTest
    @CsvSource({
            "kivi, sakset, PELAAJA1_VOITOT",
            "sakset, kivi, PELAAJA2_VOITOT",
            "kivi, kivi, TASAPELI",
            "paperi, kivi, PELAAJA1_VOITOT",
            "kivi, paperi, PELAAJA2_VOITOT",
            "paperi, paperi, TASAPELI",
            "sakset, paperi, PELAAJA1_VOITOT",
            "paperi, sakset, PELAAJA2_VOITOT",
            "sakset, sakset, TASAPELI"
    })
    public void testYksiKierros(String p1Valinta, String p2Valinta, PeliTulokset expectedOutcome) {
        PeliTulokset result = peli.pelaaYksiKierros(p1Valinta, p2Valinta);
        assertEquals(expectedOutcome, result);
    }

    /**
     * Testaa pelin tasapeli tuloksen laskemista
     * */

    @Test
    public void testTasaPelitLaskettuOikein() {
        //Mockataan pelaajien valinnat ja voitot
        when(p1.getVoitot()).thenReturn(1, 1,2,3);
        when(p2.getVoitot()).thenReturn(1, 1,2,2);
        when(p1.pelaajanValinta()).thenReturn("sakset","kivi");
        when(p2.pelaajanValinta()).thenReturn("kivi", "kivi");

        //Pelataan peliä mock pelaajilla
        peli.pelaa();

        //Tulokset, Tasapelit 2
        assertEquals(1, peli.getTasapelit());

    }

    /**
     * Testaa pelin tasapeli tuloksen laskemista
     * */

    @Test
    public void test_PelinOdotusVirhe() {

        //Mock Pelaajat
        when(p1.getVoitot()).thenReturn(0);
        when(p2.getVoitot()).thenReturn(0);
        when(p1.pelaajanValinta()).thenReturn("sakset","kivi");
        when(p2.pelaajanValinta()).thenReturn("kivi", "kivi");

        Peli peli = new Peli(p1, p2);
        Thread.currentThread().interrupt();

        assertThrows(RuntimeException.class, peli::pelaa);
    }
}