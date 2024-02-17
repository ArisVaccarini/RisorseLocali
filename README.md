# Progetto IdS 2023/2024
Progetto di Ingegneria del Software (Prof. Morichetta - Prof. Polini) [ST0496] per la valorizzazione del territorio comunale, sviluppato da:
-	Samuele Pirani (N⁰ matricola 118535);
-	Aris Vaccarini (N⁰ matricola 118536);
  
Frequentati il corso di [Informatica per la Comunicazione Digitale (L-31)](http://www.didattica.cs.unicam.it/doku.php?id=didattica:licd) presso l’università di Camerino.  
## Descrizione :page_facing_up:
Il progetto ha come fine la valorizzazione del territorio comunale della città di Senigallia dando al cittadino la possibilità di caricare contenuti aggiuntivi come informazioni culturali, sportive, turistiche o di qualsiasi altra natura. Ognuno di questi contenuti è legato a un punto di interesse (o “POI”) o un itinerario, entrambi geolocalizzati nel comune di riferimento e definibili, come per i contenuti, dagli utenti stessi.

Per ulteriori dettagli vedi – [Specifica progetto](https://docs.google.com/document/d/1kqarA2bRB8I8StOazcWotmkxf4Afycyl34a-n536JHo/edit)
## Come eseguire il progetto :gear:
Per eseguire il progetto basterà o scaricarlo ed eseguirlo direttamente da IntelliJ, oppure eseguire questa serie di passaggi:

•	Aprire il prompt dei comandi ed entrare nella cartella del progetto

•	Eseguire il comando mvn clean install per installare le dipendenze

•	Eseguire il comando java -jar .\target\risorseLocali-0.0.1-SNAPSHOT.jar per avviare il progetto

Per far si che il progetto venga eseguito nel secondo metodo, occorre installare nel proprio terminale Maven. 
Guida -> [Come installare Maven](https://www.html.it/articoli/maven-organizzazione-dei-progetti-java/) 
## Testare il progetto :computer:
Per interagire con il progetto, all’interno della cartella “ApiExemple” sono stati lasciati degli oggetti JSON e le corrispettive chiamate REST API da utilizzare su Postman, essendo il progetto sprovvisto di interfaccia grafica.
