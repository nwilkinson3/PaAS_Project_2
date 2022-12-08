package Graphing2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Smoother {
	DescriptiveStatistics stat;
	XYSeries points;
	XYSeriesCollection database;
	JFreeChart chart;
	
	public Smoother(XYSeries input, int windowSize, int numSmooths) throws CloneNotSupportedException {
		stat = new DescriptiveStatistics();
		if(windowSize%2 != 1) {	// make sure window is odd
			windowSize++;
		}
		stat.setWindowSize(windowSize);
		points = new XYSeries(1);
		for(int i = 0; i < numSmooths; i++) {
			int index = -1;
			for(int j = 0; j < input.getItemCount(); j++) {
				stat.addValue(input.getY(j).doubleValue());
				if(stat.getN()%2 == 1) {	// .getN() returns # of items in window already
					index++;
					points.add(input.getX(index), stat.getMean());
				}
			}
			ArrayList<Double> temp = new ArrayList<>();
			for(int j = windowSize-1; j >= 0; j--) {
				temp.add(stat.getElement(j));
			}
			stat.clear();
			while(!temp.isEmpty()) {
				stat.addValue(temp.remove(0));
			}
			while(index < input.getItemCount()-1) {
				stat.removeMostRecentValue();
				if(stat.getN()%2 == 1) {
					index++;
					points.add(input.getX(index), stat.getMean());
				}
			}
			if(i != numSmooths-1) {
				input = points.createCopy(0, points.getItemCount()-1);
				points.clear();
				stat.clear();
			}
		}
		database = new XYSeriesCollection();
		database.addSeries(points);
		chart = ChartFactory.createXYLineChart("Smooth", "X-Value", "Y-Value", database);
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
