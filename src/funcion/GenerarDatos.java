package funcion;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Random;

public class GenerarDatos {
    public static int filasOriginales = 0; // Variable para almacenar la cantidad de filas originales

    // Este método genera nuevos datos basados en los valores de la tabla
    public static void generarMasDatos(JTable JTableCSV, int cantidadDeFilas) {
        DefaultTableModel model = (DefaultTableModel) JTableCSV.getModel();
        int rowCount = model.getRowCount();

        if (rowCount == 0) {
            System.err.println("La tabla está vacía. Carga un archivo CSV primero.");
            return;
        }

        // Almacenar la cantidad de filas originales antes de agregar nuevas
        filasOriginales = rowCount;

        // Tomar los últimos valores de la tabla para hacer las proyecciones
        int ultimoAnio = Integer.parseInt((String) model.getValueAt(rowCount - 1, 0));
        double ultimaTasaNatalidad = Double.parseDouble((String) model.getValueAt(rowCount - 1, 1));
        double ultimaEsperanzaVida = Double.parseDouble((String) model.getValueAt(rowCount - 1, 2));
        double ultimaRelacionDesechos = Double.parseDouble((String) model.getValueAt(rowCount - 1, 3));
        double ultimaOcupacionInadecuada = Double.parseDouble((String) model.getValueAt(rowCount - 1, 4));

        // Crear una instancia de Random para generar variaciones
        Random rand = new Random();

        // Generar las filas de datos simulados
        for (int i = 1; i <= cantidadDeFilas; i++) {
            // Proyección de los nuevos valores
            double variacionTasaNatalidad = rand.nextGaussian() * 0.1;
            double variacionEsperanzaVida = rand.nextGaussian() * 0.5;
            double variacionRelacionDesechos = rand.nextGaussian() * 0.02;
            double variacionOcupacionInadecuada = rand.nextGaussian() * 0.02;

            // Calcular los nuevos valores
            double nuevaTasaNatalidad = ultimaTasaNatalidad + variacionTasaNatalidad;
            double nuevaEsperanzaVida = ultimaEsperanzaVida + variacionEsperanzaVida;
            double nuevoRelacionDesechos = ultimaRelacionDesechos + variacionRelacionDesechos;
            double nuevaOcupacionInadecuada = ultimaOcupacionInadecuada + variacionOcupacionInadecuada;

            // Agregar la nueva fila con los datos generados
            model.addRow(new Object[]{
                String.valueOf(ultimoAnio + i), // Incrementar el año automáticamente
                String.format("%.2f", nuevaTasaNatalidad), // Nueva tasa de natalidad
                String.format("%.2f", nuevaEsperanzaVida), // Nueva esperanza de vida
                String.format("%.2f", nuevoRelacionDesechos), // Nueva relación de desechos
                String.format("%.2f", nuevaOcupacionInadecuada) // Nueva ocupación inadecuada
            });

            // Actualizar los valores para la siguiente proyección
            ultimaTasaNatalidad = nuevaTasaNatalidad;
            ultimaEsperanzaVida = nuevaEsperanzaVida;
            ultimaRelacionDesechos = nuevoRelacionDesechos;
            ultimaOcupacionInadecuada = nuevaOcupacionInadecuada;
        }
    }
}