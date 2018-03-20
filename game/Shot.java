import java.awt.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class Shot extends MovObject{
		final double G=0.5; // accï¿½lï¿½ration gravitationnel, c'est marrant de la mettre nï¿½gatif :)
		final int SCREENX=600;
		final int SCREENY=480;
		final int MAXMOVOBJECTS = 100;
		
                
                int dpx,dpy;
                
                

		public Shot (int nx, int ny, int nn, int nd) {
			this.x = (nx-nd/2);
			this.y = (ny-nd/2);
                        this.n=nn;
                        this.d=nd;
			this.dx = 5;
                        this.t ='b';
                        this.g=G;
                        this.deplacementauto=true;
                        
                        
		}

		public void simulate (MovObject[] tab) {
			dy += g;
                        dpx=x;
                        dpy=y;
			x += (int) Math.round(dx); // Math.round : Pour les types float et double, cette mï¿½thode ajoute 0,5 et arrondi a l'entier le plus proche (et 5 ï¿½a s'arrondi en supï¿½rieur bande de tard)
			y += (int) Math.round(dy);
                        
                        double masse= 1;   // masse de la balle
                        double masseP=100; // masse des plateformes et des murs
                        
                        

			if (y+d+10>SCREENY) {
				/*dy*= -0.8;
                                dx*=0.95;*/
                                System.out.println("dx="+dx+" et dy="+dy);
				dx= -((0.5)*masseP*(-dx)+masse*dx)/(masseP+masse);  // CR: coef de restitution d�pend des mati�res = 0,5 ici
				dy=((0.5)*masseP*(-dy)+masse*dy)/(masseP+masse);
                            
                            
				y=SCREENY-d-10;
                                System.out.println("collision balle "+n+"et mur du bas");
			}

			if (x+d+10>SCREENX) {
				/*dx *= -0.8;
                                dy*=0.95;*/
                        
				
				dx= ((0.5)*masseP*(-dx)+masse*dx)/(masseP+masse);
				dy=-((0.5)*masseP*(-dy)+masse*dy)/(masseP+masse);
                                
				x=SCREENX-d-10;
                                System.out.println("collision balle "+n+"et mur de droite");

			}
// les deux if au dessus interdise a la balle de sortir de l'ï¿½cran, on peut jouer avec les valeurs pour resserer un peu plus les limites pour la balle, afin que pas seulement le centre soit concernï¿½ par la limite mais envelloper un rectangle autourd de la balle
			if (x<10) {
				/*dx *= -0.8;
                                dy *=0.95;*/
                            
				dx= ((0.5)*masseP*(-dx)+masse*dx)/(masseP+masse);
				dy=-((0.5)*masseP*(-dy)+masse*dy)/(masseP+masse);
                            
				x=10;
                                System.out.println("collision balle "+n+"et mur de gauche");

			}
// on suppose ici que la balle ne peut pas repartir plus haut que la d'ou elle vient, donc y'a meme pas besoin de lui interdir de sortir par le haut
                        
                        
                        
                        for (int i=0; i<MAXMOVOBJECTS; ++i) {
                            if (tab[i]!=null && i!=n ) {
                            
                                
                                if ( Math.sqrt(((x-tab[i].x)*(x-tab[i].x)+(y-tab[i].y)*(y-tab[i].y))) <= (d+tab[i].d)/2 && tab[i].t=='b' ) {
                                    /*dx*=-0.8;
                                    dy*=-0.8;
                                    tab[i].dx*=0.8;
                                    tab[i].dy*=0.8;*/
                                    
                                   

                                    dx= ((0.5)*masse*(tab[i].dx-dx)+masse*(tab[i].dx+dx))/(2*masse);
                                    dy=((0.5)*masse*(tab[i].dy-dy)+masse*(tab[i].dy+dy))/(2*masse);
                                    tab[i].dx=((0.5)*masse*(-tab[i].dx+dx)+masse*(tab[i].dx+dx))/(2*masse);
                                    tab[i].dy=((0.5)*masse*(-tab[i].dy+dy)+masse*(tab[i].dy+dy))/(2*masse);
                                    
                                    /* Faut ins�rer ici une condition pour que les balles rentrent en collision qu'une fois (pour qu'elles restent pas coll�es) */
                                    
                                    System.out.println ("collision de la balle "+n + " et " +i);
                                 
                                    
                                }
                                if (x+d>=(tab[i].x) && x<=(tab[i].x+tab[i].w) && (y+(d))>=(tab[i].y) && y<=(tab[i].y+tab[i].h) && tab[i].t=='p' )   {
                                    
                                   
                                    System.out.print ("collision de la balle "+n + " et de la platforme " +i);
                                    
                                    if(dpx<=tab[i].x) {
                                        x=tab[i].x-d;
                                       /* dx*=-0.8;
                                        dy*=0.95;*/
                                        
                                        dx= ((0.5)*masseP*(-dx)+masse*dx)/(masseP+masse);
                                        dy=((0.5)*masseP*(-dy)+masse*dy)/(masseP+masse);
                                        
                                        
                                        
                                        System.out.println("a gauche ");
                                    }
                                    if((dpx+(d/2))>=(tab[i].x+tab[i].w)) {
                                        x=tab[i].x+tab[i].w;
                                        /*dx*=-0.8;
                                        dy*=0.95;*/
                                        
                                        dx= ((0.5)*masseP*(-dx)+masse*dx)/(masseP+masse);
                                        dy=((0.5)*masseP*(-dy)+masse*dy)/(masseP+masse);
                                        
                                        
                                        System.out.println("a droite ");

                                    }
                                    if(dpy<=tab[i].y) {
                                        y=tab[i].y-d;
                                        /*dy*=-0.8;
                                        dx*=0.95;*/
                                        
                                        dx= -((0.5)*masseP*(-dx)+masse*dx)/(masseP+masse);
                                        dy=((0.5)*masseP*(-dy)+masse*dy)/(masseP+masse);
                                        
                                        
                                        System.out.println("en haut ");

                                    }
                                    if ((dpy+(d/2))>=(tab[i].y+tab[i].h)){
                                        y=tab[i].y+tab[i].h;
                                        /*dy*=-0.8;
                                        dx*=0.95;*/
                                        
                                        dx= -((0.5)*masseP*(-dx)+masse*dx)/(masseP+masse);
                                        dy=((0.5)*masseP*(-dy)+masse*dy)/(masseP+masse);
                                        
                                        
                                        System.out.println("en bas ");

                                    }
                                    
                                }
                                
                             
                                    
                                
                        }
                        
                        
                        }
                        
                        
		}

		public void paint (Graphics buffer) {
			buffer.setColor(Color.black);
			buffer.fillOval(x,y,d,d);	// position du centre et taille de la balle, ronde si les deux valeur sont identique

		}
	}
