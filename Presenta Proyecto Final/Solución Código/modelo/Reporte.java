package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Reporte {
    private String filtros;
    private List<String> resultados;

    public Reporte(String filtros, List<String> resultados) {
        this.filtros = filtros;
        this.resultados = resultados;
    }

    public void exportarCSV(String rutaArchivo) {
        try {
            File carpeta = new File(rutaArchivo).getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }
            FileWriter writer = new FileWriter(rutaArchivo);
            for (String linea : resultados) {
                writer.append(linea).append("\n");
            }
            writer.close();
            System.out.println(" Reporte exportado correctamente a: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println(" Error al exportar el reporte: " + e.getMessage());
        }
    }

    public String getFiltros() {
        return filtros;
    }

    public List<String> getResultados() {
        return resultados;
    }
}
