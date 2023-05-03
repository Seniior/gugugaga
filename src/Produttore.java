//Thread del produttore
//Dati d è comune per tutti 
//il Monitor m per tutti i produttori
//ID è una stringa identificativa del thread specifico
public class Produttore extends Thread{
	private Dati d;
	private String ID;
	private Monitor m;
	
	//costruttore, parametri sono i dati e monitor (comuni), ID specifico assegnato durante l'inizializzazione
	public Produttore(String s,Dati d, Monitor m) {
		this.ID=s;
		this.d=d;
		this.m=m;
	}
	
	//Codice da eseguire durante la vita del thread
	public void run() {
		while(d.dt<=d.nProc) {		//entra nel ciclo se dt (variabile ausiliaria dei processi già eseguiti) è minore del numero di processi totali
			synchronized(m) {		//blocco synchronized in mutua esclusione: può "entrare" solo un produttore alla volta
				if((d.in+1)%d.size!=d.out) {	//condizione di buffer pieno (la prossima cella dopo il produttore è vuota mentre quella dopo è quella dei consumatori)
					d.buffer[d.in]=d.dt++;		//inserimento del dato dentro il buffer (qua coincide con il numero del processo attuale)
					System.out.println("Il produttore "+this.ID+" ha prodotto: "+ d.buffer[d.in]);		//stampa del'azione
					d.in=(d.in+1)%d.size;		//la posizione del produttore si sposta di 1 più avanti o all'inizio se si trova nell'ultima cella del buffer
				}
			}
			
			try {
				sleep(d.sleepMin + (int)(Math.random())*(d.sleepMax-d.sleepMin));		//il thread va in sleep per un tempo compreso nell'intervallo sleepMin - sleepMax
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
