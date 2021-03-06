package objetsPhysiques;
import objetsMathematiques.Vecteur;
import global.Global; ;

public class RigidBody {
	public Vecteur position ;
	private double theta ;						//angle formé par le Vecteur position
	public Vecteur vitesse ;
	private Vecteur acceleration ;
	private Vecteur sommeDesForces ;
	private double masse ;
	private double Cx ;							//coefficient de trainee du corps
	private Vecteur poids ;
	private Vecteur frottements ;
	private double surfaceTransversale ;					//surface qui va subir les frottements 
													//(section plane maximale orthogonale au saut)
	private double surfaceFrontale ;						//coupe longitudinale du corps
	public boolean contactBas ;
	public boolean contactDroit ;
	public boolean contactGauche ;
	private double longueurJambeRepos ;			//données pour le saut servant
	private double longueurJambeContractee ;			//a modeliserles jambes
	private double raideur ;							//par un ressort propulsant le corps
	
	private double width ;
	private double height ;
	
	
	public RigidBody(Vecteur aPosition, Vecteur aVitesse, double aMasse, double aCx, double aSurfaceF, double aSurfaceT, double aLongueurJambeRepos, double aLongueurJambeContractee, double aRaideur, double aWidth, double aHeight) {
		width = aWidth ;
		height = aHeight ;
		position = aPosition;
		majTheta() ;
		vitesse = aVitesse;
		acceleration = new Vecteur(0, 0) ; //inutile de préciser une acceleration initiale
		masse = aMasse;
		poids = new Vecteur(0, - masse * Global.g) ;
		Cx = aCx;
		surfaceFrontale = aSurfaceF;
		surfaceTransversale = aSurfaceT;
		longueurJambeRepos = aLongueurJambeRepos;
		longueurJambeContractee = aLongueurJambeContractee;
		raideur = aRaideur;
		frottements = new Vecteur(0, 0) ;
		majFrottements() ;
		majFrottements() ; //deux fois pour reinitialiser xTemp et yTemp
		contactBas = false ;
		contactDroit = false ;
		contactGauche = false ;
		sommeDesForces = poids.sommeVect(frottements) ;
	}
	
	public void deplacement(){
		majTheta() ;
		majFrottements() ;
		sommeDesForces = poids.sommeVect(frottements) ;
		if(contactBas){
			if(Global.derniereCollisionBas.xMax > position.x && Global.derniereCollisionBas.xMin < position.x + width){
				sommeDesForces.y = 0 ;
			} else {
				contactBas = false ;
			}
		}
		//System.out.println("Somme des forces : " + sommeDesForces) ;
		majAcceleration() ;
		//System.out.println("Acceleration : " + acceleration) ;
		majVitesse() ;
		if(contactDroit){
			if(Global.derniereCollisionDroit.yMax > position.y && Global.derniereCollisionDroit.yMin < position.y + height){
				vitesse.x = 0 ;
			} else {
				contactDroit = false ;
			}
		}

		if(contactGauche){
			if(Global.derniereCollisionGauche.yMax > position.y && Global.derniereCollisionGauche.yMin < position.y + height){
				vitesse.x = 0 ;
			} else {
				contactGauche = false ;
			}
		}
		//System.out.println("Vitesse : " + vitesse) ;
		majPosition() ;
		//System.out.println("Position : " + position) ;

		//System.out.println() ;
	}

	public void majTheta(){
		theta = position.getArgument() ;
	}
	
	public void majFrottements(){
		double resistance = 0.5 * Cx * Global.rho * surfaceFrontale * Math.pow(vitesse.getModule(), 2) ;
		double frottementX = resistance * Math.cos(theta) ;
		double frottementY = resistance * Math.sin(theta) ;
		frottements.modifierComposants(frottementX, frottementY) ;
	}
	
	private void majAcceleration() {
		acceleration.modifierComposants(sommeDesForces.produitReel(1 / masse)) ;
	}
	
	private void majVitesse() {
		double newVx = Global.dt * acceleration.getX() + vitesse.getX() ;
		double newVy = Global.dt * acceleration.getY() + vitesse.getY() ;
		vitesse.modifierComposants(newVx, newVy) ;
	}
	
	private void majPosition() {
		double newX = Global.dt * vitesse.getX() + position.getX() ;
		double newY = Global.dt * vitesse.getY() + position.getY() ;
		position.modifierComposants(newX, newY) ;
	}

	public void forcePlacement(double x, double y) {
		position.modifierComposants(x, y);
	}
	
	public void deplaceDroite(){
		if(!contactDroit){
			vitesse.modifierComposants(/*vitesse.getX() +*/ 1, vitesse.getY()) ;
			contactGauche = false ;
		}
		Global.joueurG.cycle = 1 ;
		Global.joueurG.orientation = 0 ;
	}
	
	public void deplaceGauche(){
		if(!contactGauche){
			vitesse.modifierComposants(/*vitesse.getX()*/ - 1, vitesse.getY()) ;
			contactDroit = false ;
		}
		Global.joueurG.cycle = 1 ;
		Global.joueurG.orientation = 1 ;
	}
	
	public void arreteDeplace(){
		//if(contactBas){
			vitesse.modifierComposants(0, vitesse.getY()) ;
		//}
			Global.joueurG.cycle = 0 ;
	}
	
	
	
	public void saute(){
		if(contactDroit || contactGauche){
			vitesse.modifierComposants(0, 1) ;
			contactBas = false ;
		} else {
			if(contactBas){
				double vsCarre = longueurJambeContractee - longueurJambeRepos ;
				vsCarre *= raideur * (longueurJambeContractee - longueurJambeRepos) - masse * Global.g ;
				vsCarre /= masse + (longueurJambeContractee - longueurJambeRepos) * Cx * Global.rho * surfaceTransversale ;
				vitesse.modifierComposants(vitesse.getX(), Math.sqrt(Math.abs(vsCarre))) ;
				//vitesse.modifierComposants(vitesse.getX(), 6.935) ;
				contactBas = false ;
			}
		}
	}
	
}
