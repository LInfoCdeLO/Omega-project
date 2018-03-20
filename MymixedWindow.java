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
import java.awt.event.MouseMotionListener;
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




public class MymixedWindow extends JFrame implements MouseListener, MouseMotionListener{
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
    private JLabel lplay;
    private JSlider srayon;
    private JSlider sraideur;
    private int vrayon;
    private int vraideur;
    private char selec;
    private int clicx;
    private int clicy;
    private boolean attenteclic=false,clic=false;
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
    public int x,y;
    public int largeur=20;
    boolean accroche =false;
    private int clignotement=0;
    private Color couleurclignotement;

    
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu1 = new JMenu("Paramètres");
    private JMenu menu1_2 = new JMenu("Milieu");
    private JMenu menu2 = new JMenu("Fichier");
    private JMenu menu4 = new JMenu("Apparence");
    private JMenu menu4_2 = new JMenu("Fond d'écran");
    private JMenu menu4_3 = new JMenu("Theme");
    private JMenu menu3 = new JMenu ("Aide");

    private JMenuItem item1 = new JMenuItem("Truc");
    private JMenuItem item2 = new JMenuItem("Fermer");
    private JMenuItem item3 = new JMenuItem("Play/Pause");
    private JMenuItem item5 = new JMenuItem("Truc2");
    private JMenuItem item8 = new JMenuItem("Tutoriel");

    private JCheckBoxMenuItem jcmi1 = new JCheckBoxMenuItem("Gravité");
    private JCheckBoxMenuItem jcmi2 = new JCheckBoxMenuItem("Vent");

    private JRadioButtonMenuItem jrmi0 = new JRadioButtonMenuItem("Vide");
    private JRadioButtonMenuItem jrmi1 = new JRadioButtonMenuItem("Air");
    private JRadioButtonMenuItem jrmi2 = new JRadioButtonMenuItem("Eau");
    
    private JRadioButtonMenuItem jrmi3 = new JRadioButtonMenuItem("Espace");
    private JRadioButtonMenuItem jrmi4 = new JRadioButtonMenuItem("Paquerettes");
    private JRadioButtonMenuItem jrmi5 = new JRadioButtonMenuItem("Mer");
    
    
   

    public class MyPanel extends JPanel {
        public boolean haveshot;
        
        public MyPanel () {
            super();

            haveshot=false;
        }
        
        public void paintComponent (Graphics g) {
               //Anti-aliasing
         
                System.out.println("Clignotement= "+ clignotement);
                Graphics2D g2d = (Graphics2D) g;
                RenderingHints rhints = g2d.getRenderingHints();
                boolean antialiasOn = rhints.containsValue(RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                //transparence, si on a envie de mettre de la transparence, vers varier le nombre devant le f entre 0 et 1
                /*
                AlphaComposite alcom = AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1f);
                g2d.setComposite(alcom);*/
       
            //Si on est en mode play
            if (haveshot) {
                g.setColor(Color.red);
                g.fillRect(0, 0, LARGUEUR, LONGUEUR);
                g.setColor(Color.white);
                g.drawString("Play", LARGUEUR-40, LONGUEUR-10);
            }
            //si on est en mode pause
            if(!haveshot) {
                g.setColor(Color.blue);
                g.fillRect(0, 0,LARGUEUR, LONGUEUR);
                g.setColor(Color.white);
                g.drawString("Pause", LARGUEUR-40, LONGUEUR-10);
            }
            
            if (selec=='p') {
                g.drawString("Plateforme selectionnée", 10,660);
                
            }
            else             
            if (selec=='b') {
                g.drawString("Balle selectionnée", 10,660);
                
            }
            else             
            if (selec=='r') {
                g.drawString("Ressort selectionné", 10,660);
                
            }
            else             
            if (selec=='e') {
                g.drawString("Fixation libre selectionnée", 10,660);
                
            }
            else             
            if (selec=='l') {
                g.drawString("Fixation liée selectionnée", 10,660);
                
            }
            else             
            if (selec=='f') {
                g.drawString("Fixation fixe selectionnée", 10,660);
                
            }
            
            //Affichage de la balle avant qu'elle soit crée
            if(selec=='b'&& attenteclic==true){                                         
                g2d.setColor(Color.black);          
                g2d.fillOval(x-8-vrayon,y-50-vrayon,2*vrayon,2*vrayon);
            } 
            
            //Affichage du ressort pendant sa création, les 4 dernieres conditions corrigent le bug du clic droit
               
            if(selec=='r'&& attenteclic==true&& clic==true &&clicx!=0&& clicy!=0  && x!=0&& y!=0){
                  
                   g.setColor(Color.black);                 
                   BasicStroke line = new BasicStroke(epaisseurRessort);
                   g2d.setStroke(line);
                   g2d.drawLine(clicx-8,clicy-51,x-8,y-50 );
            }
            // Affichage de la plateforme pendant sa création, les 4 dernieres conditions corrigent le bug du clic droit
            
            if(selec=='p'&& attenteclic==true&& clic==true && x!=0&& y!=0&&clicx!=0&& clicy!=0){
                      
                       if(clicy<y){
                           g.setColor(Color.black);
                        }
                       if(clicy>y){
                           //couleur clignotement à changer quand on aura le timer
                           if(clignotement>5000){ couleurclignotement=Color.green;}
                        g.setColor(couleurclignotement);
                       }
                       //creation de la plateforme intermediaire
                            //creation du des coordonnées du point de base et du point intermediaire pour obtenir l'angle
                       int[] first=new int[2];
                       first[0]=clicx; first[1]=clicy;
                       int[] second=new int[2];
                       second[0]=x; second[1]=y;
                            //on calcule l'angle
                       double angle= angleBetweenTwoPoints(first, second);
                            //creation des tableaux de coordonnées des 4 points de la plateforme
                       int []platIntermediairex=new int[4]; 
                       platIntermediairex[0]=clicx-8;
                       platIntermediairex[1]=x-8;
                       platIntermediairex[2]=(int)(x-Math.sin(angle)*largeur)-8;
                       platIntermediairex[3]=(int)(clicx-Math.sin(angle)*largeur)-8;
                       
                       int []platIntermediairey=new int[4]; 
                       platIntermediairey[0]=clicy-50;
                       platIntermediairey[1]=y-50;
                       if( angle>-Math.PI/2&&angle<Math.PI/2){
                            platIntermediairey[2]=(int)(y+Math.cos(angle)*largeur)-50;
                            platIntermediairey[3]=(int)(clicy+Math.cos(angle)*largeur)-50;
                        }
                       if( angle<-Math.PI/2 ||angle>Math.PI/2){
                            platIntermediairey[2]=(int)(y+Math.cos(angle)*largeur)-50;
                            platIntermediairey[3]=(int)(clicy+Math.cos(angle)*largeur)-50;
                        }
                       //affichage de cette plateforme
                       g.fillPolygon(platIntermediairex,platIntermediairey,4);
            } 
               
            
            //Affichage balles
            
            
           if (tabballe[0]!=null){
               
                for( int entier=0;entier<idballe;entier++){
                         g.setColor(Color.lightGray);                
                         g.fillOval(tabballe[entier].positionx-tabballe[entier].rayon,tabballe[entier].positiony-tabballe[entier].rayon,2*tabballe[entier].rayon,2*tabballe[entier].rayon);
               }
           }
           //Affichage plateformes
           if (tabplatform[0]!=null){
                   for( int i=0;i<idplatform;i++){
                           g.setColor(Color.green);                           
                           g.fillPolygon(tabplatform[i].coordx,tabplatform[i].coordy,4);
                       // Affichage des points liées
                           if(tabplatform[i].accroche1=='l'){ 
                               g.setColor(Color.cyan);
                               g.fillOval(tabplatform[i].point1[0]-4,tabplatform[i].point1[1]-4,8,8);
                            }
                           if(tabplatform[i].accroche2=='l'){ 
                               g.setColor(Color.cyan);
                               g.fillOval(tabplatform[i].point2[0]-4,tabplatform[i].point2[1]-4,8,8);
                            }
                       //affichage des points libres
                       if(tabplatform[i].accroche1=='e'){ 
                           g.setColor(Color.white);
                           g.fillOval(tabplatform[i].point1[0]-4,tabplatform[i].point1[1]-4,8,8);
                        }
                       if(tabplatform[i].accroche2=='e'){ 
                           g.setColor(Color.white);
                           g.fillOval(tabplatform[i].point2[0]-4,tabplatform[i].point2[1]-4,8,8);
                        }
                       //affichage des points fixes
                       if(tabplatform[i].accroche1=='f'){ 
                           g.setColor(Color.black);
                           g.fillOval(tabplatform[i].point1[0]-4,tabplatform[i].point1[1]-4,8,8);
                        }
                       if(tabplatform[i].accroche2=='f'){ 
                           g.setColor(Color.black);
                           g.fillOval(tabplatform[i].point2[0]-4,tabplatform[i].point2[1]-4,8,8);
                        }
                   }
                       
                   }
           //Affichage ressorts
           if (tabressort[0]!=null){
                       for( int i=0;i<idressort;i++){                           
                               g.setColor(Color.orange);                                       
                               Graphics2D g2 = (Graphics2D) g;
                               BasicStroke line = new BasicStroke(epaisseurRessort);
                               g2.setStroke(line);
                               g2.drawLine(tabressort[i].positionx1, tabressort[i].positiony1, tabressort[i].positionx2, tabressort[i].positiony2 );
                            // affichage des points liés
                               if(tabressort[i].accroche1=='l'){ 
                                   g.setColor(Color.cyan);
                                   g.fillOval(tabressort[i].positionx1-4,tabressort[i].positiony1-4,8,8);
                               }
                               if(tabressort[i].accroche2=='l'){ 
                                   g.setColor(Color.cyan);
                                   g.fillOval(tabressort[i].positionx2-4,tabressort[i].positiony2-4,8,8);
                               }
                           //affichage des points libres
                               if(tabressort[i].accroche1=='e'){ 
                                    g.setColor(Color.white);
                                    g.fillOval(tabressort[i].positionx1-4,tabressort[i].positiony1-4,8,8);
                               }
                               if(tabressort[i].accroche2=='e'){ 
                                    g.setColor(Color.white);
                                    g.fillOval(tabressort[i].positionx2-4,tabressort[i].positiony2-4,8,8);
                                }
                                //affichage des point fixes
                               if(tabressort[i].accroche1=='f'){ 
                                    g.setColor(Color.black);
                                    g.fillOval(tabressort[i].positionx1-4,tabressort[i].positiony1-4,8,8);
                               }
                               if(tabressort[i].accroche2=='f'){ 
                                    g.setColor(Color.black);
                                    g.fillOval(tabressort[i].positionx2-4,tabressort[i].positiony2-4,8,8);
                                }
                                
                           }
                       }
 
           
           }
           
 
            
            //System.out.println("valeur du rayon "+vrayon);
            //System.out.println("valeur de la raideur "+vraideur);
            
            
        }
    
    
    
   
    
    
    public MyPanel p;

    public  MymixedWindow () {
        setSize(800,720);           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setLayout(null);
        chargement();
        this.setIconImage(new ImageIcon("image\\omega.png").getImage());
        addMouseListener(this);
        addMouseMotionListener(this);
       
        
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
        llie = new JLabel (": Liée");
        lfixe = new JLabel (": Fixe");
        lrayon = new JLabel ("Rayon");
        lraideur = new JLabel("Raideur");
        lplay=new JLabel("Play/Pause");
        
        //jplay.setBorder(BorderFactory.createEmptyBorder());
        jplay.setContentAreaFilled(false); 
       // bplateforme.setBorder(BorderFactory.createEmptyBorder());
        bplateforme.setContentAreaFilled(false); 
       // bballe.setBorder(BorderFactory.createEmptyBorder());
        bballe.setContentAreaFilled(false); 
       // bressort.setBorder(BorderFactory.createEmptyBorder());
        bressort.setContentAreaFilled(false); 
        //blibre.setBorder(BorderFactory.createEmptyBorder());
        blibre.setContentAreaFilled(false); 
        //blie.setBorder(BorderFactory.createEmptyBorder());
        blie.setContentAreaFilled(false); 
        //bfixe.setBorder(BorderFactory.createEmptyBorder());
        bfixe.setContentAreaFilled(false); 
        
        bplateforme.setBounds(new Rectangle(600, 20, 150, 30));
        lplatforme.setBounds(640, 50, 150, 30);
        bballe.setBounds(new Rectangle(600, 90, 50, 50));
        lballe.setBounds(new Rectangle(610,140,150,30));
        lrayon.setBounds(new Rectangle(690,140,150,30));
        bressort.setBounds(new Rectangle(605, 165, 43, 63));
        lressort.setBounds(new Rectangle(605,220,150,30));
        lraideur.setBounds(new Rectangle(690,220,150,30));
        blibre.setBounds(new Rectangle(600, 250, 55, 55));
        llibre.setBounds(new Rectangle(660,260,150,30));
        blie.setBounds(new Rectangle(600,320, 55, 55));
        llie.setBounds(new Rectangle(660,330,150,30));
        bfixe.setBounds(new Rectangle(600,390, 55, 55));
        lfixe.setBounds(new Rectangle(660,400,150,30));
        jplay.setBounds(new Rectangle(650, 550, 85, 85));
        lplay.setBounds(new Rectangle(660, 600, 85, 85));
        
        
        
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
               vrayon=(int)(srayon.getValue()/3);
           }   
         });
        
        
        //creation du slider de la raideur
        
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
        this.add(lplay);
        
        //creation finale du jpanel
        p =  new MyPanel();
        p.setBounds(new Rectangle(0, 0, LARGUEUR, LONGUEUR));
        this.add(p);
        repaint();
        
        
        

        //On ajoute les items aux menus      
        this.menu1.add(item1);
              
        // Ajout d'un séparateur
        this.menu1.addSeparator();
        
        this.menu1.add(item5);  
        this.menu1.addSeparator();
        
        

        //On ajoute les éléments dans notre sous-menu
        this.menu1_2.add(jcmi1);
        this.menu1_2.add(jcmi2);
        //Ajout d'un séparateur
        this.menu1_2.addSeparator();
        //On met nos radios dans un ButtonGroup
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrmi1);
        bg.add(jrmi2);
        bg.add(jrmi0);
        //On présélectionne la première radio
        jrmi1.setSelected(true);

        this.menu1_2.add(jrmi1);
        this.menu1_2.add(jrmi2);
        this.menu1_2.add(jrmi0);

        //Ajout du sous-menu dans notre menu
        this.menu1.add(this.menu1_2);
        
        item2.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
          }        
        });        
      
        
        this.menu2.add(item3);   
        this.menu2.addSeparator();
        item3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jplay_actionPerformed(e);
            }
        });

      
        this.menu2.add(item2);  
        
        
        
        
            //On met nos radios dans un ButtonGroup
            ButtonGroup bgp = new ButtonGroup();
            bgp.add(jrmi3);
            bgp.add(jrmi4);
            bgp.add(jrmi5);
            //On présélectionne la première radio
            jrmi3.setSelected(true);

            this.menu4_2.add(jrmi3);
            this.menu4_2.add(jrmi4);
            this.menu4_2.add(jrmi5);

            //Ajout du sous-menu dans notre menu
            this.menu4.add(this.menu4_2);       
        
        
        this.menu3.add(item8);
        
        item8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FenetreAide().setVisible(true);
                
                }
        });
               
        

        //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
        //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
        this.menuBar.add(menu2);
        this.menuBar.add(menu1);
        this.setJMenuBar(menuBar);
        this.menuBar.add(menu4);
        this.menuBar.add(menu3);    
        this.setVisible(true);
        }
    
    
    
    public class FenetreAide extends JFrame{

        public FenetreAide(){
        
            this.setTitle("Tutoriel");
            this.setSize(500,300);
            
        }
        
      
        
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
        accroche=true;
        repaint();

    }
    private void blie_actionPerformed(ActionEvent e) {
       selec='l';
       //System.out.println("bouton lié pressé");
       accroche=true;
        repaint();

    }
    private void bfixe_actionPerformed(ActionEvent e) {
       selec='f';
       //System.out.println("bouton fixe pressé");7
       accroche=true;
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

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mouse=e.getButton();
        System.out.println("test");
        
        clicx=e.getX();
        clicy=e.getY();

        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='b' && clicx<=LARGUEUR && clicy<=LONGUEUR ){ //clic gauche
            
            tabballe[idballe] = new balle(clicx,clicy,vrayon);
            System.out.println("la balle "+idballe+ " de rayon "+vrayon+" et de masse "+tabballe[idballe].masse+" a été créé à la position x=" + clicx+" et y=" + clicy);
            idballe++;
            
            x1=y1=x2=y2=0; // sécurité pour gerer le cas d'un vilain qui change d'avis entre les deux clic pour platforme/ressort
            repaint();

        }
        
        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='r' && clicx<=LARGUEUR && clicy<=LONGUEUR) {
            
            if (x1==0 && y1==0 && x2==0 && y2==0 ) {
                x1=clicx;
                y1=clicy;
                clic=true;
        }
            else if (x1!=x2 || y1!=y2)  { 
                x2=clicx;
                y2=clicy;
                tabressort[idressort]= new ressort (x1,y1,x2,y2,vraideur);
                System.out.println("le ressort "+ idressort+" de raideur "+vraideur+" a été créé avec pour premier point x= "+x1+" y="+y1+" et pour second point x="+x2+" y="+y2);
                idressort++;
                x1=y1=x2=y2=0;
                clic=false;
                repaint();
            }
        }
        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='p' && clicx<=LARGUEUR && clicy<=LONGUEUR) {
            if (x1p==0 && y1p==0 && x2p==0 && y2p==0 ) {
                x1p=clicx;
                y1p=clicy;
                clic=true;
                
            }
            else if (x1p!=x2p || y1p!=y2p )  { 
                int[] premier={x1p,y1p};
                x2p=clicx;
                y2p=clicy;
                if(y2p>y1p){
                    int[] deuxieme={x2p,y2p};
                    tabplatform[idplatform]= new platform (premier,deuxieme);
                    System.out.println("la plateforme "+ idplatform+" de longueur "+tabplatform[idplatform].longueur+" a été créé avec pour premier point x= "+x1p+" y="+y1p+" et pour second point x="+x2p+" y="+y2p);
                    idplatform++;
                }
                x1p=y1p=x2p=y2p=0;
                clic=false;
                
                repaint();
            }
        }   
        //si un des bouton de point d'accroche est presse
        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='l' && clicx<=LARGUEUR && clicy<=LONGUEUR) {
            accroche=true;
        }
        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='f' && clicx<=LARGUEUR && clicy<=LONGUEUR) {
            accroche=true;
        }
        if(mouse==MouseEvent.BUTTON1 && attenteclic==true && selec=='e' && clicx<=LARGUEUR && clicy<=LONGUEUR) {
            accroche=true;
        }
        if(accroche){
            int [] clic= new int[2];
            clic[0]=clicx-8;clic[1]=clicy-50;
            for(int j=0;j<idplatform;j++){
                // On verifie si la souris est dans le rectangle, cf methode
                boolean c=false;
                c=inTheRectangle(tabplatform[j].point1,tabplatform[j].point3,clic,tabplatform[j].angle);
                if(c ){
                    System.out.println(" la souris est dans le rectangle n°"+j);
                    double dist1=distance(clic,tabplatform[j].point1);
                    double dist2=distance(clic,tabplatform[j].point3);
                    if (dist1<dist2){
                        System.out.println("point d'accroche 1 plateforme choisi avec pour fixation:"+ selec);
                        tabplatform[j].accroche1=selec;
                        
                    }
                    if (dist1>dist2){
                        System.out.println("point d'accroche 2 plateforme choisi avec pour fixation:"+selec);
                        tabplatform[j].accroche2=selec;
                    }
                    }
                    
                }
            for(int i=0;i<idressort;i++){
                double dist1=distance(tabressort[i].point1,clic);
                double dist2=distance(tabressort[i].point2,clic);
                //si on clic autour de 10 pixel du point d'accroche, on affecte le point d'accroche
                if (dist1<10){
                        System.out.println("Point d'accroche 1 ressort n°"+i+"choisi");
                        tabressort[i].accroche1=selec;
                }
                if (dist2<10){
                    System.out.println("Point d'accroche 2 ressort n°"+i+"choisi");
                    tabressort[i].accroche2=selec;
                }
            }
        }
        //Si un point d'accroche du ressort est censé être lié et qu'il appartient a une partie d'une plateforme alors les coordonnées du ressort sont les mêmes que le point d'accroche 2 du ressort
        for(int i=0;i<idplatform;i++){
            //Pour le point d'accroche 1 des plateformes
            if(tabplatform[i].accroche1=='l'){

                for(int j=0;j<idressort;j++){
                    boolean c=false;
                    c=inTheRectangle(tabplatform[i].point1,tabplatform[i].point3,tabressort[j].point1,tabplatform[i].angle);
                    //on verifie que le point est bien dans la premiere moitié de la plateforme
                    double distance1=distance(tabplatform[i].point1,tabressort[j].point1);
                    double distance2=distance(tabplatform[i].point2,tabressort[j].point1);
                    //point d'accroche 1 plat et ressort
                    if(c && tabressort[j].accroche1=='l'&& distance1<distance2){               
                        tabressort[j].positionx1=tabplatform[i].point1[0];
                        tabressort[j].positiony1=tabplatform[i].point1[1];
                    }
                    boolean d=false;
                    d=inTheRectangle(tabplatform[i].point1,tabplatform[i].point3,tabressort[j].point2,tabplatform[i].angle);
                    double dist1=distance(tabplatform[i].point1,tabressort[j].point2);
                    double dist2=distance(tabplatform[i].point2,tabressort[j].point2);
                    //point d'accroche 1 plat et 2 ressort
                    if(d && tabressort[j].accroche2=='l'&& dist1<dist2){                       
                        tabressort[j].positionx2=tabplatform[i].point1[0];
                        tabressort[j].positiony2=tabplatform[i].point1[1];
                    }
                }
            }
            
               if(tabplatform[i].accroche2=='l'){

                    for(int j=0;j<idressort;j++){
                        boolean c=false;
                        c=inTheRectangle(tabplatform[i].point1,tabplatform[i].point3,tabressort[j].point1,tabplatform[i].angle);
                        double distance1=distance(tabplatform[i].point1,tabressort[j].point1);
                        double distance2=distance(tabplatform[i].point2,tabressort[j].point1);
                        //point d'accroche 2 plateforme et 1 ressort
                        if(c&& tabressort[j].accroche1=='l'&& distance2<distance1){               
                            tabressort[j].point1[0]=tabplatform[i].point2[0];
                            tabressort[j].point1[1]=tabplatform[i].point2[1];
                        }
                        boolean d=false;
                        d=inTheRectangle(tabplatform[i].point1,tabplatform[i].point3,tabressort[j].point2,tabplatform[i].angle);
                        double dist1=distance(tabplatform[i].point1,tabressort[j].point2);
                        double dist2=distance(tabplatform[i].point2,tabressort[j].point2);
                        // point d'accroche 2 plat et ressort
                        if(d && tabressort[j].accroche2=='l'&& dist2<dist1){                       
                            tabressort[j].positionx2=tabplatform[i].point2[0];
                            tabressort[j].positiony2=tabplatform[i].point2[1];
                        }
                    }
                }
            repaint();
        }
        //Si clic droit on annule les actions
        if(mouse==MouseEvent.BUTTON3 ) {
            System.out.println("clic droit");
            attenteclic=false;
            accroche=false; 
            x1p=y1p=x2p=y2p=x=y=clicx=clicy=x1=y1=x2=y2=0;
            repaint();

        }
        
        
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
    
     public void mouseMoved( MouseEvent e ) {
             x=e.getX();
             y=e.getY();
             repaint();
             }
     
    public void mouseDragged(MouseEvent e) {
            // TODO Implement this method
        }
    public double angleBetweenTwoPoints(int [] first,int [] last){
        
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
    public double distance(int[] first, int [] last){
        double dist= Math.sqrt((first[0]-last[0])*(first[0]-last[0])+(first[1]-last[1])*(first[1]-last[1]) )  ;
        return dist;
    }
    public boolean inTheRectangle(int [] point1,int [] point3,int [] clic, double angle){
        boolean c=false;
        double angle1= angleBetweenTwoPoints(point1,clic);
        double angle2= angleBetweenTwoPoints(point3,clic);
        System.out.println("l'angle 1 est"+ angle1+ "l'angle 2 est: "+angle2);
        
        if((angle1-angle)>0 && (angle1-angle)<Math.PI/2  && angle2+Math.PI/2-angle<0&& (angle2)>angle-Math.PI ){
            c=true;
            System.out.println("yihaaaaaaa");
        }
        return c;
    }
    
    
        

    public static void main (String [] s) {
        
        MymixedWindow c = new MymixedWindow();
    }

}