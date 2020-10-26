package ohtu.ohtuvarasto;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void otaLiikaaVarastosta() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(110);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaNegatiivinenMaara() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(-10);

        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylimaaraMeneeHukkaan() {
        varasto.lisaaVarastoon(varasto.getTilavuus() * 10);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaNegatiivinenMaara() {
        double ed = varasto.getSaldo();
        varasto.lisaaVarastoon(-10);

        assertEquals(ed, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void mahdotonTilavuus() {
        Varasto v = new Varasto(-100);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriOikeaTilavuus() {
        Varasto v = new Varasto(10, 100);

        assertEquals(v.getTilavuus(), 10, vertailuTarkkuus);
    }

    @Test
    public void kkMahdotonTilavuus() {
        Varasto v = new Varasto(-10, 100);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoNegatiivinen() {
        Varasto v = new Varasto(10, -10);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoSamaKuinSaldo() {
        Varasto v = new Varasto(10, 6);
        assertEquals(6, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tulostusmuotoOikea() {
        String tulos = varasto.toString().replaceAll("[0-9\\.]", "");
        String oikea = "saldo = 10, vielä tilaa 10".replaceAll("[0-9\\.]", "");

        assertTrue("oikea:" + oikea + "    tulos:" + tulos, oikea.equals(tulos));
    }

}