package objetsPhysiques;
import objetsMathematiques.Vecteur;
import global.Global; ;

public class RigidBody {
	public Vecteur position ;
	private double theta ;						//angle formé par le Vecteur position
	public Vecteur vitesse ;
	private Vecteur acceleration ;
	private Vecteur sommeDesForces ;
	private double g ;							//constante d'acceleration de la gravite 
	private double masse ;
	private double Cx ;							//coefficient de trainee du corps
	private Vecteur poids ;
	private Vecteur frottements ;
	private double rho ;						//masse volumique du fluide dans lequel le personnage est
	private double surface ;					//surface qui va subir les frottements 
	public boolean contactBas ;													//(section plane maximale orthogonale au saut)
	private double longueurJambeRepos ;			//données pour le saut servant
	private double longueurJambeContractee ;			//a modeliserles jambes
	private double raideur ;							//par un ressort propulsant le corps
	
	
	public RigidBody(Vecteur aPosition, Vecteur aVitesse, double aG, double aMasse, double aCx,
			double aRho, double aSurface, double aLongueurJambeRepos, double aLongueurJambeContractee, double aRaideur) {
		position = aPosition;
		majTheta() ;
		vitesse = aVitesse;
		acceleration = new Vecteur(0, 0) ; //inutile de préciser une acceleration initiale
		g = aG;
		masse = aMasse;
		poids = new Vecteur(0, - masse * g) ;
		Cx = aCx;
		rho = aRho;
		surface = aSurface;
		longueurJambeRepos = aLongueurJambeRepos;
		longueurJambeContractee = aLongueurJambeContractee;
		raideur = aRaideur;
		frottements = new Vecteur(0, 0) ;
		majFrottements() ;
		majFrottements() ; //deux fois pour reinitialiser xTemp et yTemp
		contactBas = false ;
		sommeDesForces = poids.sommeVect(frottements) ;
	}
	
	public void deplacement(){
		majTheta() ;
		majFrottements() ;
		sommeDesForces = poids.sommeVect(frottements) ;
		if(contactBas){
			sommeDesForces.y = 0 ;
		}
		//System.out.println("Somme des forces : " + sommeDesForces) ;
		majAcceleration() ;
		//System.out.println("Acceleration : " + acceleration) ;
		majVitesse() ;
		//System.out.println("Vitesse : " + vitesse) ;
		majPosition() ;
		//System.out.println("Position : " + position) ;

		//System.out.println() ;
	}

	public void majTheta(){
		theta = position.getArgument() ;
	}
	
	public void majFrottements(){
		double resistance = 0.5 * Cx * rho * surface * Math.pow(vitesse.getModule(), 2) ;
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
		vitesse.modifierComposants(vitesse.getX() + 10, 0) ;
		contactBas = false ;
	}
	
	public void deplaceGauche(){
		vitesse.modifierComposants(vitesse.getX() - 10, 0) ;
		contactBas = false ;
	}
	
}
