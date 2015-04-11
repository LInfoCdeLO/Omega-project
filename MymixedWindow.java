package omegaproject;


import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BasicStroke;


public class MymixedWindow extends JFrame implements MouseListener {
    private JButton jplay;
    private JButton bplateforme;
    private JButton bballe;
    private JButton bressort;
    private JButton blibre;
    private JButton blie;
    private JButton bfixe;
    private Image iplay=null;
    private Image iplatforme=null;
    private Image iballe=null;
    private Image iressort=null;
    private Image ilibre=null;
    private Image ilie=null;
    private Image ifixe=null;
    private JLabel lplatforme;
    private JLabel lballe;
    private JLabel lressort;
    private JLabel llibre;
    private JLabel llie;
    private JLabel lfixe;
    private JLabel lrayon;
    private JLabel lraideur;
    private JSlider srayon;
    private JSlider sraideur;
    private int vrayon;
    private int vraideur;
    private char selec;
    private int clicx;
    private int clicy;
    private boolean attenteclic=false;
    public balle [] tabballe = new balle [100];
    public int idballe=0;
    private ressort [] tabressort = new ressort [100];
    private int idressort=0;
    private platform [] tabplatform=new platform[100];
    private int idplatform=0;
    private int x1=0;
    private int y1=0;
    private int x2=0;
    private int y2=0;
    private int x1p=0,x2p=0,y1p=0,y2p=0;
    private final int LARGUEUR=590;
    private final int LONGUEUR=660;
    private float epaisseurRessort=7;

    
    
   

    public class MyPanel extends JPanel {
        public boolean haveshot;
        
        public MyPanel () {
            super();
            haveshot=false;
        }
        
        public void paintComponent (Graphics g) {
            
       
            //Si on est en mode play
            if (haveshot) {
                g.setColor(Color.red);
                g.fillRect(0, 0, LARGUEUR, LONGUEUR);
                g.setColor(Color.white);
                g.drawString("play", 100, 150);
            }
            //si on est en mode pause
            if(!haveshot) {
                g.setColor(Color.blue);
                g.fillRect(0, 0,LARGUEUR, LONGUEUR);
                g.setColor(Color.white);
                g.drawString("stop", 100, 150);
            }
            
            if (selec=='p') {
                g.drawString("plateforme selectionnée", 10,660);
                
            }
            else             
            if (selec=='b') {
                g.drawString("balle selectionnée", 10,660);
                
            }
            else             
            if (selec=='r') {
                g.drawString("ressort selectionné", 10,660);
                
            }
            else             
            if (selec=='e') {
                g.drawString("fixation libre selectionnée", 10,660);
                
            }
            else             
            if (selec=='l') {
                g.drawString("fixation liée selectionnée", 10,660);
                
            }
            else             
            if (selec=='f') {
                g.drawString("fixation fixe selectionnée", 10,660);
                
            }
            
            // il y a 2 bug, en 1 les balles ne s'affichent pas, problème avec le jpanel et en 2 des qu'on modifie la taille de la fenêtre, le bug apparait
           if (tabballe[0]!=null){
               
                for( int entier=0;entier<idballe;entier++){
                         g.setColor(Color.white);                
                         g.fillOval(tabballe[entier].positionx-tabballe[entier].rayon,tabballe[entier].positiony-tabballe[entier].rayon,2*tabballe[entier].rayon,2*tabballe[entier].rayon);
               }
           }
           if (tabplatform[0]!=null){
                   for( int i=0;i<idplatform;i++){
                       
                           g.setColor(Color.green);   
                                
                           g.fillPolygon(tabplatform[i].coordx,tabplatform[i].coordy,4);
                       
                         Graphics2D g2 = (Graphics2D) g;
                         BasicStroke line = new BasicStroke(15.0f);
                         g2.setStroke(line);
                         g2.drawLine(58, 153, getWidth(), getHeight() );
                       }
                   }
               if (tabressort[0]!=null){
                       for( int i=0;i<idressort;i++){
                           
                               g.setColor(Color.orange);   
                                    
                               Graphics2D g2 = (Graphics2D) g;
                           //Il faut changer le 7 en avec le epaisseurRessort
                               BasicStroke line = new BasicStroke(epaisseurRessort);
                               g2.setStroke(line);
                               g2.drawLine(tabressort[i].positionx1, tabressort[i].positiony1, tabressort[i].positionx2, tabressort[i].positiony2 );
                           }
                       }
           }
           
 
            
            //System.out.println("valeur du rayon "+vrayon);
            //System.out.println("valeur de la raideur "+vraideur);
            
            
        }
    
    
    
   
    
    
    public MyPanel p;

    public  MymixedWindow() {
        setSize(800,720);           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setLayout(null);
        chargement();
        this.setIconImage(new ImageIcon("image\\omega.png").getImage());
        addMouseListener(this);
       
        
        //creation des boutons
        
        jplay = new JButton(new ImageIcon(iplay));
        bplateforme = new JButton(new ImageIcon(iplatforme));
        bballe = new JButton(new ImageIcon(iballe));
        bressort = new JButton(new ImageIcon(iressort));
        blibre = new JButton(new ImageIcon(ilibre));
        blie = new JButton(new ImageIcon(ilie));
        bfixe = new JButton(new ImageIcon(ifixe));
        lplatforme = new JLabel ("Plateforme");
        lballe = new JLabel ("Balle");
        lressort = new JLabel ("Ressort");
        llibre = new JLabel (": Libre");
        llie = new JLabel (": Lié");
        lfixe = new JLabel (": Fixe");
        lrayon = new JLabel ("Rayon");
        lraideur = new JLabel("Raideur");
        
        jplay.setBorder(BorderFactory.createEmptyBorder());
        jplay.setContentAreaFilled(false); 
        bplateforme.setBorder(BorderFactory.createEmptyBorder());
        bplateforme.setContentAreaFilled(false); 
        bballe.setBorder(BorderFactory.createEmptyBorder());
        bballe.setContentAreaFilled(false); 
        bressort.setBorder(BorderFactory.createEmptyBorder());
        bressort.setContentAreaFilled(false); 
        blibre.setBorder(BorderFactory.createEmptyBorder());
        blibre.setContentAreaFilled(false); 
        blie.setBorder(BorderFactory.createEmptyBorder());
        blie.setContentAreaFilled(false); 
        bfixe.setBorder(BorderFactory.createEmptyBorder());
        bfixe.setContentAreaFilled(false); 
        
        bplateforme.setBounds(new Rectangle(600, 20, 150, 30));
        lplatforme.setBounds(640, 50, 150, 30);
        bballe.setBounds(new Rectangle(600, 90, 50, 50));
        lballe.setBounds(new Rectangle(610,140,150,30));
        lrayon.setBounds(new Rectangle(690,140,150,30));
        bressort.setBounds(new Rectangle(590, 170, 75, 56));
        lressort.setBounds(new Rectangle(605,220,150,30));
        lraideur.setBounds(new Rectangle(690,220,150,30));
        blibre.setBounds(new Rectangle(600, 250, 40, 48));
        llibre.setBounds(new Rectangle(650,260,150,30));
        blie.setBounds(new Rectangle(590, 300, 55, 49));
        llie.setBounds(new Rectangle(650,310,150,30));
        bfixe.setBounds(new Rectangle(595, 350, 43, 60));
        lfixe.setBounds(new Rectangle(640,360,150,30));
        jplay.setBounds(new Rectangle(600, 420, 52, 52));
        
        
        
        jplay.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jplay_actionPerformed(e);
                }
            });
        bplateforme.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bplatforme_actionPerformed(e);
                    
                }
            });
        bballe.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bballe_actionPerformed(e);
                }
            });
        bressort.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bressort_actionPerformed(e);
                }
            });
        blibre.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    blibre_actionPerformed(e);
                }
            });
        blie.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    blie_actionPerformed(e);
                }
            });
        bfixe.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bfixe_actionPerformed(e);
                }
            });
        
        //création du slider du rayon      
        srayon = new JSlider(); 
        srayon.setMaximum(100);
        srayon.setMinimum(0);
        srayon.setValue(5);
        srayon.setPaintTicks(true);
        srayon.setPaintLabels(true);
        //srayon.setMinorTickSpacing(10);
        srayon.setMajorTickSpacing(25);
        srayon.setBounds(new Rectangle(660,100,100,50));
        this.add(srayon);
        srayon.addChangeListener(new ChangeListener(){
           public void stateChanged(ChangeEvent event){
               vrayon=(int)srayon.getValue();
           }   
         });
        
        
        //creation du lider de la raideur
        
        sraideur = new JSlider(); 
        sraideur.setMaximum(100);
        sraideur.setMinimum(0);
        sraideur.setValue(5);
        sraideur.setPaintTicks(true);
        sraideur.setPaintLabels(true);
        //sraideur.setMinorTickSpacing(10);
        sraideur.setMajorTickSpacing(25);
        sraideur.setBounds(new Rectangle(660,180,100,50));
        this.add(sraideur);
        sraideur.addChangeListener(new ChangeListener(){
           public void stateChanged(ChangeEvent event){
               vraideur=(int)sraideur.getValue();
           }   
         });
    
        // Ajout de tous les widgets
        this.add(jplay);
        this.add(bplateforme);
        this.add(bballe);
        this.add(bressort);
        this.add(blibre);
        this.add(blie);
        this.add(bfixe);
        this.add(lplatforme);
        this.add(lballe);
        this.add(lrayon);
        this.add(lressort);
        this.add(lraideur);
        this.add(llibre);
        this.add(llie);
        this.add(lfixe);
        
        //creation finale du jpanel
        p =  new MyPanel();
        p.setBounds(new Rectangle(0, 0, LARGUEUR, LONGUEUR));
        this.add(p);
        repaint();
    }

    // When the button is pressed, set a variable of the  panel
    // to true, and repaint it.
    private void jplay_actionPerformed(ActionEvent e) {
        if(p.haveshot) {
        p.haveshot = false;
        }
        else {
            p.haveshot=true;
        }
        attenteclic=true;
        selec='s';
        p.repaint();
    }
    private void bplatforme_actionPerformed(ActionEvent e) {
        selec='p';
       //System.out.println("bouton plateforme pressé");
        attenteclic=true;
       repaint();
    }
    private void bballe_actionPerformed(ActionEvent e) {
       selec = 'b';
       //System.out.println("bouton balle pressé");
       attenteclic=true;
       
       repaint();

    }
    private void bressort_actionPerformed(ActionEvent e) {
       selec='r';
       //System.out.println("bouton ressort pressé");
       attenteclic=true;

        repaint();

    }
    private void blibre_actionPerformed(ActionEvent e) {
       selec='e';
       //System.out.println("bouton libre pressé");
        repaint();

    }
    private void blie_actionPerformed(ActionEvent e) {
       selec='l';
       //System.out.println("bouton lié pressé");
        repaint();

    }
    private void bfixe_actionPerformed(ActionEvent e) {
       selec='f';
       //System.out.println("bouton fixe pressé");
        repaint();

    }
  
    public void chargement () {
        try {
            iplay = ImageIO.read(new File("image\\play.png"));        }
            catch (IOException e){
                System.out.println ("Could not load image file.");
        }
        try {
            iplatforme = ImageIO.read(new File("image\\platforme.png"));        }
            catch (IOException e){
                System.out.println ("Could not load image file.");
        }
        try {
            iballe = ImageIO.read(new File("image\\balle.png"));        }
            catch (IOException e){
                System.out.println ("Could not load image file.");
        }
        try {
            iressort = ImageIO.read(new File("image\\ressort.png"));        }
            catch (IOException e){
                System.out.println ("Could not load image file.");
        }
        try {
            ilibre = ImageIO.read(new File("image\\libre.png"));        }
            catch (IOException e){
                System.out.println ("Could not load image file.");
        }
        try {
            ilie = ImageIO.read(new File("image\\lie.png"));        }
            catch (IOException e){
                System.out.println ("Could not load image file.");
        }
        try {
            ifixe = ImageIO.read(new File("image\\fixe.png"));        }
            catch (IOException e){
                System.out.println ("Could not load image file.");
        }
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouse=e.getButton();
        System.out.println("test");
        clicx=e.getX();
        clicy=e.getY();
        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='b' && clicx<=LARGUEUR && clicy<=LONGUEUR ){ //clic gauche
            
            tabballe[idballe] = new balle(clicx,clicy,vrayon);
            System.out.println("la balle "+idballe+ " de rayon "+vrayon+" et de masse "+tabballe[idballe].masse+" a été créé à la position x=" + clicx+" et y=" + clicy);
            idballe++;
            attenteclic=false;
            x1=y1=x2=y2=0; // sécurité pour gerer le cas d'un vilain qui change d'avis entre les deux clic pour platforme/ressort
            repaint();

        }
        
        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='r' && clicx<=LARGUEUR && clicy<=LONGUEUR) {
            
            if (x1==0 && y1==0 && x2==0 && y2==0 ) {
                x1=clicx;
                y1=clicy;
        }
            else if (x1!=x2 || y1!=y2)  { 
                x2=clicx;
                y2=clicy;
                tabressort[idressort]= new ressort (x1,y1,x2,y2,vraideur);
                System.out.println("le ressort "+ idressort+" de raideur "+vraideur+" a été créé avec pour premier point x= "+x1+" y="+y1+" et pour second point x="+x2+" y="+y2);
                idressort++;
                x1=y1=x2=y2=0;
                attenteclic=false;
                repaint();
            }
        }
        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='p' && clicx<=LARGUEUR && clicy<=LONGUEUR) {
            if (x1p==0 && y1p==0 && x2p==0 && y2p==0 ) {
                x1p=clicx;
                y1p=clicy;              
                
            }
            else if (x1p!=x2p || y1p!=y2p)  { 
                int[] premier={x1p,y1p};
                x2p=clicx;
                y2p=clicy;
                int[] deuxieme={x2p,y2p};
                tabplatform[idplatform]= new platform (premier,deuxieme);
              
                System.out.println("la plateforme "+ idplatform+" de longueur "+tabplatform[idplatform].longueur+" a été créé avec pour premier point x= "+x1p+" y="+y1p+" et pour second point x="+x2p+" y="+y2p);
                idplatform++;
                x1p=y1p=x2p=y2p=0;
                attenteclic=false;
                repaint();
            }
        }   
        // TODO Implement this method
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Implement this method
        
    }
    

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Implement this method
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Implement this method
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Implement this method
    }
    public static void main (String [] s) {
        
        MymixedWindow c = new MymixedWindow();
    }
 }

