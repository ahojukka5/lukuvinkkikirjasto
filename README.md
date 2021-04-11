# Lukuvinkkikirjasto

Product backlog:
<https://docs.google.com/spreadsheets/d/1zUi1pc9pQ-kz11Bn88Q34mbKgrZqrk1Bsj93MvS5I70/edit#gid=1>

Miniprojektin ohjeet: <https://ohjelmistotuotanto-hy-avoin.github.io/miniprojekti/>

Arkkitehtuuri

![arkkitehtuuri](docs/arkkitehtuuri.jpg)

## Definition of done

Scrumin määrittelyn mukainen definition of done sisältää:

- user storyn määrittelyn,
- suunnittelun,
- toteutuksen,
- automatisoitujen testien tekemisen,
- integroinnin muuhun sovellukseen,
- dokumentoinnin.

## Käyttöohje

Ohjelman suoritus gradlella:

```bash
./gradlew run
```

Ohjelmassa on tekstikäyttöliittymä. Kun ohjelman käynnistää, se listaa eri komennot. Vaihtoehdot ovat

- `add`
- `list`
- `quit`

### Vinkin lisääminen

Vinkin lisääminen tapahtuu kirjoittamalla `add`. Tämän jälkeen ohjelma kysyy ensin vinkin otsikon jonka jälkeen vinkin url-osoitteen.

### Vinkkien listaaminen

Vinkkien listaaminen tapahtuu kirjoittamalla `list`. Tuloste on (esimerkiksi)

```text
Id: 1
Otsikko: Merge sort algorithm
Url: https://www.youtube.com/watch?v=TzeBrDU-JaY


Id: 2
Otsikko: Consistency models
Url: https://dev.to/napicellatwit/consistency-models-52l
```

### Ohjelmasta poistuminen

Ohjelmasta poistuminen tapahtuu kirjoittamalla `quit`.
