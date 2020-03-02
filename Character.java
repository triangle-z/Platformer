import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Image;

abstract class Character{
	
	protected String nom;
	protected int masse;
	LinkedList<Image> animation = new LinkedList<Image>();
}
	
