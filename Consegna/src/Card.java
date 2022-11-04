

	public class Card {
		private int value;
		private String seme;
		private int vita; //0cancella 1=coperta 2=libera
		
		
		
		public Card() {}



		public Card(int value, String seme, int vita) {
			super();
			this.value = value;
			this.seme = seme;
			this.vita=vita;
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



		public void print() {
			System.out.print(value+seme);
		}
		
		
		
	

}
