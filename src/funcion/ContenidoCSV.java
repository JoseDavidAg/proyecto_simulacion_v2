package funcion;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContenidoCSV {
    private List<String[]> filas;
    private String[] encabezados;

    public ContenidoCSV() {
        filas = new ArrayList<>();
    }

    public void agregarFila(String[] fila) {
        
        filas.add(fila);
    }

    public int obtenerNumeroDeFilas() {
        return filas.size();
    }

    public List<String[]> obtenerFilas() {
        return filas;
    }

    public void limpiar() {
        filas.clear();
    }

    // Método para cargar CSV desde archivo
    public void cargarDesdeArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                boolean esPrimeraLinea = true;

                // Limpiar filas antes de cargar nuevas
                filas.clear();

                // Leer las líneas del archivo
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split(",");

                    // La primera línea será el encabezado
                    if (esPrimeraLinea) {
                        encabezados = valores;
                        esPrimeraLinea = false;
                    } else {
                        agregarFila(valores);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Archivo leído con exito");
        
        }
    }

    public String[] obtenerEncabezados() {
        return encabezados;
    }
}

