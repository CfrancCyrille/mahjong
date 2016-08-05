package com.squirrel.model;

public interface Valuable {

	public int getValue();
	public String getName();
	public int ordinal();
	/**
	 * 	
	public enum NumTuile implements Valuable{
		UN("un"),DEU("deux"),TRO("trois"),QUA("quatre"),CIN("cinq"),SIX("six"),SEP("sept"),HUI("huit"),NEU("neuf");
		String nomNum;
		int value;
		private NumTuile(String nomNum){
			this.nomNum=nomNum;
			this.value=this.ordinal();
		}
		public int getValue(){
			return this.value;
		}
		public String getName(){
			return nomNum;
		}
	}
	
	public enum VenTuile implements Valuable{
		EST("est"),SUD("sud"),OUE("ouest"),NOR("nord");
		String nomNum;
		int value;
		private VenTuile(String nomNum){
			this.nomNum=nomNum;
			this.value=this.ordinal();
		}
		public int getValue(){
			return this.value;
		}
		public String getName(){
			return nomNum;
		}
	}
	
	public enum DraTuile implements Valuable{
		BLA("blanc"),VER("vert"),ROU("rouge");
		String nomNum; 
		int value;
		private DraTuile(String nomNum){
			this.nomNum=nomNum;
			this.value=this.ordinal();
		}
		public int getValue(){
			return this.value;
		}
		public String getName(){
			return nomNum;
		}
	}
	
	public enum SaiTuile implements Valuable{
		PRI("printemps"),ETE("été"),AUT("automne"),HIV("hiver");
		String nomNum; 
		int value;
		private SaiTuile(String nomNum){
			this.nomNum=nomNum;
			this.value=this.ordinal();
		}
		public int getValue(){
			return this.value;
		}
		public String getName(){
			return nomNum;
		}
	}
	
	public enum FleTuile implements Valuable{
		PRU("prunier"),ORC("orchidée"),CHR("chrysanthème"),BAM("bambou");
		String nomNum;
		int value;
		private FleTuile(String nomNum){
			this.nomNum=nomNum;
			this.value=this.ordinal();
		}
		public int getValue(){
			return this.value;
		}
		public String getName(){
			return nomNum;
		}
	}
	 */
	
}
