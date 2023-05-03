//Classe dati comune per tutti i produttori e consumatori (thread) contenente 
//i dati da analizzare nel buffer e le posizioni in esso
public class Dati {
	public volatile int in;				//posizione del produttore
	public volatile int out;			//posizione del consumatore
	public volatile int[] buffer;		//buffer contenente i dati
	public volatile int size;			//dimensione buffer
	public volatile int sleepMin;		//tempo di sleep minimo
	public volatile int sleepMax;		//tempo di sleep massimo
	public volatile int nProc;			//numero di processi da produrre/consumare	
	public volatile int dt;				//numero del processo attuale (durante eseuzione)
	
	//Costruttore, parametri presi dall'utente
	public Dati(int sleepMin, int sleepMax, int size, int nProc) {
		this.sleepMin=sleepMin;
		this.sleepMax=sleepMax;
		this.in=0;
		this.out=0;
		this.size=size;
		buffer = new int[size];
		this.nProc=nProc;
	}
}
