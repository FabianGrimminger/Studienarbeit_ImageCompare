package main.Compare;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		try {
			BufferedImage first  = ImageIO.read(new File("TestImage"+File.separator+"4colors.jpg"));
			BufferedImage secound  = ImageIO.read(new File("TestImage"+File.separator+"4colors.png"));
			compare(first, secound);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static int compare(BufferedImage original, BufferedImage changed) throws IOException {
		int difference = 0;
		BufferedImage image = new BufferedImage(original.getWidth(),original.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int i=0;i<original.getWidth();i++) {
			for(int j=0;j<original.getHeight();j++) {
				Color c_org = new Color(original.getRGB(i, j));
				Color c_ch = new Color(changed.getRGB(i, j));
				int reddiff = (int)Math.pow(c_org.getRed()-c_ch.getRed(),1);
				int greendiff = (int)Math.pow(c_org.getGreen()-c_ch.getGreen(),1);
				int bluediff = (int)Math.pow(c_org.getBlue()-c_ch.getBlue(),1);
				
				Color newColor = new Color(Math.abs(reddiff), Math.abs(greendiff), Math.abs(bluediff));
				
				image.setRGB(i, j, newColor.getRGB());
				
//				if(reddiff>5)
//				System.out.println("Rot("+i+","+j+"):"+reddiff);
//				if(greendiff>5)
//				System.out.println("Grün("+i+","+j+"):"+greendiff);
//				if(bluediff>5)
//				System.out.println("Blue("+i+","+j+"):"+bluediff);
				
				difference += (int)Math.pow(c_org.getRed()-c_ch.getRed(),2);
				difference += (int)Math.pow(c_org.getBlue()-c_ch.getBlue(),2);
				difference += (int)Math.pow(c_org.getGreen()-c_ch.getGreen(),2);
			}
		}
		ImageIO.write(image, "png", new File("TestImage"+File.separator+"difference.png"));
		double maximalValue = original.getWidth()*original.getHeight()*(255*255+255*255+255*255);
		System.out.printf("Zu %.2f %s gleich", (1-difference/maximalValue)*100,"%");
		return difference;
	}

}
