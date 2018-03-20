import java.awt.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class Platform extends MovObject{
		final int PLATFHEIGHT=10;
                
		public Platform (int x, int y, int w, int n) {
			this.x = x;
			this.y = y;
			this.w = w;
                        this.n=n;
                        this.t='p';
                        this.h=PLATFHEIGHT;
                        
		}
                
                public Platform (int x, int y, int w, int h, int n) {
                    this.x = x;
                    this.y = y;
                    this.w = w;
                    this.n=n;
                    this.t='p';
                    this.h=h;
                }

		public void simulate (MovObject[] tab) {

		}

		public void paint (Graphics buffer) {
			buffer.setColor(Color.red);
			buffer.fillRect(x,y,w,h); // dans l'ordre, position x, position y, longueur, hauteur
		}
	}
	
