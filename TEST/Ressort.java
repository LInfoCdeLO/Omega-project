package platform;

import java.awt.image.*;
import javax.swing.*;
//import java.swing.event.*;
//import java.util.*;
import java.awt.event.*;
import java.awt.*;




public class Ressort extends JFrame  {

    int interval;
    Timer timer;
    long cycles;
    Graphics buffer;   
    BufferedImage ArrierePlan; 
    int largeur=25;
    int longueur=100;
    int masse=30;
    int raideur=1;
    int longueurDeBase=50;
    double vitesse;

    
    
    public Ressort(int w, int h) {
       interval=40 ; 
       cycles=0;
       setResizable(true);

       setSize(w,h);
       ArrierePlan = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB); 
       buffer = ArrierePlan.getGraphics(); 





        timer = new Timer(interval, new TimerAction());
    

         timer.start();  
    
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        }

    
   


    class TimerAction implements ActionListener {
 

                // ActionListener appelee 25 fois par secondes  
        public void actionPerformed(ActionEvent e) {
                  repaint();    
                cycles++;

                }
    }
    public void paint(Graphics g) {
        buffer.setColor( Color.black );
        buffer.fillRect(0,0,getSize().width,getSize().height);
      
        vitesse=((raideur*(longueurDeBase-longueur))*2-vitesse)/masse+vitesse;
        int a=longueur;
        longueur=(int)(vitesse*2)+longueur;
       if (a-longueur<0){largeur=largeur-1;}
       if (a-longueur>0){largeur++;}
        
        buffer.setColor( Color.white );
        buffer.drawRect(20,200,largeur,longueur);
        g.drawImage(ArrierePlan,0,0,this);
        System.out.println(vitesse);
    }

     public static void main(String[] args) {
        Frame Mafenetre;
        Mafenetre = new Ressort(800,400);
        
    }

}
