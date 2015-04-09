
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MymixedWindow extends JFrame {
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
    
    public class MyPanel extends JPanel {
        public boolean haveshot;
        
        public MyPanel () {
            super();
            haveshot=false;
        }
        
        public void paintComponent (Graphics g) {
            if (haveshot) {
                g.setColor(Color.red);
                g.fillRect(0, 0, 400, 480);
                g.setColor(Color.white);
                g.drawString("play", 100, 150);
            }
            if(!haveshot) {
                g.setColor(Color.blue);
                g.fillRect(0, 0, 400, 480);
                g.setColor(Color.white);
                g.drawString("stop", 100, 150);
            }
            
            if (selec=='p') {
                g.drawString("plateforme selectionnée", 10,470);
                
            }
            else             
            if (selec=='b') {
                g.drawString("balle selectionnée", 10,470);
                
            }
            else             
            if (selec=='r') {
                g.drawString("ressort selectionné", 10,470);
                
            }
            else             
            if (selec=='e') {
                g.drawString("fixation libre selectionnée", 10,470);
                
            }
            else             
            if (selec=='l') {
                g.drawString("fixation liée selectionnée", 10,470);
                
            }
            else             
            if (selec=='f') {
                g.drawString("fixation fixe selectionnée", 10,470);
                
            }
            
            //System.out.println("valeur du rayon "+vrayon);
            //System.out.println("valeur de la raideur "+vraideur);
            
        }
    }
    
   
    
    
    public MyPanel p;

    public  MymixedWindow() {
        setSize(600,540);           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setLayout(null);
        chargement();
       
        
        
        
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
        
        bplateforme.setBounds(new Rectangle(410, 20, 150, 30));
        lplatforme.setBounds(450, 50, 150, 30);
        bballe.setBounds(new Rectangle(410, 90, 50, 50));
        lballe.setBounds(new Rectangle(420,140,150,30));
        lrayon.setBounds(new Rectangle(500,140,150,30));
        bressort.setBounds(new Rectangle(400, 170, 75, 56));
        lressort.setBounds(new Rectangle(415,220,150,30));
        lraideur.setBounds(new Rectangle(500,220,150,30));
        blibre.setBounds(new Rectangle(410, 250, 40, 48));
        llibre.setBounds(new Rectangle(460,260,150,30));
        blie.setBounds(new Rectangle(400, 300, 55, 49));
        llie.setBounds(new Rectangle(460,310,150,30));
        bfixe.setBounds(new Rectangle(410, 350, 43, 60));
        lfixe.setBounds(new Rectangle(460,360,150,30));
        jplay.setBounds(new Rectangle(410, 420, 52, 52));
        
        
        
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
        
        
        
        srayon = new JSlider(); 
        srayon.setMaximum(100);
        srayon.setMinimum(0);
        srayon.setValue(5);
        srayon.setPaintTicks(true);
        srayon.setPaintLabels(true);
        //srayon.setMinorTickSpacing(10);
        srayon.setMajorTickSpacing(25);
        srayon.setBounds(new Rectangle(470,100,100,50));
        this.add(srayon);
        srayon.addChangeListener(new ChangeListener(){
           public void stateChanged(ChangeEvent event){
               vrayon=(int)srayon.getValue();
           }   
         });
        
        

        
        sraideur = new JSlider(); 
        sraideur.setMaximum(100);
        sraideur.setMinimum(0);
        sraideur.setValue(5);
        sraideur.setPaintTicks(true);
        sraideur.setPaintLabels(true);
        //sraideur.setMinorTickSpacing(10);
        sraideur.setMajorTickSpacing(25);
        sraideur.setBounds(new Rectangle(470,180,100,50));
        this.add(sraideur);
        sraideur.addChangeListener(new ChangeListener(){
           public void stateChanged(ChangeEvent event){
               vraideur=(int)sraideur.getValue();
           }   
         });
    
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
        

        p =  new MyPanel();
        p.setBounds(new Rectangle(0, 0, 400, 480));
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
        selec='s';
        p.repaint();
    }
    private void bplatforme_actionPerformed(ActionEvent e) {
        selec='p';
       System.out.println("bouton plateforme pressé");
       repaint();
    }
    private void bballe_actionPerformed(ActionEvent e) {
       selec = 'b';
       System.out.println("bouton balle pressé");
        repaint();

    }
    private void bressort_actionPerformed(ActionEvent e) {
       selec='r';
       System.out.println("bouton ressort pressé");
        repaint();

    }
    private void blibre_actionPerformed(ActionEvent e) {
       selec='e';
       System.out.println("bouton libre pressé");
        repaint();

    }
    private void blie_actionPerformed(ActionEvent e) {
       selec='l';
       System.out.println("bouton lié pressé");
        repaint();

    }
    private void bfixe_actionPerformed(ActionEvent e) {
       selec='f';
       System.out.println("bouton fixe pressé");
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
    public static void main (String [] s) {
        
        MymixedWindow c = new MymixedWindow();
    }
}
