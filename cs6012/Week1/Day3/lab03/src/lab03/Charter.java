package lab03;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Charter {

  public void createChart(String dataFile, String filename) {
    JFreeChart chart = ChartFactory.createXYLineChart("Contains on sorted set", "Size (N)", "Time (ms)",
        createDataSet(dataFile));
    // chart.getXYPlot().setDomainAxis(new LogarithmicAxis("Size (N"));
    try {
      ChartUtils.saveChartAsPNG(new File(filename), chart, 500, 500);
    } catch (IOException e) {
      e.printStackTrace();
    }
    showChart(chart);
  }

  private void showChart(JFreeChart chart) {
    JFrame frame = new JFrame();
    frame.setTitle("Remove Timing Experiement");
    ChartPanel chartPanel = new ChartPanel(chart);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(400, 200));
    frame.add(chartPanel);
    frame.pack();
    frame.setVisible(true);
  }

  private XYDataset createDataSet(String dataFile) {
    XYSeriesCollection dataset = new XYSeriesCollection();
    try (FileReader reader = new FileReader(dataFile); BufferedReader br = new BufferedReader(reader)) {
      String line;
      XYSeries series = new XYSeries("remove");
      while ((line = br.readLine()) != null) {
        String[] split = line.split("\t");
        series.add(new XYDataItem(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
      }
      dataset.addSeries(series);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return dataset;
  }
}
