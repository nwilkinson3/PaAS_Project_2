package Graphing2;

import java.io.IOException;

public class GraphingMain {
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		
		Plotter plot = new Plotter(0, 5);
		plot.print("plot1");
		Salter salt = new Salter(plot.getChart(), 1);
		salt.print("salt1");
		Smoother smooth = new Smoother(salt.getChart(), 25, 4);
		smooth.print("smooth1"); 
		
	}
	
}
