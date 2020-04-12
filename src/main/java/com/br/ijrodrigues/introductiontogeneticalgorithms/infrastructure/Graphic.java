package com.br.ijrodrigues.introductiontogeneticalgorithms.infrastructure;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.List;

public class Graphic extends ApplicationFrame {

    public Graphic(String title, List<Double> data, String categoryLabel, String valueLabel) {
        super(title);

        JFreeChart lineChart = ChartFactory.createLineChart(
                title,
                categoryLabel,
                valueLabel,
                loadData(data),
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset loadData(List<Double> data) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (int i = 0; i < data.size(); i++) {
            dataSet.addValue((Number) data.get(i), "Best Solution", i);
        }
        return dataSet;
    }
}