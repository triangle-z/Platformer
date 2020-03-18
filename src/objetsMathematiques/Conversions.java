package objetsMathematiques;

public class Conversions {
	
	private static double xMinFenetreMetric = 0 ;
	private static double yMinFenetreMetric = -10 ;
	private static double widthFenetreMetric = 20 ;
	private static double heightFenetreMetric = 20 ;

	public static int widthFenetrePixel = 720 ;
	public static int heightFenetrePixel = - 400 ;
	private static int xMinFenetrePixel = 0 ;
	private static int yMinFenetrePixel = - heightFenetrePixel ;
	
	//Conversion de metric vers pixel : matrice de passage {{a,b},{c,d}} et decalage matrice {e,f}
	
	private static double xM1 = xMinFenetreMetric ;
	private static double xM2 = xMinFenetreMetric ;
	private static double xM3 = xMinFenetreMetric + widthFenetreMetric ;
	private static double yM1 = yMinFenetreMetric ;
	private static double yM2 = yMinFenetreMetric + heightFenetreMetric ;
	private static double yM3 = yMinFenetreMetric + heightFenetreMetric ;
	private static double xP1 = xMinFenetrePixel ;
	private static double xP2 = xMinFenetrePixel ;
	private static double xP3 = xMinFenetrePixel + widthFenetrePixel ;
	private static double yP1 = yMinFenetrePixel ;
	private static double yP2 = yMinFenetrePixel + heightFenetrePixel ;
	private static double yP3 = yMinFenetrePixel + heightFenetrePixel ;
	
	private static double b = (double) ((xP3 - xP1) * (xM2 - xM1) - (xP2 - xP1) * (xM3 - xM1)) / ((yM3 - yM1) * (xM2 - xM1) - (yM2 - yM1) * (xM3 - xM1)) ;
	private static double a = (xP2 - xP1 - b * (yM2 - yM1)) / (xM2 - xM1) ;
	private static double e = xP1 - a * xM1 - b * yM1 ;
	private static double d = (double) ((yP3 - yP1) * (xM2 - xM1) - (yP2 - yP1) * (xM3 - xM1)) / ((yM3 - yM1) * (xM2 - xM1) - (yM2 - yM1) * (xM3 - xM1)) ;
	private static double c = (yP2 - yP1 - d * (yM2 - yM1)) / (xM2 - xM1) ;
	private static double f = yP1 - c * xM1 - d * yM1 ;
	
	private static double[][] valeursMatPassage1 = {{a,b},{c,d}} ;
	private static double[][] valeursDecalage1 = {{e},{f}} ;
	private static Matrice matPassage1 = new Matrice(valeursMatPassage1) ;
	private static Matrice decalage1 = new Matrice(valeursDecalage1) ;
	
	public static int[] metricToPixel (double[] metricT){
		double[][] metricT2 = {{metricT[0]}, {metricT[1]}} ;
		Matrice metric = new Matrice(metricT2) ;
		Matrice pixel = metric.transformationAffine(matPassage1, decalage1) ;
		int[] valeurs = {(int)pixel.tab[0][0], (int)pixel.tab[1][0]} ;
		return valeurs ;
	}
	
	//idem de pixel vers metric
	private static Matrice matPassage2 = matPassage1.inverse() ;
	private static Matrice decalage2 = matPassage1.inverse().multiplier(decalage1.multiplier(-1)) ;
	
	public static double[] pixelToMetric (double[] pixelT){
		double[][] pixelT2 = {{pixelT[0]}, {pixelT[1]}} ;
		Matrice pixel = new Matrice(pixelT2) ;
		Matrice metric = pixel.transformationAffine(matPassage2, decalage2) ;
		double[] valeurs = {metric.tab[0][0], metric.tab[1][0]} ;
		return valeurs ;
	}
	
}
