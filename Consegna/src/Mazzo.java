


import java.util.ArrayList;
import java.util.Collections;

public class Mazzo  {
	
	private ArrayList<Card> mazzo;

	public Mazzo() {
		mazzo= new ArrayList<Card>(); 
	}
	
	public Mazzo(ArrayList<Card> mazzo) {
		this.mazzo = mazzo;
	}
	
	public void add(Card c) {
		mazzo.add(c);
	}
	
	public Card deleteC() {
		Card c= mazzo.get(mazzo.size()-1);
		mazzo.remove(mazzo.size()-1);
		return c;
	}
	public void delete() {
		mazzo.remove(mazzo.size()-1);
	}
	
	public Card Top() {
		return mazzo.get(mazzo.size()-1);
	}
	
	public ArrayList<Card> getMazzo() {
		return mazzo;
	}

	public void setMazzo(ArrayList<Card> mazzo) {
		this.mazzo = mazzo;
	}
	
	public void generaMazzo() {
		//Card fiori
		for (int i=1; i<=13; i++ ) {
			Card c= new Card(i, "f", 1);
			this.add(c);
		}
		//Card cuori
		for (int i=1; i<=13; i++ ) {
			Card c= new Card(i, "c", 1);
			this.add(c);
		}
		//Card quadri
		for (int i=1; i<=13; i++ ) {
			Card c= new Card(i, "q", 1);
			this.add(c);
		}
		//Card Picche
			for (int i=1; i<=13; i++ ) {
				Card c= new Card(i, "p", 1);
				this.add(c);
			}
		Collections.shuffle(mazzo); //mescola mazzo
	}
	
	public boolean isEmpty(){
			return mazzo.isEmpty();
		
	}
	
	public int size() {
		return mazzo.size();
	}
	
	
	
}
