package Graphing2;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Plotter {
	XYSeries points;
	XYSeriesCollection database;
	JFreeChart chart;
	
	public Plotter(int start, int end) {
		points = new XYSeries(1);
		for(double i = start; i <= end; i += 0.01) {
			points.add(i, Math.sin(Math.PI * i));
		}
		database = new XYSeriesCollection();
		database.addSeries(points);
		chart = ChartFactory.createXYLineChart("Plot", "X-Value", "Y-Value", database);
	}
	
	public void print(String filename) throws IOException {
		filename += ".jpeg";
		File file = new File(filename);
		ChartUtilities.saveChartAsJPEG(file, chart, 640, 480);
	}
	
	public XYSeries getChart() {
		return points;
	}
	
}
