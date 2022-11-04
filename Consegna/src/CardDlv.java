import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cardDlv")
public class CardDlv {
	@Param(0)
	private int x;
	@Param(1)
	private int y;
	@Param(2)
	private int value;
	@Param(3)
	private String seme;
	@Param(4)
	private String posizione; // p=carta nella piramide // d= carta nel deck
	
	
	
	
	public CardDlv() {}




	public CardDlv(int x, int y, int value, String seme, String posizione) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.seme = seme;
		this.posizione=posizione;
	}




	public int getX() {
		return x;
	}




	public void setX(int x) {
		this.x = x;
	}




	public int getY() {
		return y;
	}




	public void setY(int y) {
		this.y = y;
	}




	public int getValue() {
		return value;
	}




	public void setValue(int value) {
		this.value = value;
	}




	public String getSeme() {
		return seme;
	}




	public void setSeme(String seme) {
		this.seme = seme;
	}
	
	
	
	public String getPosizione() {
		return posizione;
	}




	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}




	public void stampa() {
		System.out.print(this.x+" "+this.y+" " +this.value+ this.seme);
	}
	
	
}
