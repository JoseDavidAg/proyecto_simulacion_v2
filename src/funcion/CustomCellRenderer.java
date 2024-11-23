package funcion;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomCellRenderer extends DefaultTableCellRenderer {

    private int filasOriginales;

    public CustomCellRenderer(int filasOriginales) {
        this.filasOriginales = filasOriginales; // Número de filas originales antes de generar nuevas
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Obtener el componente estándar de renderizado
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Aplicar fondo azul claro para las filas nuevas
        if (row >= filasOriginales) {
            c.setBackground(new Color(173, 216, 230)); // Azul claro
            c.setForeground(Color.BLACK); // Texto negro
        } else {
            c.setBackground(Color.WHITE); // Fondo blanco para filas originales
            c.setForeground(Color.BLACK); // Texto negro
        }

        return c;
    }
}