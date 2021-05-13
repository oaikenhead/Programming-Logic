import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ColorBox extends JComponent
{
	Color myColor;
	
	public void paint (Graphics g)
	{
		Dimension d = getSize ();
		
		g.setColor(myColor);
		g.fillRect(1, 1, d.width - 2, d.height - 2);
		
	}
	
	public void SetColor (int r, int g, int b)
	{
		myColor = new Color(r, g, b);
		
		repaint ();
		
	}

}
