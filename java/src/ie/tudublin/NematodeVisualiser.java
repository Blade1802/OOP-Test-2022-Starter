package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{
	ArrayList<Nematode> nematodes = new ArrayList<Nematode>();
	
	public void keyPressed()
	{		
		if (keyCode == LEFT)
		{
		}		
	}


	public void settings()
	{
		size(800, 800);
	}

	public void setup() 
	{
		colorMode(HSB);
		background(0);
		smooth();
		loadNematodes();
					
	}
	

	public void loadNematodes()
	{
		Table table = loadTable("nematodes.csv", "header");
        for(TableRow r:table.rows())
        {
            Nematode n = new Nematode(r);
            nematodes.add(n);
        }
	}

	int circleRadius = 40;
	Nematode currentNematode = null;

	public void draw()
	{
		stroke(255);
		strokeWeight(2);
		noFill();	
		int halfW = width/2;
		int halfH = height/2;
		int offset = 0;
		currentNematode = nematodes.get(9);
		point(halfW, halfH);
		for(int i = 0; i < (currentNematode.getLength() + 1)/2; i++)
		{
			offset = circleRadius * i;

			if(currentNematode.getLength() % 2 == 1)
			{
				// Segments
				circle(halfW, halfH - offset, circleRadius);
				circle(halfW, halfH + offset, circleRadius);
				
				// Limbs
				if(currentNematode.isLimbs())
				{
					// Top segment limbs
					line(halfW + circleRadius/2, halfH - offset, halfW + circleRadius, halfH - offset);
					line(halfW - circleRadius/2, halfH - offset, halfW - circleRadius, halfH - offset);
					// Bottom segment limbs
					line(halfW + circleRadius/2, halfH + offset, halfW + circleRadius, halfH + offset);
					line(halfW - circleRadius/2, halfH + offset, halfW - circleRadius, halfH + offset);
				}
			}
			else
			{
				// Segments
				circle(halfW, halfH + circleRadius/2 - offset, circleRadius);
				circle(halfW, halfH - circleRadius/2 + offset, circleRadius);
				// Top limbs
				line(halfW + circleRadius/2, halfH + circleRadius/2 - offset, halfW + circleRadius, halfH + circleRadius/2 - offset);
				line(halfW - circleRadius/2, halfH + circleRadius/2 - offset, halfW - circleRadius, halfH + circleRadius/2 - offset);
				// Bottom limbs
				line(halfW + circleRadius/2, halfH + circleRadius/2 + offset, halfW + circleRadius, halfH + circleRadius/2 + offset);
				line(halfW - circleRadius/2, halfH + circleRadius/2 + offset, halfW - circleRadius, halfH + circleRadius/2 + offset);
			}
		}

		String gender = currentNematode.getGender();
		switch(gender)
		{
			case "m":
			{
				int y = halfH + (circleRadius/2) * currentNematode.getLength();
				line(halfW, y, halfW, y + circleRadius/2);
				y = y + 5 + circleRadius/2;
				circle(halfW, y, 10);
				break;
			}
			case "f":
			{
				int y = halfH + (circleRadius/2) * (currentNematode.getLength() - 1);
				circle(halfW, y, circleRadius/2);
				break;
			}
			case "h":
			{
				int y = halfH + (circleRadius/2) * currentNematode.getLength();
				line(halfW, y, halfW, y + circleRadius/2);
				y = y + 5 + circleRadius/2;
				circle(halfW, y, 10);
				y = halfH + (circleRadius/2) * (currentNematode.getLength() - 1);
				circle(halfW, y, circleRadius/2);
			}
		}
		
	}
}
