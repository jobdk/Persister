# Luca Weinmann

## Praktikum 2

### Aufgabe 1

```bash
docker run ubuntu echo "Cloud Native Computing"
```

### Aufgabe 2

```bash
docker ps -a
docker start c050d
c050d
docker start c050d
c050d
docker logs c050d
Cloud Native Computing
Cloud Native Computing
Cloud Native Computing
```

### Aufgabe 3

```bash
docker rm c050d
```

### Aufgabe 4

```bash 
docker run -d -p 9090:80 nginxdemos/hello
```

Server Address: 172.17.0.2:80

### Aufgabe 5

```bash 
docker ps
docker stop 0baa
docker ps -a
docker rm 0baa
docker ps -a
```

### Aufgabe 6

im Verzeichnis documents/index.html

### Aufgabe 7

```bash 
docker run -d -v D:/WorkHFU/Master/CloudNativeComputing/Praktika/Praktikum_01_REST_Server/service/documents/:/usr/share/nginx/html -p 9090:80 nginx
```

![index.html auf Port 9090](pictures/Praktikum_2_Aufgabe_7_Docker.png)
<br>Die index.html auf Port 9090 aufgerufen

![index.html direkt lokal aufgerufen](pictures/Praktikum_2_Aufgabe_7_lokal.png)
<br>Die index.html als lokale Datei aufgerufen

### Aufgabe 8

```bash 
docker ps
docker stop 2567
docker rm 2567
```

## Praktikum 3
### Schritt 1
```bash 
docker build -t ubuntu:figlet .
docker run ubuntu:figlet "Cloud Native Computing"
```
![Erzeugte Schrift](pictures/Praktikum_3_Schritt_1.png)

### Schritt 2
```bash 
docker login -u weinmann docker.informatik.hs-furtwangen.de
mvn compile jib:build -D"image=docker.informatik.hs-furtwangen.de/cnc-weinmann/service"
```

### Schritt 3
![Docker.Hosted](pictures/Praktikum_3_Schritt_3.png)

### Schritt 4
Port 8080 ist schon belegt
```bash
 docker run -p 8090:8080 docker.informatik.hs-furtwangen.de/cnc-weinmann/service
```

### Schritt 5
Siehe GitLab