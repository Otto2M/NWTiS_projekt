// package org.foi.nwtis.omilermat.zadaca_1;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// import java.io.IOException;
//
// import org.foi.nwtis.Konfiguracija;
// import org.foi.nwtis.KonfiguracijaApstraktna;
// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
//
// class GlavniPosluziteljTest {
//
// private static Konfiguracija konf;
// private GlavniPosluzitelj gp;
//
// @BeforeAll
// static void setUpBeforeClass() throws Exception {
// konf = KonfiguracijaApstraktna.preuzmiKonfiguraciju("NWTiS_omilermat_1.txt");
// }
//
// @AfterAll
// static void tearDownAfterClass() throws Exception {
// konf = null;
// }
//
// @BeforeEach
// void setUp() throws Exception {
// this.gp = new GlavniPosluzitelj(GlavniPosluziteljTest.konf);
// }
//
// @AfterEach
// void tearDown() throws Exception {
// this.gp = null;
// }
//
// @Disabled("Još treba implementirati!")
// @Test
// void testPokreniPosluzitelja() {
// fail("Not yet implemented");
// }
//
// @Test
// void akoImamoIspravnuDatotekuKadaPokrenemoUcitajKorisnikeTadaImamoKorisnike() {
// try {
// gp.ucitajKorisnike();
// assertEquals(10, gp.korisnici.size());
// assertEquals("Pero", gp.korisnici.get("pkos").ime());
// assertEquals("Kos", gp.korisnici.get("pkos").prezime());
// assertEquals(true, gp.korisnici.get("pkos").administrator());
// } catch (IOException e) {
// fail(e.getMessage());
// }
// }
//
// @Disabled("Još treba implementirati!")
// @Test
// void testOtvoriMreznaVrata() {
// fail("Not yet implemented");
// }
//
// }
