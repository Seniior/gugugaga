//Thread del consumatore
//Dati d è comune per tutti
//il Monitor m per tutti i consumatori
//ID è una stringa identificativa del thread specifico
public class Consumatore extends Thread{
	private Dati d;
	private String ID;
	private Monitor m;
	
	//costruttore, parametri sono i dati e monitor (comuni), ID specifico assegnato durante l'inizializzazione
	public Consumatore(String s,Dati d, Monitor m) {
		this.ID=s;
		this.d=d;
		this.m=m;
	}
	
	//Codice da eseguire durante la vita del thread
	public void run() {	
		while(d.buffer[d.out]<d.nProc) {	//entra nel ciclo se il prossimo elemento da consumare è minore del numero di processi totali
			
			synchronized(m) {				//blocco synchronized in mutua esclusione: può "entrare" solo un consumatore alla volta			
				if(d.in!=d.out) {			//condizione di buffer vuoto (posizione dei consumatori = posizione produttori)
					System.out.println("Il consumatore "+this.ID+" ha letto: "+ d.buffer[d.out]);		//stampa dell'azione
					d.out=(d.out+1)%d.size;			//la posizione del consumatore si sposta di 1 più avanti o all'inizio se si trova nell'ultima cella del buffer
				}
			}
			
			try {	
				sleep(d.sleepMin+(int)(Math.random())*(d.sleepMax-d.sleepMin));		//il thread va in sleep per un tempo compreso nell'intervallo sleepMin - sleepMax
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
