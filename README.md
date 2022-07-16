# Persister
Der Persister ist für das speichern der Bilder und Ergebnisse verantwortlich, da es von OpenFaaS keinen Zugriff das
Dateisystem des Servers gibt.

## Persister bauen und starten
Im root des Projekts folgende Befehle ausführen:

```bash
mvn clean install
```

```bash
docker build -t persister .
```

```bash
docker run -d -v <gewünschter Ablageort im Verzeichnis>:/var/tmp -p 8089:8089 persister
```