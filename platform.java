package omegaproject;



import java.awt.Color;
import java.awt.Graphics;


public class platform {
    public int masse;
    public int longueur;
    public int largeur=20;
    public int[] point1=new int [2],point2=new int [2],point3=new int [2],point4=new int [2];
    public int[] coordx=new int [4],coordy=new int [4];
    public double[] vitesse1,vitesse2;
    public double angle;
    public char accroche1,accroche2;
    public int sigma=20;
    
    
    public  platform( int[] premier, int[] deuxieme){

        point1[0]=premier[0];
        point1[1]=premier[1];
        point2[0]=deuxieme[0];
        point2[1]=deuxieme[1];
        
        angle=getAngle(point1,point2);
        //definition des coordonnées du point en bas à droite
        point3[0]=(int)(point2[0]-Math.sin(angle)*largeur);
        point3[1]=(int)(point2[1]+Math.cos(angle)*largeur);
        
        //definition des coordonnées du point en bas a gauche
        
        point4[0]=(int)(point1[0]-Math.sin(angle)*largeur);
        point4[1]=(int)(point1[1]+Math.cos(angle)*largeur);
        
        
        //calcul de la longueur via pythagore
        longueur=(int)(Math.sqrt((point1[0]-point2[0])*(point1[0]-point2[0])+(point1[1]-point2[1])*(point1[1]-point2[1])));
       
        
        //Tableau des abscisses des 4 points
        coordx[0]=point1[0]-8;
        coordx[1]=point2[0]-8;
        coordx[2]=point3[0]-8;
        coordx[3]=point4[0]-8;
        
        //Tableau des ordonnées des 4 points
        coordy[0]=point1[1]-31;
        coordy[1]=point2[1]-31;
        coordy[2]=point3[1]-31;
        coordy[3]=point4[1]-31;
        
        //definition de la masse de la plateforme
        int aire=longueur*largeur;
        masse=sigma*(aire);
        
        
        
        
        
    }
    public double getAngle(int [] first,int [] last){
        
        float distanceHorizontale=(first[0]-last[0]);
        float distanceVerticale=(first[1]-last[1]);
        
        double angle=Math.atan(distanceVerticale/distanceHorizontale);
        System.out.println(angle);
        
        return angle;}
  /*  public int longueur(platform p){
        int longueur= 0;
       
        longueur=(int)(Math.sqrt((p.point1[0]-p.point2[0])*(p.point1[0]-p.point2[0])+(p.point1[1]-p.point2[1])*(p.point1[1]-p.point2[1])));
        return longueur;}
    
    
    }*/
        
}
    