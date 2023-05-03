import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	    
		String sleepMin="", sleepMax="", nCons="", nProd="", nProc="";
		
		
		//Inserimento i dati di base: sleep minimo, sleep massimo,
		//numero di rpodutori e consumatori, numero di processi da eseguire
		System.out.println("\nInserisci il sleep minimo: ");
		try {
			sleepMin=scanner.nextLine();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\nInserisci il sleep massimo: ");
		try {
			sleepMax=scanner.nextLine();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\nInserisci il numero di produttori: ");
		try {
			nProd=scanner.nextLine();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\nInserisci il numero di consumatori: ");
		try {
			nCons=scanner.nextLine();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\nInserisci il numero di processi: ");
		try {
			nProc=scanner.nextLine();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		//viene istanziato l'oggetto 'Dati' contenente le informazioni inserite dall'utente
		Dati d = new Dati(Integer.parseInt(sleepMax) ,Integer.parseInt(sleepMin), 5, Integer.parseInt(nProc));
		
		//istanza del monitor
		Monitor m = new Monitor();
		
		//avvio dei thread consumatori
		for(int i=0; i<Integer.parseInt(nCons);i++) {
			Consumatore cons= new Consumatore(Integer.toString(i), d, m);
			cons.start();
		}
		
		//nuovo monitor (deve essere diverso per produttori e consumatori)
		m = new Monitor();
		
		//avvio dei thread produttori
		for(int i=0; i<Integer.parseInt(nProd);i++) {
			Produttore prod= new Produttore(Integer.toString(i), d, m);
			prod.start();
		}
	}

}
