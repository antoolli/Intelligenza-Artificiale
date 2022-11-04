

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cell")
public class Cell {
	@Param(0)
	private int x;
	@Param(1)
	private int y;
	@Param(2)
	private int value;
	@Param(3)
	private String  seme;
	private int vita;
	
	public Cell() {}
	
	

	public Cell(int x, int y, int value, String seme, int vita) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.seme = seme;
		this.vita = vita;
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

	public int getVita() {
		return vita;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}
	
	
	public void stampa() {
		System.out.println(this.x+" "+this.y+" "+ this.value+" "+this.seme);
	}
	
	
}
