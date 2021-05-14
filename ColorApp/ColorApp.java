/**
 * ColorApp
 *
 * Olliver Aikenhead
 *
 * A Java Application that implements a color sampler, a graphical user interface is created, with colors loaded from a text file
 * App has the ability to modify preset colors loaded from colors.txt, can save and reopen to modified color values.
 */

/* import declarations */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;

/* ColorApp class */
public class ColorApp extends JFrame implements Serializable {
	/* labels */
	protected JLabel redLabel;
	protected JLabel greenLabel;
	protected JLabel blueLabel;
	/* fields */
	protected JTextField redField;
	protected JTextField greenField;
	protected JTextField blueField;
	/* buttons */
	protected JButton saveButton;
	protected JButton resetButton;
	protected JButton redPlus;
	protected JButton redMinus;
	protected JButton greenPlus;
	protected JButton greenMinus;
	protected JButton bluePlus;
	protected JButton blueMinus;
	/* color init */
	protected ColorBox colorBox;
	protected JList<String> colorList;
	protected ColorObject colors[];
	protected ColorObject selectedColor;

	/* needed this for compiler to stop giving warnings */
	private static final long serialVersionUID = 1L;

	/* main function */
	public static void main(String[] args) throws IOException {
		new ColorApp("Color Sampler");
	}

	/* sets up window for color app, and button functionality */
	public ColorApp(String title) throws IOException {
		super(title);
		setBounds(100, 100, 300, 300);
		addWindowListener(new WindowDestroyer());
		FileInputStream stream = new FileInputStream("Colors.txt");						// contains color vals for app
		InputStreamReader read = new InputStreamReader(stream);
		StreamTokenizer token = new StreamTokenizer(read);
		String readName;
		int readRed;
		int readGreen;
		int readBlue;
		colors = new ColorObject[11];
		int index = 0;

		while (token.nextToken() != StreamTokenizer.TT_EOF) {
			readName = token.sval;
			token.nextToken();

			readRed = (int) token.nval;
			token.nextToken();

			readGreen = (int) token.nval;
			token.nextToken();

			readBlue = (int) token.nval;

			colors[index] = new ColorObject(readName, readRed, readGreen, readBlue);
			index++;
		}
		stream.close();
		// printing colors created
		for (int i = 0; i < 11; i++) {
			System.out.println(colors[i]);
		}
		// creating linked list for colors, and box for colors to be displayed
		colorList = new JList<String>();
		colorList.addListSelectionListener(new ListHandler ());
		colorBox = new ColorBox();
		getContentPane().setLayout(new GridBagLayout ());
		GridBagConstraints gbc;

		// defining dim for the color box
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipady = 40;
		gbc.weightx = 0.1;
		gbc.weighty = 0.2;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 0;
		getContentPane().add(colorBox, gbc);

		// creating list of colors
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.1;
		gbc.gridheight = 7;
		gbc.gridx = 4;
		gbc.gridy = 0;
		getContentPane().add(colorList, gbc);

		// creating label for red
		redLabel = new JLabel("Red: ");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		getContentPane().add(redLabel, gbc);

		// creating field for red
		redField = new JTextField();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		getContentPane().add(redField, gbc);

		// creating minus button for red
		redMinus = new JButton("-");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 1;
		getContentPane().add(redMinus, gbc);

		// creating plus button for red
		redPlus = new JButton("+");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 1;
		getContentPane().add(redPlus, gbc);

		// creating label for green
		greenLabel = new JLabel("Green: ");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		getContentPane().add(greenLabel, gbc);

		// creating field for green
		greenField = new JTextField();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		getContentPane().add(greenField, gbc);

		// creating minus button for green
		greenMinus = new JButton("-");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 2;
		getContentPane().add(greenMinus, gbc);

		// creating plus button for green
		greenPlus = new JButton("+");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 2;
		getContentPane().add(greenPlus, gbc);

		// creating label for blue
		blueLabel = new JLabel("Blue: ");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		getContentPane().add(blueLabel, gbc);

		// creating field for blue
		blueField = new JTextField();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.3;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		getContentPane().add(blueField, gbc);

		// creating minus button for blue
		blueMinus = new JButton("-");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 3;
		getContentPane().add(blueMinus, gbc);

		// creating plus button for blue
		bluePlus = new JButton("+");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 3;
		getContentPane().add(bluePlus, gbc);

		// creating button for save
		saveButton = new JButton("Save");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.1;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 4;
		getContentPane().add(saveButton, gbc);

		// creating button for reset
		resetButton = new JButton("Reset");
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.1;
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 4;
		getContentPane().add(resetButton, gbc);

		setVisible(true);
		String colorNames[] = new String[11];
		for (int i = 0; i < 11; i++) {
			colorNames[i] = colors[i].GetName();
		}

		// adding button listeners
		saveButton.addActionListener(new ActionHandler());
		resetButton.addActionListener(new ActionHandler());
		redPlus.addActionListener(new ActionHandler());
		redMinus.addActionListener(new ActionHandler());
		greenPlus.addActionListener(new ActionHandler());
		greenMinus.addActionListener(new ActionHandler());
		bluePlus.addActionListener(new ActionHandler());
		blueMinus.addActionListener(new ActionHandler());

		// adding field listeners
		redField.addActionListener(new ActionHandler());
		greenField.addActionListener(new ActionHandler());
		blueField.addActionListener(new ActionHandler());

		// adding list of colors, setting default values
		colorList.setListData(colorNames);
		colorList.setSelectedIndex (0);
		selectedColor = new ColorObject(colors[0].GetName(), colors[0].GetColorValue(0), colors[0].GetColorValue(1), colors[0].GetColorValue(2));
		colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
		redField.setText(String.valueOf(selectedColor.GetColorValue(0)));
		greenField.setText(String.valueOf(selectedColor.GetColorValue(1)));
		blueField.setText(String.valueOf(selectedColor.GetColorValue(2)));
	}

	/* class implementation for color objects */
	public class ColorObject {
		public String name;
		public int r;
		public int g;
		public int b;

		// ctors
		public ColorObject() {
			name = "NONAME";
			r = 0;
			g = 0;
			b = 0;
		}

		public ColorObject(String sentName, int sentR, int sentG, int sentB) {
			name = sentName;
			r = sentR;
			g = sentG;
			b = sentB;

			// preventing values outside of the acceptable range (0,255)
			if (r > 255) {
				r = 255;
			} else if (r < 0) {
				r = 0;
			}
			if (g > 255) {
				g = 255;
			} else if (g < 0) {
				g = 0;
			}
			if (b > 255) {
				b = 255;
			} else if (b < 0) {
				b = 0;
			}
		}

		public int GetColorValue(int channel) {
			if (channel == 0) {										// r=0
				return r;
			} else if (channel == 1) {						// g=1
				return g;
			} else if (channel == 2) {						// b=2
				return b;
			} else {
				System.out.println("Invalid color definition requested.");

				return -1;
			}
		}

		public void SetColorValue(int channel, int value) {
			if (channel == 0) {										// r=0
				r = value;
			} else if (channel == 1) {						// g=1
				g = value;
			} else if (channel == 2) {						// b=2
				b = value;
			} else {
				System.out.println("Invalid color definition requested.");
			}
		}

		public String GetName() {
			return name;
		}

		 @Override
		 public String toString() {
		    return name + " " + r + " " + g + " " + b;
		 }
	}

	/* functionality for colorbox objects */
	public class ColorBox extends JComponent implements Serializable {
		// serializable ID, stops warnings
		private static final long serialVersionUID = 2L;
		Color myColor;

		public void paint(Graphics g) {
			Dimension dim = getSize();
			g.setColor(myColor);
			g.fillRect(1, 1, dim.width - 2, dim.height - 2);
		}

		public void SetColor(int r, int g, int b) {
			myColor = new Color(r, g, b);
			repaint();
		}
	}

	/* event driven handler */
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == redPlus && selectedColor.GetColorValue(0) < 255) {
				selectedColor.SetColorValue(0, selectedColor.GetColorValue(0) + 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				redField.setText(String.valueOf(selectedColor.GetColorValue(0)));
				setTitle("Color Sampler*");
			} else if (e.getSource() == redMinus && selectedColor.GetColorValue(0) > 0) {
				selectedColor.SetColorValue(0, selectedColor.GetColorValue(0) - 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				redField.setText(String.valueOf(selectedColor.GetColorValue(0)));
				setTitle("Color Sampler*");
			} else if (e.getSource() == greenPlus && selectedColor.GetColorValue(1) < 255) {
				selectedColor.SetColorValue(1, selectedColor.GetColorValue(1) + 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				greenField.setText(String.valueOf(selectedColor.GetColorValue(1)));
				setTitle("Color Sampler*");
			} else if (e.getSource() == greenMinus && selectedColor.GetColorValue(1) > 0) {
				selectedColor.SetColorValue(1, selectedColor.GetColorValue(1) - 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				greenField.setText(String.valueOf(selectedColor.GetColorValue(1)));
				setTitle("Color Sampler*");
			} else if (e.getSource() == bluePlus && selectedColor.GetColorValue(2) < 255) {
				selectedColor.SetColorValue(2, selectedColor.GetColorValue(2) + 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				blueField.setText (String.valueOf(selectedColor.GetColorValue(2)));
				setTitle("Color Sampler*");
			} else if (e.getSource() == blueMinus && selectedColor.GetColorValue(2) > 0) {
				selectedColor.SetColorValue(2, selectedColor.GetColorValue(2) - 5);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				blueField.setText (String.valueOf(selectedColor.GetColorValue(2)));
				setTitle("Color Sampler*");
			} else if (e.getSource() == saveButton) {
				// saving the color
				int i = colorList.getSelectedIndex();
				colors[i] = new ColorObject(selectedColor.GetName(), selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				setTitle("Color Sampler");
			} else if (e.getSource() == resetButton) {
				// reseting the color
				int i = colorList.getSelectedIndex();
				selectedColor = new ColorObject(colors[i].GetName(), colors[i].GetColorValue(0), colors[i].GetColorValue(1), colors[i].GetColorValue(2));
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				redField.setText(String.valueOf(selectedColor.GetColorValue(0)));
				greenField.setText(String.valueOf(selectedColor.GetColorValue(1)));
				blueField.setText(String.valueOf(selectedColor.GetColorValue(2)));
				setTitle("Color Sampler");
			} else if (e.getSource() == redField) {
				int newR = Integer.parseInt(redField.getText ());
				selectedColor.SetColorValue(0, newR);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
			} else if (e.getSource() == greenField) {
				int newG = Integer.parseInt(greenField.getText ());
				selectedColor.SetColorValue(1, newG);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
			} else if (e.getSource() == blueField) {
				int newB = Integer.parseInt(blueField.getText ());
				selectedColor.SetColorValue(2, newB);
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
			}
		}
	}

	/* closes the application if window is closed */
	private class WindowDestroyer extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			// output to file
			try {
				OutputToFile();
			} catch (IOException e1) {
				// auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

	/* list handler */
	private class ListHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				int i = colorList.getSelectedIndex();
				selectedColor = new ColorObject(colors[i].GetName(), colors[i].GetColorValue(0), colors[i].GetColorValue(1), colors[i].GetColorValue(2));
				colorBox.SetColor(selectedColor.GetColorValue(0), selectedColor.GetColorValue(1), selectedColor.GetColorValue(2));
				redField.setText(String.valueOf(selectedColor.GetColorValue(0)));
				greenField.setText(String.valueOf(selectedColor.GetColorValue(1)));
				blueField.setText(String.valueOf(selectedColor.GetColorValue(2)));
			}
		}
	}

	/* writes saved color data to an output file */
	public void OutputToFile() throws IOException {
		FileOutputStream ostream = new FileOutputStream("Colors.txt");
		PrintWriter write = new PrintWriter(ostream);
		for (int i = 0; i < 11; i++) {
			write.println(colors[i].GetName() + " " + colors[i].GetColorValue(0) + " " + colors[i].GetColorValue(1) + " " + colors[i].GetColorValue(2));
		}
		write.flush();
		ostream.close();
	}
}
