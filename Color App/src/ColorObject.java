
public class ColorObject 
{
	public String name;
	public int r;
	public int g;
	public int b;
	
	//Constructors
	public ColorObject ()
	{
		name = "NONAME";
		r = 0;
		g = 0;
		b = 0;
		
	}
	
	public ColorObject (String sentName, int sentR, int sentG, int sentB)
	{
		name = sentName;
		r = sentR;
		g = sentG;
		b = sentB;
		
		//The following if statements are to prevent values outside of the acceptable range (0,255)
		if (r > 255)
		{
			r = 255;
			
		} else if (r < 0)
		{
			r = 0;
			
		}
		
		if (g > 255)
		{
			g = 255;
			
		} else if (g < 0)
		{
			g = 0;
			
		}
		
		if (b > 255)
		{
			b = 255;
			
		} else if (b < 0)
		{
			b = 0;
			
		}
		
	}
	
	public int GetColorValue (int channel) //0=r, 1=g, 2=b
	{
		if (channel == 0)
		{
			return r;
			
		} else if (channel == 1)
		{
			return g;
			
		} else if (channel == 2)
		{
			return b;
			
		} else
		{
			System.out.println ("Invalid color channel requested.");
			
			return -1;
			
		}
		
	}
	
	public void SetColorValue (int channel, int value) //0=r, 1=g, 2=b
	{
		if (channel == 0)
		{
			r = value;
			
		} else if (channel == 1)
		{
			g = value;
			
		} else if (channel == 2)
		{
			b = value;
			
		} else
		{
			System.out.println ("Invalid color channel requested.");
						
		}
		
	}
	
	public String GetName ()
	{
		return name;
		
	}
	
	 @Override
	 public String toString() 
	 {
	    return name + " " + r + " " + g + " " + b;
	    
	 }
}
