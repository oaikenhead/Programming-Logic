import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*; 

import java.io.*; 

public class HW8 extends JFrame
{
	protected JButton buttonSave;
	protected JButton buttonReset;
	
	protected JLabel redLabel;
	protected JLabel greenLabel;
	protected JLabel blueLabel;
	
	protected JTextField redField;
	protected JTextField greenField;
	protected JTextField blueField;
	
	protected JButton redPlus;
	protected JButton redMinus;
	protected JButton greenPlus;
	protected JButton greenMinus;
	protected JButton bluePlus;
	protected JButton blueMinus;
	
	protected ColorBox colorBox;
	
	protected JList colorList;
	
	protected ColorObject colors[];
	protected ColorObject selectedColor;
			 
	public static void main (String[] args) throws IOException
	{

		
		
		new HW8 ("Color Sampler");
		
	}
	
	public HW8 (String title) throws IOException
	{
		super (title);
		setBounds (100, 100, 300, 300);
		addWindowListener (new WindowDestroyer ());
		
		//myObject.ReadInColors ();
		FileInputStream stream = new FileInputStream ("Colors.txt");
		InputStreamReader reader = new InputStreamReader (stream);
		StreamTokenizer tokens = new StreamTokenizer (reader); 

		String readName;
		int readR;
		int readG;
		int readB;

		//ColorObject colors[] = new ColorObject[11];
		colors = new ColorObject[11];
		
		int index = 0;

		while (tokens.nextToken() != tokens.TT_EOF)
		{
			readName = (String) tokens.sval;
			tokens.nextToken();

			readR = (int) tokens.nval;
			tokens.nextToken();

			readG = (int) tokens.nval;
			tokens.nextToken();

			readB = (int) tokens.nval;

			colors[index] = new ColorObject (readName, readR, readG, readB);

			index++;

		}

		stream.close();


		for (int i = 0; i < 11; i++)
		{
			System.out.println (colors[i]);

		}
		
		colorList = new JList ();
		colorList.addListSelectionListener(new ListHandler ());
		
		colorBox = new ColorBox ();
		//getContentPane().setLayout (new GridLayout (1, 2));
		getContentPane().setLayout (new GridBagLayout ());
		
		GridBagConstraints c;
		
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.1;
		c.weighty = 0.2;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		
		getContentPane().add (colorBox, c);
		
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.BOTH;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridheight = 7;
		c.gridx = 4;
		c.gridy = 0;
		
		getContentPane().add (colorList, c);
		
		redLabel = new JLabel ("Red: ");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		
		getContentPane().add (redLabel, c);
		
		redField = new JTextField ();
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		
		getContentPane().add (redField, c);
		
		redPlus = new JButton ("+");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		
		getContentPane().add (redPlus, c);
		
		redMinus = new JButton ("-");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		
		getContentPane().add (redMinus, c);
		
		greenLabel = new JLabel ("Green: ");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		
		getContentPane().add (greenLabel, c);
		
		greenField = new JTextField ();
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		
		getContentPane().add (greenField, c);
		
		greenPlus = new JButton ("+");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 2;
		
		getContentPane().add (greenPlus, c);
		
		greenMinus = new JButton ("-");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 2;
		
		getContentPane().add (greenMinus, c);
		
		blueLabel = new JLabel ("Blue: ");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		
		getContentPane().add (blueLabel, c);
		
		blueField = new JTextField ();
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.3;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 3;
		
		getContentPane().add (blueField, c);
		
		bluePlus = new JButton ("+");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 3;
		
		getContentPane().add (bluePlus, c);
		
		buttonSave = new JButton ("Save");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.NONE;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 4;
		
		getContentPane().add (buttonSave, c);
		
		buttonReset = new JButton ("Reset");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.NONE;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 4;
		
		getContentPane().add (buttonReset, c);
		
		setVisible (true);
		
		String colorNames[] = new String[11];
		
		for (int i = 0; i < 11; i++)
		{
			colorNames[i] = colors[i].GetName();
			
		}
		
		blueMinus = new JButton ("-");
		c = new GridBagConstraints ();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx = 30;      //make this component tall
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 3;
		
		getContentPane().add (blueMinus, c);
		
		//Add Button Listeners
		buttonSave.addActionListener(new ActionHandler ());
		buttonReset.addActionListener(new ActionHandler ());
		redPlus.addActionListener(new ActionHandler ());
		redMinus.addActionListener(new ActionHandler ());
		greenPlus.addActionListener(new ActionHandler ());
		greenMinus.addActionListener(new ActionHandler ());
		bluePlus.addActionListener(new ActionHandler ());
		blueMinus.addActionListener(new ActionHandler ());
		
		//Add Field Listeners
		redField.addActionListener(new ActionHandler ());
		greenField.addActionListener(new ActionHandler ());
		blueField.addActionListener(new ActionHandler ());
		
		colorList.setListData(colorNames);
		
		//Set default values
		colorList.setSelectedIndex (0);
		selectedColor = new ColorObject (colors[0].GetName(), colors[0].GetColorValue(0), colors[0].GetColorValue(1), colors[0].GetColorValue(2));
		colorBox.SetColor (selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
		redField.setText (String.valueOf(selectedColor.GetColorValue(0)));
		greenField.setText (String.valueOf(selectedColor.GetColorValue(1)));
		blueField.setText (String.valueOf(selectedColor.GetColorValue(2)));
		
				 
	}
	
	private class ActionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == redPlus && selectedColor.GetColorValue(0) < 255)
			{	
				selectedColor.SetColorValue (0, selectedColor.GetColorValue(0) + 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				redField.setText (String.valueOf(selectedColor.GetColorValue(0)));
				
				setTitle ("Color Sampler*");
				
			} else if (e.getSource() == redMinus && selectedColor.GetColorValue(0) > 0)
			{
				selectedColor.SetColorValue (0, selectedColor.GetColorValue(0) - 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				redField.setText (String.valueOf(selectedColor.GetColorValue(0)));
				
				setTitle ("Color Sampler*");
				
			} else if (e.getSource() == greenPlus && selectedColor.GetColorValue(1) < 255)
			{	
				selectedColor.SetColorValue (1, selectedColor.GetColorValue(1) + 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				greenField.setText (String.valueOf(selectedColor.GetColorValue(1)));
				
				setTitle ("Color Sampler*");
				
			} else if (e.getSource() == greenMinus && selectedColor.GetColorValue(1) > 0)
			{
				selectedColor.SetColorValue (1, selectedColor.GetColorValue(1) - 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				greenField.setText (String.valueOf(selectedColor.GetColorValue(1)));
				
				setTitle ("Color Sampler*");
				
			} else if (e.getSource() == bluePlus && selectedColor.GetColorValue(2) < 255)
			{	
				selectedColor.SetColorValue (2, selectedColor.GetColorValue(2) + 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				blueField.setText (String.valueOf(selectedColor.GetColorValue(2)));
				
				setTitle ("Color Sampler*");
				
			} else if (e.getSource() == blueMinus && selectedColor.GetColorValue(2) > 0)
			{
				selectedColor.SetColorValue (2, selectedColor.GetColorValue(2) - 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				blueField.setText (String.valueOf(selectedColor.GetColorValue(2)));
				
				setTitle ("Color Sampler*");
				
			} else if (e.getSource() == buttonSave)
			{
				//Save the color
				int i = colorList.getSelectedIndex();
				
				colors[i] = new ColorObject(selectedColor.GetName(), selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				
				setTitle ("Color Sampler");
				
			} else if (e.getSource() == buttonReset)
			{
				//Reset the color
				int i = colorList.getSelectedIndex();
				
				selectedColor = new ColorObject(colors[i].GetName(), colors[i].GetColorValue(0), colors[i].GetColorValue(1), colors[i].GetColorValue(2));
				
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				
				redField.setText (String.valueOf(selectedColor.GetColorValue(0)));
				greenField.setText (String.valueOf(selectedColor.GetColorValue(1)));
				blueField.setText (String.valueOf(selectedColor.GetColorValue(2)));
				
				setTitle ("Color Sampler");
				
			} else if (e.getSource() == redField)
			{
				int newR = Integer.parseInt(redField.getText ());
				
				selectedColor.SetColorValue (0, newR);
				
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				
			} else if (e.getSource() == greenField)
			{
				int newG = Integer.parseInt(greenField.getText ());
				
				selectedColor.SetColorValue (1, newG);
				
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				
			} else if (e.getSource() == blueField)
			{
				int newB = Integer.parseInt(blueField.getText ());
				
				selectedColor.SetColorValue (2, newB);
				
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				
			}
			
		}
	}
	
	public void OutputToFile () throws IOException
	{
		FileOutputStream ostream = new FileOutputStream("Colors.txt");
		PrintWriter writer = new PrintWriter(ostream); 
		
		for (int i = 0; i < 11; i++)
		{
			writer.println (colors[i].GetName() + " " + colors[i].GetColorValue(0) + " " + colors[i].GetColorValue(1) + " " + colors[i].GetColorValue(2));
			
		}
		
		writer.flush ();
		ostream.close ();
		
	}
	
	private class WindowDestroyer extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			//Output to the file
			try 
			{
				OutputToFile ();
			} catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			System.exit(0);
			
		}
	} 
	
	private class ListHandler implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			if (!e.getValueIsAdjusting())
			{
				int i = colorList.getSelectedIndex();
				
				selectedColor = new ColorObject(colors[i].GetName(), colors[i].GetColorValue(0), colors[i].GetColorValue(1), colors[i].GetColorValue(2));
				
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				
				redField.setText (String.valueOf(selectedColor.GetColorValue(0)));
				greenField.setText (String.valueOf(selectedColor.GetColorValue(1)));
				blueField.setText (String.valueOf(selectedColor.GetColorValue(2)));

				
			}
			
		}
	} 

}
