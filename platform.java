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

        point1[0]=premier[0]-8;
        point1[1]=premier[1]-31;
        point2[0]=deuxieme[0]-8;
        point2[1]=deuxieme[1]-31;
        
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
        coordx[0]=point1[0];
        coordx[1]=point2[0];
        coordx[2]=point3[0];
        coordx[3]=point4[0];
        
        //Tableau des ordonnées des 4 points
        coordy[0]=point1[1];
        coordy[1]=point2[1];
        coordy[2]=point3[1];
        coordy[3]=point4[1];
        
        //definition de la masse de la plateforme
        int aire=longueur*largeur;
        masse=sigma*(aire);
        
        //definition points d'accroche
        accroche1='e';
        accroche2='e';
        
        
        
        
    }
    public double getAngle(int [] first,int [] last){
        
            float distanceHorizontale=(first[0]-last[0]);
            float distanceVerticale=(first[1]-last[1]);
            
            double angle=Math.atan(distanceVerticale/distanceHorizontale);
            if (angle<0 && first[1]<last[1]){
                angle= Math.PI+angle;
            }
            if(angle>0 && first[1]>last[1]){
                angle=angle-Math.PI;
            }
            System.out.println(angle);
            
            return angle;}
    
    
    
        
}
    