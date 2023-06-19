package com.example.basedatos_ejemplo;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class JasperReports extends JFrame {
    /**
     * Muestra un informe Jasper simple.
     * Realiza una conexión a la base de datos utilizando los parámetros de conexión especificados.
     * Compila el archivo jrxml del informe.
     * Define y asigna parámetros al informe.
     * Rellena el informe con los datos obtenidos de la base de datos.
     * Muestra el informe en un componente JRViewer.
     * Configura y muestra la ventana que contiene el JRViewer.
     * Imprime "Done!" en la salida estándar una vez que se ha completado el proceso.
     *
     * @throws JRException           Si ocurre algún error durante la generación o visualización del informe Jasper.
     * @throws ClassNotFoundException Si no se encuentra la clase de la base de datos especificada en la conexión.
     * @throws SQLException         Si ocurre algún error al establecer la conexión con la base de datos.
     */
    public void showReportSimple() throws JRException, ClassNotFoundException, SQLException {

         String servidor = "jdbc:mariadb://localhost:3306/pokemon";
         String usuario = "root";
         String passwd = "";

         Connection conexionBBDD;
        // Nos conectamos
        conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);

        String reportSrcFile = "src/main/resources/com/example/basedatos_ejemplo/Reportes/Blank_A4.jrxml";

        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        // Ejemplo de definición de parámetros para el informe

        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("company", "MAROTHIA TECHS");
        parameters.put("receipt_no", "RE101".toString());

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conexionBBDD);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        System.out.print("Done!");

    }
}
