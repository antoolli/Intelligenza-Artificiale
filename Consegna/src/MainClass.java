import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.languages.asp.SymbolicConstant;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class MainClass {

	public MainClass() {}
	
	private static String encodingResource="encodings/pyramid";
	private static Handler handler;
	private static int sizeColumn=15;
	private static int sizeRow=7;
	
	
	private static int[][] piramide={
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, 
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 }, 
			{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
			{0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
			{0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 }, 
			{0, 0, 2 ,0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 }, 
	};	
	
	private static Card[][] matrix= {
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null }, 
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null }, 
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null },
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null },
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null },
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null },
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null },
	};	
	
	private static Card [] deck = { 
		null, null, null, null, null, null, null, null, null, null, null, null, null,
		null, null, null, null, null, null, null, null, null, null, null
	};


								
	public static void main(String[] args) {
		caricaGioco();
		stampaPiramide();
	
		stampaDeck();
		
		//handler = new DesktopHandler(new DLVDesktopService("lib/dlv.mingw.exe"));
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
		
	
		
		try {
			ASPMapper.getInstance().registerClass(CardDlv.class);
			ASPMapper.getInstance().registerClass(Cell.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		boolean gioca=true;
		while(gioca) {
			gioca=false;
		InputProgram facts= new ASPInputProgram();
		
//------------------------------------------------PASSO LE CARTE SCOPERTE
		
		for(int i=0;i<sizeRow;i++){
			for(int j=0;j<sizeColumn;j++){
				if(piramide[i][j]== 2){
					try {
						
						facts.addObjectInput(new CardDlv(i,j,matrix[i][j].getValue(), matrix[i][j].getSeme(),"p"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}	
			}			
		}
		
		
		
//--------------------------------------------PASSO IL DECK		
		for (int i=0; i<deck.length ; i++) { 
			if (deck[i] != null) {
				try {
					facts.addObjectInput(new CardDlv(7,7,deck[i].getValue(), deck[i].getSeme(),"d"));
				} catch (Exception e) {
					System.out.println("ERRORE NELLE CARTE DECK");
					e.printStackTrace();
				}
			}
		}
			
		//Aggiungiamo all'handler il programma logico comprensivo di encoding e fatti.
		handler.addProgram(facts);
		
		
		InputProgram  program = new ASPInputProgram();
		program.addFilesPath(encodingResource);
		handler.addProgram(program);
		
	
		
		
		
		//L'handler invoca DLV in modo SINCRONO dando come input il programma logico e i fatti.
		Output o =  handler.startSync();
		
		//Stampiamo su standard output ogni answerset ottenuto.
		AnswerSets answers = (AnswerSets) o;
		int n=0;
		for(AnswerSet a:answers.getAnswersets()){
			try {

				for(Object obj:a.getAtoms()){
					if (obj instanceof Cell) {
						Cell c= (Cell) obj;
							//c.stampa();   stampa le carte del deck e della piramide eliminate
							elimina(c);
							gioca=true;
					}
						
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} 			
		}
		facts.clearAll();
		System.out.println(n);
	
		stampaPiramide();
		stampaDeck();
		System.out.println();
		if (piramideVuota()) { break;}
		
		}
		
		if (!piramideVuota()) System.out.println("GAME OVER");
	
	}//fine main
	
	
	
//------------------------------------------------------- FUNZIONE CARICA GIOCO E STAMPA 	
	




	public static void caricaGioco() {
		Mazzo m= new Mazzo();
		m.generaMazzo();
	
		for (int i=0; i<sizeRow; i++) {
			for (int j=0; j<sizeColumn; j++ ) {
				if(piramide[i][j]==1) {
					m.Top().setVita(1);
					matrix[i][j]=m.Top();
					m.delete();
				}
				if(piramide[i][j]==2) {
					m.Top().setVita(2);
					matrix[i][j]=m.Top();
					m.delete();
				}
				
			}
		}
		int i=0;
		while(!m.isEmpty()) {
			m.Top().setVita(2);
			deck[i]=m.Top();
			m.delete();
			i++;
		}
	}
	
	public static void stampaPiramide() {
		for (int i=0; i<sizeRow; i++) {
			for (int j=0; j<sizeColumn; j++ ) {
				
				if(piramide[i][j] != 0 ) { 
					if (matrix[i][j] != null) matrix[i][j].print();
					else System.out.print("--"); 
				}
				if (piramide[i][j] == 0) System.out.print("__");
				
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static void stampaDeck() {
		for (int i=0; i<deck.length ; i++) {
			if (deck[i] != null) {
				deck[i].print();
				System.out.print(" ");
			}
		}
		
	}
	private static void elimina(Cell c) {
	
		
		if (c.getX()==7) {
			String seme= c.getSeme();
			seme=seme.substring(1, 2);
			for (int i=0; i<deck.length ; i++) { 
				if(deck[i]!= null) {
					String seme2=deck[i].getSeme();
					if (c.getValue() == deck[i].getValue() && seme.equals(seme2)) 
						deck[i]=null;
				}
			}
		}
		
		else {
			int x= c.getX();
			int y= c.getY();
			matrix[x][y]=null;
			piramide[x][y]=0;
			modificavita(x,y);
		}
		
	}



	private static void modificavita(int x, int y) {
		int i=x-1; //riga precedente
		
		for (int j=0; j<sizeColumn ; j++) {
			if (piramide[i][j] == 1) {
				if (libera(i,j)) {
					piramide[i][j]=2;
					matrix[i][j].setVita(2);
				}
			}
		}
		
	}



	private static boolean libera(int x, int y) {
		
		if (piramide[x+1][y-1]== 0 && piramide[x+1][y+1]== 0) return true;
		
		return false;
	}
	
	private static boolean piramideVuota() {
		for (int i=0; i<sizeRow; i++) {
			for (int j=0; j<sizeColumn; j++) {
				if (piramide[i][j]==1 || piramide[i][j]==2) {
					return false;
				}
			}
		}
		System.out.print("DLV WIN!!!");
		return true;
	}
	
	
	
}

	
	