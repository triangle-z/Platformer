import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Image;

public class MainCharacter extends Character {

	
	public MainCharacter(String n, int m){
	try {
		
		URL url = new URL("//image/Test.jpg");
		Image image = ImageIO.read(url);
		this.animation.add(image);
	
	}catch (IOException e) {
            e.printStackTrace();
		}
		
		this.nom=n;
		this.masse=m;

	}
}
