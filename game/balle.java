package omegaproject;

public class balle {
    public double masse;
    public int positionx;
    public int positiony;
    public int rayon;
    public double vitessex;
    public double vitessey;
    public final int MASSESURFACIQUE=50;
    public char accroche;
    
    
    public balle (int clicx, int clicy, int rayondef) {
        positionx=clicx-8;
        positiony=clicy-50;
        rayon=rayondef;
        masse=3.14*rayon*rayon*MASSESURFACIQUE; // actuellement la masse varie de 0 à 1 570 000
        accroche = 'e';
    }
    
}
