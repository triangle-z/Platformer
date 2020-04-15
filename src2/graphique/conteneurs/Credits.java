package graphique.conteneurs;

import global.Global;
import graphique.objets.PlateformeG;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import objetsJeux.Plateforme;

public class Credits extends Scene{
	
	public final int translateValueMax = 100 ;
	public int translateValue ;
	public Text txt ;
	
	public Credits(Group root, int width, int height){
		super(root, width, height, Color.GOLDENROD) ;
		
		translateValue = 0 ;
		
		txt = new Text("Watashi wa kono sekai o hakai suru\nKagetane Hiruko\nBlack bullet\n\n\nBoku no meirei wa zettai da\nAkashi Seijuro\nKuroko no basket") ;
		
		txt.setTextAlignment(TextAlignment.CENTER);
		translate(txt) ;
		
		root.getChildren().add(txt) ;
		
		AnimationTimer timer = new MyTimer(this);
        timer.start();
		
	}
	
	public void translate(Text txt){
		txt.setTranslateY(translateValue) ;
	}
	
	private class MyTimer extends AnimationTimer {
		
		Credits credits ;
		
		public MyTimer(Credits c){
			super() ;
			credits = c ;
		}
		
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			doHandle(now) ;

		}
		
		public void doHandle(long now){
			if(credits.translateValue > 20){
				credits.translateValue -- ;
				credits.translate(credits.txt);
			} else {
				if(credits.translateValue == 20){
					Global.menu.remetMenu() ;
					credits.translateValue -- ;
				}
			}
		}

	}
	
	
}
