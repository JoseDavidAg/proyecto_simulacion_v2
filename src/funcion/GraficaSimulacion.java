package funcion;

import Vista.grafica;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficaSimulacion {

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
        XYSeries seriesNormal = new XYSeries("Esperanza de Vida Normal");
        XYSeries seriesCalculada = new XYSeries("Esperanza de Vida Calculada");

        // Predefinir el año inicial (2021)
        int añoInicial = 2021;

        // Agregar los datos de la tabla a las series
        for (int i = 0; i < rowCount; i++) {
            // El año aumenta de 1 por cada fila
            int anio = añoInicial + i;

            // Obtener los valores de la tabla
            double esperanzaVidaNormal = Double.parseDouble((String) model.getValueAt(i, 1)); // Esperanza de vida normal
            double esperanzaVidaCalculada = Double.parseDouble((String) model.getValueAt(i, 6)); // Esperanza de vida calculada

            // Agregar los datos a las series
            seriesNormal.add(anio, esperanzaVidaNormal);
            seriesCalculada.add(anio, esperanzaVidaCalculada);
        }

        // Crear el dataset para la gráfica
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesNormal);
        dataset.addSeries(seriesCalculada);

        // Crear la gráfica con el dataset
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Proyección de Esperanza de Vida",   // Título de la gráfica
                "Año",                              // Eje X (Año)
                "Esperanza de Vida",                // Eje Y (Esperanza de Vida)
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
