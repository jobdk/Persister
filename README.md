# Persister bauen und starten

Im root des Projekts

```bash
mvn clean install
```

```bash
docker build -t persister .
```

```bash
docker run -d -v <gewünschter Ablageort im Verzeichnis>:/var/tmp -p 8089:8089 persister
```