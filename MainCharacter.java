import java.util.LinkedList;



public class MainCharacter extends Character {

	
	public MainCharacter(String n, int m){
		
		this.image.add("image/main_character/stand_up/1.png");

		for(int i=1;i<7;i++){
			this.image.add("image/main_character/run_cycle/"+i+".jpg");
		}
		
		for(int i=1;i<6;i++){
			this.image.add("image/main_character/jump_cycle/"+i+".jpg");
		}

		
		this.nom=n;
		this.masse=m;

	}
}
