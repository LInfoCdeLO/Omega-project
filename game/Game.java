import java.awt.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements MouseListener, MouseMotionListener, KeyListener{
	Image background;
        Graphics buffer;
        BufferedImage Arriereplan;
	Timer timer;
	final public double G=0.5; // accï¿½lï¿½ration gravitationnel, c'est marrant de la mettre nï¿½gatif :)
	final public int SCREENX=600;
	final  public int SCREENY=480;
	final public int MAXMOVOBJECTS = 100;
        MovObject [] movobjects;
        int j=0;
        int mx;
        int my;
        int mdx;
        int mdy;
        boolean tracerLine=false;
        boolean shift=false;
        boolean tracerPlat=false;
        public GridLayout gridLayoutPrincipal;
        public GridLayout gridLayoutSecondaire;
        public JPanel panel;
        public JButton bouton1;
        


	/***********************************************
	 * The timer class which contains a method 
	 * called periodically by the timer.
	 ***********************************************/

	class TimerClass implements ActionListener {

		public void actionPerformed (ActionEvent e) {
			simulate();
			repaint();
		}
	}

	/***********************************************
	 * Constructor
	 ***********************************************/

	public Game () {

		background=null;
                
		try {
			background = ImageIO.read (new File ("background.jpg"));
		}
		catch (IOException e){
			System.out.println ("Could not load image file.");
		}

		movobjects = new MovObject[MAXMOVOBJECTS];
		movobjects[0] = new Platform (50,400,300,2);
		movobjects[1] = new Platform (400,250,150,3);
		movobjects[2] = new Platform (10,200,50,4);
                //movobjects[3] = new Shot(150,150,0,10); // position x et y, numero objet, diametre
		//movobjects[4] = new Shot(250,50,1,10);
		
                /* 
                gridLayoutPrincipal = new GridLayout(1,2);
                gridLayoutSecondaire = new GridLayout(1,1);
                
                this.getContentPane().setLayout(gridLayoutPrincipal);

                
                bouton1= new JButton("abracadabra");
           
                
                panel = new JPanel();
                panel.setLayout(gridLayoutSecondaire);
                panel.add(bouton1);

                this.getContentPane().add(??????????????????????);
                this.getContentPane().add(panel);
                */

                this.setSize(SCREENX,SCREENY);
                this.setTitle ("Awesome Computer Game by Omage Project");
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		

		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
                
                
                Arriereplan = new BufferedImage(SCREENX,SCREENY,BufferedImage.TYPE_INT_RGB);
                buffer = Arriereplan.getGraphics();
                
		timer = new Timer (40, new TimerClass());
		timer.start();
		
	}

	/***********************************************
	 * Simulate
	 ***********************************************/

	public void simulate () {

		for (int i=0; i<MAXMOVOBJECTS; ++i) {
			if (movobjects[i]!=null) {
				movobjects[i].simulate(movobjects);
			}
		}
                
		
		
	}

	/***********************************************
	 * DRAW the contents of our window
	 ***********************************************/

	public void paint (Graphics g)  {		

		if (background!=null) {
			buffer.drawImage (background, 0, 0, this);	
		}
	//tem.out.println (background);

		buffer.setColor(Color.red);
		for (int i=0; i<MAXMOVOBJECTS; ++i) {
			if (movobjects[i]!=null) {
				movobjects[i].paint(buffer);

								
			}
		}
                if(tracerLine) {
                    buffer.drawLine(mx, my, mdx, mdy);
                }
                buffer.setColor(Color.black);
                if(movobjects[j]!=null) {
                    if(movobjects[j].t=='b') {
                        if(movobjects[j].deplacementauto){
                            buffer.drawString("la balle actuellement selectionné est la balle "+j+", elle est en commande automatique ",getInsets().left,getInsets().top+15);
                        }
                        else
                        buffer.drawString("la balle actuellement selectionné est la balle "+j+", elle est en commande manuelle ",getInsets().left,getInsets().top+15);

                    }
                    if(movobjects[j].t=='p' ){
                        buffer.drawString("plateforme "+j+ " selectionne, il est impossible de la déplacer",getInsets().left,getInsets().top+15);
                    }
                }
                else {
                    buffer.drawString("l'objet "+j+ " selectionne n'existe pas",getInsets().left,getInsets().top+15);

                }
                
                
                if(tracerPlat) {
                    buffer.drawString("tracer de plateforme en cours",getInsets().left,getInsets().top+30);

                    buffer.setColor(Color.red);
                    if(mdx>mx && mdy>my){
                        buffer.fillRect(mx,my,(mdx-mx),(mdy-my));
                    }
                    if(mdx>mx && mdy<=my){
                        buffer.fillRect(mx,mdy,(mdx-mx),(my-mdy));
                    }
                    if(mdx<=mx && mdy>my){
                        buffer.fillRect(mdx,my,(mx-mdx),(mdy-my));
                    }
                    if(mdx<=mx && mdy<=my){
                        buffer.fillRect(mdx,mdy,(mx-mdx),(my-mdy));
                    }
                    
                    
                }
                g.drawImage(Arriereplan,0,0,this);
                
	    
	}

	/***********************************************
	 * The mouse events
	 ***********************************************/

	public void mouseExited( MouseEvent e ) { }        
    public void mousePressed( MouseEvent e ) { 
        int mouse=e.getButton();
        if(mouse==MouseEvent.BUTTON1){ //clic gauche
           
            if ((e.getX()-movobjects[j].d/2)<=(movobjects[j].x+movobjects[j].d/2) && (e.getX()-movobjects[j].d/2)>=(movobjects[j].x-movobjects[j].d/2) && (e.getY()-movobjects[j].d/2)<=(movobjects[j].y+movobjects[j].d/2) && (e.getY()-movobjects[j].d/2)>=(movobjects[j].y-movobjects[j].d/2) && movobjects[j].t=='b' && movobjects[j].deplacementauto==false  ){
           
                movobjects[j].deplacdrag=true;
               
            }
           
        }
       
        
        if(mouse==MouseEvent.BUTTON3 && shift==false){ // clic droit
            System.out.println("coo x "+e.getX()+" et coo y "+e.getY()+" du clic droit");
            mx=e.getX();
            my=e.getY();
            mdx=mx;
            mdy=my;
            tracerLine=true;
            
        }
        if(mouse==MouseEvent.BUTTON3 && shift==true){ // clic droit
            mx=e.getX();
            my=e.getY();
            mdx=mx;
            mdy=my;
            tracerPlat=true;

        }
        
       
        

    	

    }
    public void mouseReleased( MouseEvent e ) { 
        int mouse=e.getButton();
        if(mouse==MouseEvent.BUTTON1){ //clic gauche
            movobjects[j].deplacdrag=false;
        }

        

        if(mouse==MouseEvent.BUTTON3 && tracerPlat==false){
            System.out.println("bouton relaché");
            for (int i=0; i<MAXMOVOBJECTS; ++i) {
                    if (movobjects[i]==null) {
                            movobjects[i] = new Shot(mx, my,i,10);
                            movobjects[i].dx=(mx-mdx)/10;
                            movobjects[i].dy=(my-mdy)/10;
                            tracerLine=false;
                            mx=mdx;
                            my=mdy;
                            break;
                    }
            }
        }
        
        if(mouse==MouseEvent.BUTTON3 && tracerPlat==true){
            System.out.println("bouton relaché");
            for (int i=0; i<MAXMOVOBJECTS; ++i) {
                    if (movobjects[i]==null) {
                        if(mdx>mx && mdy>my){
                            movobjects[i] = new Platform (mx, my,(mdx-mx),(mdy-my),i);
                        }
                        if(mdx>mx && mdy<=my){
                            movobjects[i] = new Platform (mx, mdy,(mdx-mx),(my-mdy),i);
                        }
                        
                        if(mdx<=mx && mdy>my){
                            movobjects[i] = new Platform (mdx, my,(mx-mdx),(mdy-my),i);
                        }
                        if(mdx<=mx && mdy<=my){
                            movobjects[i] = new Platform (mdx, mdy,(mx-mdx),(my-mdy),i);
                        }
                        
                        tracerPlat=false;
                            break;
                    }
            }
        }
        
    }
    public void mouseDragged( MouseEvent e ) {
        mdx=e.getX();
        mdy=e.getY();
        
        if(movobjects[j].deplacdrag) {
            mdx=e.getX()-movobjects[j].d/2;
            mdy=e.getY()-movobjects[j].d/2;
            movobjects[j].x=mdx;
            movobjects[j].y=mdy;
         
        }
        
        
        
        
    }
    public void mouseEntered( MouseEvent e ) { }
    public void mouseMoved( MouseEvent e ) {  
    }
    public void mouseClicked( MouseEvent e )  {
        }
    public void keyPressed(KeyEvent e) {
        display(e);
        gestionClavier(e);
    }
    public void keyReleased (KeyEvent e) {
            if(e.getKeyCode()==16) {
                shift=false;
            }
        }
    public void keyTyped (KeyEvent e) {}
    
    public void gestionClavier(KeyEvent e) {
        /*
         * Partie en cours de travail
         * appuyer sur le clavier numérique pour selectionné une balle (de 0 à 9, 2,3,4 exclut car c'est des platformes)
         * appuyer sur espace pour arreter la balle
         * diriger sa vitesse avec les fleches directionnelles
         * appuyer sur espace pour lui rendre sa vitesse initial
         * selectionner a tout moment une autre balle avec la pavé numerique (balle 0 selectionné par défault);
         * appuyer sur a affiche les coo de l'object selectionné
         * 
         * faudrai faire una affichage sur le coté pour savoir plus clairement quelle balle est actuellement selectionné (c'est en haut pour l'instant)
         * le cas de la balle qui n'existe pas encore est pas gerer, faudra propablement faire un try and catch
         */
        
        
        
        if(e.getKeyCode()>=96 && e.getKeyCode()<=105){
            j=e.getKeyCode()-96;
            
            if (movobjects[j].t=='b') {
                System.out.println ("balle "+j+" sélectionnée");
            }
            if (movobjects[j].t=='p'){
                System.out.println("erreur, plateforme "+j+" selectionné");
            }
            if (movobjects[j]==null) 
                System.out.println("L'object sélectionné n'existe pas encore");
        }
        
        if(e.getKeyCode()==32) {
            movobjects[j].g=0;
            movobjects[j].dy=0;
            movobjects[j].dx=0;
            if (movobjects[j].deplacementauto==true && movobjects[j].t=='b'){
                movobjects[j].deplacementauto=false;
                System.out.println("balle "+j+" a l'arret");   

            }
            else  if ((movobjects[j].deplacementauto==false && movobjects[j].t=='b')){
                movobjects[j].deplacementauto=true;
                movobjects[j].g=G;
                movobjects[j].dx=5  ;
                System.out.println("balle "+j+" en mouvement");   
            }
        }
        if(movobjects[j].deplacementauto==false && e.getKeyCode()==39){
            movobjects[j].dx+=2;
        }
        if(movobjects[j].deplacementauto==false && e.getKeyCode()==38){
            movobjects[j].dy-=2;
        }
        if(movobjects[j].deplacementauto==false && e.getKeyCode()==37){
            movobjects[j].dx-=2;
        }
        if(movobjects[j].deplacementauto==false && e.getKeyCode()==40){
            movobjects[j].dy+=2;
        }
        if(e.getKeyCode()==65){
            System.out.println(movobjects[j].x+"    "+movobjects[j].y);
        }
        if(e.getKeyCode()==16) {
            shift=true;
        }
    }
    
    public void display (KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("la touche "+keyCode+" a été appuyé");
    }
  


	public static void main (String [] args) {
		
		Game g = new Game();	
	}
    
}
