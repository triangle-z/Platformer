package objetsMathematiques;
import global.Global;

public class Vecteur {
	
	//coordonnees actuelles		(à t)
	private double x ;

	private double y ;
	
	//coordonnees précédentes	(à t-dt)
	private double xTemp ;
	private double yTemp ;
	
	private double module ;
	private double argument ;
	
	public Vecteur(double aX, double aY){
		x = aX ;
		y = aY ;
		xTemp = aX ;
		yTemp = aY ;
		changeModule() ;
		changeArgument() ;
	}
	
	public double[] composantes(){
		double[] res = {x, y} ;
		return res ;
	}
	
	public void modifierComposants(double aX, double aY){

		xTemp = x ; //stock les anciennes composantes
		yTemp = y ;
		x = aX ; // change les nouveaux
		y = aY ;
		changeModule() ; //mets a jour le module
	}
	
	public void modifierComposants(Vecteur aVect){
		
		double aX = aVect.getX() ;
		double aY = aVect.getY() ;
		
		modifierComposants(aX, aY) ;
	}
	
	private void changeArgument(){
		if(x == 0){	// si x vaut 0, l'arctangente n'est pas définie
			if(y == 0){
				argument = 0 ;
			} else {
				if(y > 0){
					argument = Math.PI / 2 ;
				} else {
					argument = - Math.PI / 2 ;					
				}
			}
		} else {
			argument = Math.atan(y / x) ;
		}
	}
	
	public double getArgument() {
		return argument;
	}

	private void changeModule(){
		double moduleCarre = Math.pow(x, 2) + Math.pow(y, 2) ;
		module = Math.sqrt(moduleCarre) ;
	}
	
	public double getModule(){
		return module ;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public Vecteur deriveVecteur(){
		double dx = deriveX() ;
		double dy = deriveY() ;
		Vecteur dV = new Vecteur(dx, dy) ;
		return dV ;
	}
	
	public double deriveX(){
		double dx = Global.dt * Math.abs(x - xTemp) ;
		return dx ;
	}
	
	public double deriveY(){
		double dy = Global.dt * Math.abs(y - yTemp) ;
		return dy ;
	}
	
	///////////////////////////*** Opérations ***\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public Vecteur sommeReel(double terme){		//Cette operation n'existe pas en maths, c'est moi qui l'ai definit ici
		double aX = x + terme ;
		double aY = y + terme ;
		Vecteur aVect = new Vecteur(aX, aY) ;
		return aVect ;
	}
	
	public Vecteur sommeVect(Vecteur terme){
		double aX = x + terme.x ;
		double aY = y + terme.y ;
		Vecteur aVect = new Vecteur(aX, aY) ;
		return aVect ;
	}
	
	public Vecteur produitReel(double terme){
		double aX = x * terme ;
		double aY = y * terme ;
		Vecteur aVect = new Vecteur(aX, aY) ;
		return aVect ;
	}
	
	public Vecteur produitVect(Vecteur terme){	//Cette operation n'existe pas en maths, c'est moi qui l'ai definit ici
		double aX = x * terme.x ;
		double aY = y * terme.y ;
		Vecteur aVect = new Vecteur(aX, aY) ;
		return aVect ;
	}
	
	public Matrice multiplieMatrice(Matrice M){ // this * matrice et non l'inverse
		double[][] values = {{x}, {y}} ;
		Matrice res = new Matrice(values) ;
		Matrice produit = res.multiplier(M) ;
		return produit ;
	}
	
}
