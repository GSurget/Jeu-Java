package cars;

public class Configuration {
	
	String voiture1;
	String voiture2;
	String circuit;
	int mode;
	
	public Configuration(){
		
	}
	
	public String getVoiture1(){
		return voiture1;
	}
	
	public void setVoiture1(String newVoiture1){
		voiture1 = newVoiture1;
	}
	
	public String getVoiture2(){
		return voiture2;
	}
	
	public void setVoiture2(String newVoiture2){
		voiture2 = newVoiture2;
	}
	
	public String getCircuit(){
		return circuit;
	}
	
	public void setCircuit(String newCircuit){
		circuit = newCircuit;
	}
	
	public int getMode(){
		return mode;
	}
	
	public void setMode(int newMode){
		mode = newMode;
	}
}

