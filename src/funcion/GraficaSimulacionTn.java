/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcion;

/**
 *
 * @author yael
 */

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficaSimulacionTn {

    // Este método toma los datos de la tabla y los grafica en un panel proporcionado
    public static void graficarDesdeTabla(JTable JTableCSV, JPanel jpane1) {
        DefaultTableModel model = (DefaultTableModel) JTableCSV.getModel();
        int rowCount = model.getRowCount();

        // Asegurarnos de que la tabla no esté vacía
        if (rowCount == 0) {
            System.err.println("La tabla está vacía. Carga un archivo CSV primero.");
            return;
        }

        // Crear series para la gráfica
        XYSeries seriesTasaNatalidad = new XYSeries("Tasa de Natalidad");
        XYSeries seriesTasaNatalidadCalculada = new XYSeries("Tasa de Natalidad Calculada");

        // Predefinir el año inicial (2021)
        int añoInicial = 2021;

        // Agregar los datos de la tabla a las series
        for (int i = 0; i < rowCount; i++) {
            // El año aumenta de 1 por cada fila
            int anio = añoInicial + i;

            // Obtener los valores de la tabla
            double tasaNatalidad = Double.parseDouble((String) model.getValueAt(i, 0)); // Tasa de natalidad
            double tasaNatalidadCalculada = Double.parseDouble((String) model.getValueAt(i, 5)); // Tasa de natalidad calculada

            // Agregar los datos a las series
            seriesTasaNatalidad.add(anio, tasaNatalidad);
            seriesTasaNatalidadCalculada.add(anio, tasaNatalidadCalculada);
        }

        // Crear el dataset para la gráfica
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesTasaNatalidad);
        dataset.addSeries(seriesTasaNatalidadCalculada);

        // Crear la gráfica con el dataset
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Proyección de Tasa de Natalidad",   // Título de la gráfica
                "Año",                              // Eje X (Año)
                "Tasa de Natalidad",                // Eje Y (Tasa de Natalidad)
                dataset                             // Datos de la gráfica
        );

        // Crear un panel para la gráfica
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600)); // Tamaño del panel

        // Limpiar el panel existente
        jpane1.removeAll();
        jpane1.setLayout(new java.awt.BorderLayout());
        jpane1.add(chartPanel); // Agregar la gráfica al panel
        jpane1.validate(); // Validar el panel para actualizar los cambios
    }
}