package Graphing2;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Salter {
	XYSeries points;
	XYSeriesCollection database;
	JFreeChart chart;
	Random rng;
	
	public Salter(XYSeries input, double salt) {
		points = input;
		rng = new Random();
		for(int i = 0; i < points.getItemCount(); i++) {
			double old = points.getDataItem(i).getYValue();
			points.updateByIndex(i, old + ((2.0 * rng.nextDouble() - 1.0) * salt));
		}
		database = new XYSeriesCollection();
		database.addSeries(points);
		chart = ChartFactory.createXYLineChart("Salt", "X-Value", "Y-Value", database);
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
