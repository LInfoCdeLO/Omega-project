import java.awt.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public abstract class MovObject extends JFrame {
		Graphics buffer;
		public int x,y,n,w,d,h;
                public char t;
                public double dx,dy,g;
                public boolean deplacementauto;
                public boolean deplacdrag=false;


		abstract public void simulate(MovObject[] tab);
		abstract public void paint(Graphics g);
	}
