SELECT ID, RADNJA, METODA, DATUM_VRIJEME, VRSTA
FROM PUBLIC.PUBLIC.DNEVNIK;

INSERT INTO PUBLIC.PUBLIC.DNEVNIK (RADNJA, METODA, DATUM_VRIJEME, VRSTA)
VALUES('Dozvoljen ispis', 'INFO DA', NOW(), 'AP2');


SELECT ID, RADNJA, METODA, DATUM_VRIJEME, VRSTA FROM PUBLIC.PUBLIC.DNEVNIK WHERE VRSTA = 'AP2' LIMIT 5 OFFSET 1;

SELECT ID, RADNJA, METODA, DATUM_VRIJEME, VRSTA FROM PUBLIC.PUBLIC.DNEVNIK LIMIT 5 OFFSET 0;

GRANT SELECT, UPDATE, INSERT ON TABLE DNEVNIK TO APLIKACIJA;

GRANT SELECT, UPDATE, INSERT ON TABLE KORISNIK TO APLIKACIJA;

GRANT SELECT, UPDATE, INSERT ON TABLE AERODROMI_LETOVI TO APLIKACIJA;


SELECT *
FROM PUBLIC.PUBLIC.KORISNIK;

SELECT IME, PREZIME, KORISNICKO_IME, LOZINKA, EMAIL
FROM PUBLIC.PUBLIC.KORISNIK WHERE KORISNICKO_IME = 'omilermat';

INSERT INTO KORISNIK
(IME, PREZIME, KORISNICKO_IME, LOZINKA, EMAIL)
VALUES('', '', '', '', '');

SELECT COUNT(ID)
FROM PUBLIC.KORISNIK;

SELECT * FROM AERODROMI_LETOVI;

INSERT INTO AERODROMI_LETOVI
(ICAO, AKTIVAN)
VALUES('LSZH', true);

INSERT INTO PUBLIC.PUBLIC.AERODROMI_LETOVI
(ICAO, AKTIVAN)
VALUES
('LFML', true),
('LTCE', true);

INSERT INTO PUBLIC.PUBLIC.AERODROMI_LETOVI
(ICAO, AKTIVAN)
VALUES
('EBBR', true),
('EDDF', true),
('EDDM', true),
('EGGP', true),
('EGLL', true),
('EIDW', true),
('EPWA', true),
('GCLP', true),
('HEGN', true),
('LDZA', true),
('LEBL', true),
('LEPA', true),
('LFPG', true),
('EDDS', true),
('LIPZ', true),
('LOWW', true),
('LTBJ', true),
('LSZH', true),
('LJLJ', true),
('OMDB', true);

SELECT ICAO, NAME, ISO_COUNTRY, COORDINATES FROM AIRPORTS JOIN  

SELECT ICAO, NAME, ISO_COUNTRY, COORDINATES 
FROM AIRPORTS
INNER JOIN AERODROMI_LETOVI ON AIRPORTS.ICAO=AERODROMI_LETOVI.ICAO;

SELECT KORISNICKO_IME, LOZINKA FROM KORISNIK WHERE KORISNICKO_IME = 'omilermat' AND LOZINKA = 'lozinka123';

UPDATE AERODROMI_LETOVI SET AKTIVAN=FALSE WHERE ICAO='';

SELECT RADNJA, METODA, DATUM_VRIJEME, VRSTA FROM DNEVNIK WHERE VRSTA = 'AP2' LIMIT 5 OFFSET 1;

SELECT AKTIVAN FROM AERODROMI_LETOVI; 

SELECT AKTIVAN FROM AIRPORTS INNER JOIN AERODROMI_LETOVI ON AIRPORTS.ICAO=AERODROMI_LETOVI.ICAO;

ALTER TABLE PUBLIC.DNEVNIK ALTER COLUMN RADNJA VARCHAR(250);


