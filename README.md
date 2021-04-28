# Lukuvinkkikirjasto

[![Continuous Integration][ci-img]][ci-url]
[![Coverage Status][coverage-img]][coverage-url]

Product backlog:
<https://docs.google.com/spreadsheets/d/1zUi1pc9pQ-kz11Bn88Q34mbKgrZqrk1Bsj93MvS5I70/edit#gid=1>

Loppuraportti:
<https://docs.google.com/document/d/135_VJk9BmdaQidp9X9mCCURQbl2cAuYqvzr-aaYhixw/edit#>

Miniprojektin ohjeet: <https://ohjelmistotuotanto-hy-avoin.github.io/miniprojekti/>

Aiheen kuvaus: <https://ohjelmistotuotanto-hy-avoin.github.io/speksi/>

## Arkkitehtuuri

![arkkitehtuuri](docs/arkkitehtuuri.jpg)

## Definition of done

- User storylle on määritelty hyväksymiskriteerit.
- User story on jaettu teknisen tason työtehtäviksi.
- Automatisoidut yksikkötestit on tehty.
- Automatisoidut hyväksymistestit on tehty.
- Testien rivikattavuus on vähintään 80 %.
- Ohjelmakoodi on selkeää ja läpäisee automaattisen checkstyle-testin.
- Ei-triviaalit muutokset ohjelmakoodiin on katselmoitu jonkun muun kuin
  ohjelmakoodin kirjoittajan toimesta.

## Asennus- ja käyttöohje

Ohjelman riippuvuuksien asennus ja ohjelman suoritus tapahtuu gradlella:

```bash
./gradlew run --console=plain
```

Ohjelmassa on tekstikäyttöliittymä. Kun ohjelman käynnistää, se listaa eri
komennot. Vaihtoehdot ovat

- `add`
- `addUrl`
- `edit`
- `remove`
- `list`
- `markRead`
- `quit`

### Vinkin lisääminen

Vinkin lisääminen tapahtuu kirjoittamalla `add`. Tämän jälkeen ohjelma kysyy
ensin vinkin otsikon jonka jälkeen vinkin url-osoitteen.

### Vinkin lisääminen urlin perusteella

Vinkin lisääminen tapahtuu kirjoittamalla `addUrl`. Tämän jälkeen ohjelma kysyy
url-osoitteen. Ohjelma hakee automaattisesti vinkin otsikoksi websivun otsikon.
Tämän jälkeen haetun otsikon voi muuttaa tai hyväksyä jättämällä kentän tyhjäksi
(eli painamalla enter).

### Vinkin muokkaaminen

Vinkin muokkaaminen tapahtuu kirjoittamalla `edit`. Tämän jälkeen ohjelma kysyy
vinkin id-numeron, jonka syötettyään voidaan lukuvinkin kenttiä muokata yksi
kerrallaan. Mikäli kenttää ei muokata, jätetään kenttä tyhjäksi eli painetaan
vaan enteriä. Esimerkiksi vinkin "Consitency models" kirjoitusvirhe
korjattaisiin seuraavasti:

```text
edit
  1 Consitency models
Valitse id-numero:
1
title [Consitency models]: Consistency models
url [https://dev.to/napicellatwit/consistency-models-52l]:
Tags [web, models]:
Vinkki 1 päivitetty!
```

Tägit listataan pilkulla eroteltuna.

### Tägien lisääminen vinkeille

Vinkeille voi lisätä tägejä `addTags` komennolla. Komento kysyy id-numeron sekä
lisättävän tagin jonka jälkeen uusi tagi lisätään vinkkiin.

```text
addTags
  1 Consistency models
Valitse id-numero:
1
Nykyiset tägit: web
Anna uusi tag: youtube
Tägi 'youtube' lisätty vinkille 1!
```

### Vinkkien listaaminen ja suodattaminen

Vinkkien listaaminen tapahtuu kirjoittamalla `list`. Aluksi ohjelma tulostaa
kaikkia lukuvinkit. Tuloste on (esimerkiksi)

```text
Id: 1
Otsikko: Merge sort algorithm
Url: https://www.youtube.com/watch?v=TzeBrDU-JaY


Id: 2
Otsikko: Consistency models
Url: https://dev.to/napicellatwit/consistency-models-52l
```

Lukuvinkkejä voi suodattaa

- otsikon perusteella valitsemalla `title`
  - kirjoita hakusana, kuten 'otsikk'
  - haku löytää pienistä kirjoitusvirheistä huolimatta, esimerkiksi 'heslinki'
    tuottaa vinkit, joiden otsikossa on sana 'helsinki'
- otsikon perusteella täsmällisen osan sisältäviä valitsemalla `titleExact`
- luettujen perusteella valitsemalla `read`
- lukemattomien perusteella valitsemalla `unread`
- vinkkeihin liittyvien tägien perusteella valitsemalla `tag`

Tämän jälkeen ohjelma listaa lukuvinkit suodatusten perusteella. Suodattimia voi
olla useampi yhtä aikaa päällä (aktiiviset suodattimet ovat näkyvillä). Voit
tyhjentää suodattimet valitsemalla `clear`. Pääset takaisin päävalikkoon
komennolla `menu`.

### Vinkkien poistaminen

Vinkkien poistaminen tapahtuu kirjoittamalla `remove`. Ohjelma listaa
tietokannassa olevat lukuvinkit. Tämän jälkeen valitaan sen lukuvinkin id-numero
joka halutaan poistaa ja varmistuksen jälkeen vinkki poistuu tietokannasta.

```text
remove
  1 Merge sort algorithm
  2 Consistency models
Valitse id-numero:
1
Poistetaanko vinkki id-numerolla 1 ja otsikolla 'Merge sort algorithm'? [k/e]
k
Vinkki 1 poistettu!
```

### Vinkkien merkkaaminen luetuksi

Vinkkien merkkaaminen luetuksi tapahtuu kirjoittamalla `markRead`. Ohjelma listaa
tietokannassa olevat lukuvinkit. Tämän jälkeen valitaan sen lukuvinkin id-numero
joka halutaan merkata luetuksi ja varmistuksen jälkeen vinkki merkkautuu luetuksi.

```text
markRead
  1 Merge sort algorithm
  2 Consistency models
Valitse id-numero:
1
Merkitäänkö vinkki id-numerolla 1 ja otsikolla 'Merge sort algorithm' luetuksi? [k/e]
k
Vinkki 1 merkitty luetuksi!
```

### Ohjelmasta poistuminen

Ohjelmasta poistuminen tapahtuu kirjoittamalla `quit`.

[ci-img]: https://github.com/ahojukka5/lukuvinkkikirjasto/actions/workflows/gradle.yml/badge.svg
[ci-url]: https://github.com/ahojukka5/lukuvinkkikirjasto/actions?query=workflow%3ACI+branch%3Amaster
[coverage-img]: https://coveralls.io/repos/github/ahojukka5/lukuvinkkikirjasto/badge.svg?branch=master
[coverage-url]: https://coveralls.io/github/ahojukka5/lukuvinkkikirjasto?branch=master
