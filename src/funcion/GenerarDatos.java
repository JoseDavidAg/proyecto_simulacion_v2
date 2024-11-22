package funcion;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Random;


public class GenerarDatos {

    // Este método genera nuevos datos basados en los valores de la tabla
    public static void generarMasDatos(JTable JTableCSV, int cantidadDeFilas) {
        DefaultTableModel model = (DefaultTableModel) JTableCSV.getModel();
        int rowCount = model.getRowCount();

        if (rowCount == 0) {
            System.err.println("La tabla está vacía. Carga un archivo CSV primero.");
            return;
        }

        // Tomar el último valor de cada columna de la tabla para hacer las proyecciones
        double ultimaTasaNatalidad = Double.parseDouble((String) model.getValueAt(rowCount - 1, 0));
        double ultimaRelacionDesechos = Double.parseDouble((String) model.getValueAt(rowCount - 1, 2));
        double ultimaOcupacionInadecuada = Double.parseDouble((String) model.getValueAt(rowCount - 1, 3));
        double ultimaEsperanzaVida = Double.parseDouble((String) model.getValueAt(rowCount - 1, 1));

        // Crear una instancia de Random para generar probabilidades
        Random rand = new Random();

        // Generar las filas de datos simulados
        for (int i = 1; i <= cantidadDeFilas; i++) {
            // Proyección de los nuevos valores
            double variacionTasaNatalidad = rand.nextGaussian() * 0.1; // Generar una variación aleatoria
            double variacionRelacionDesechos = rand.nextGaussian() * 0.02;
            double variacionOcupacionInadecuada = rand.nextGaussian() * 0.02;
            double variacionEsperanzaVida = rand.nextGaussian() * 0.5;

            // Calcular los nuevos valores
            double nuevaTasaNatalidad = ultimaTasaNatalidad + variacionTasaNatalidad;
            double nuevoRelacionDesechos = ultimaRelacionDesechos + variacionRelacionDesechos;
            double nuevaOcupacionInadecuada = ultimaOcupacionInadecuada + variacionOcupacionInadecuada;
            double nuevaEsperanzaVida = ultimaEsperanzaVida + variacionEsperanzaVida;

            // Agregar la nueva fila con los datos generados
            model.addRow(new Object[]{
                String.format("%.2f", nuevaTasaNatalidad), // Tasa de natalidad generada
                String.format("%.2f", nuevaEsperanzaVida), // Esperanza de vida generada
                String.format("%.2f", nuevoRelacionDesechos), // Relación de desechos generada
                String.format("%.2f", nuevaOcupacionInadecuada), // Ocupación inadecuada generada
                String.format("%.4f", nuevaTasaNatalidad * 0.8), // Daño ambiental (simplificado)
                String.format("%.4f", nuevaTasaNatalidad * 0.75), // Tasa de natalidad calculada
                String.format("%.2f", nuevaEsperanzaVida) // Esperanza de vida calculada
            });

            // Actualizar los valores para la siguiente proyección
            ultimaTasaNatalidad = nuevaTasaNatalidad;
            ultimaRelacionDesechos = nuevoRelacionDesechos;
            ultimaOcupacionInadecuada = nuevaOcupacionInadecuada;
            ultimaEsperanzaVida = nuevaEsperanzaVida;
        }
    }
}
