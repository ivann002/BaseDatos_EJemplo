package com.example.basedatos_ejemplo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
    private final String servidor = "jdbc:mariadb://localhost:3306/pokemon";
    private final String usuario = "root";
    private final String passwd = "";

    private Connection conexionBBDD;

    public ObservableList<Pokemon> obtenerPokemon() {

        ObservableList<Pokemon> datosResultadoConsulta = FXCollections.observableArrayList();
        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "
                    + "FROM pokemon "
                    + "ORDER By IDpoke";

            // Ejecutamos la consulta y nos devuelve una matriz de filas (registros) y columnas (datos)
            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            // Debemos cargar los datos en el ObservableList (Que es un ArrayList especial)
            // Los datos que devuelve la consulta no son directamente cargables en nuestro objeto
            while (resultadoConsulta.next()) {
                datosResultadoConsulta.add(new Pokemon(
                        resultadoConsulta.getInt("IDpoke"),
                        resultadoConsulta.getString("pokename"),
                        resultadoConsulta.getInt("HP"),
                        resultadoConsulta.getInt("attack"),
                        resultadoConsulta.getInt("defense"),
                        resultadoConsulta.getInt("spattack"),
                        resultadoConsulta.getInt("spdefense"),
                        resultadoConsulta.getInt("speed"),
                        resultadoConsulta.getInt("dualtype"))
                );
                System.out.println("Row [1] added " + resultadoConsulta.toString());
            }
            conexionBBDD.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            conexionBBDD.close();
        } finally {
            return datosResultadoConsulta;
        }
    }

    // Alta de un nuevo producto
    //
    public Boolean altaProducto(Pokemon poke) {

        int registrosAfectadosConsulta = 0;

        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "INSERT INTO pokemon ("
                    + " IDpoke ,"
                    + " pokename ,"
                    + " HP ,"
                    + " attack ,"
                    + " defense ,"
                    + " spattack ,"
                    + " spdefense ,"
                    + " speed ,"
                    + " dualtype  )"
                    + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setInt(1, poke.getIDpoke());
            st.setString(2, poke.getPokename());
            st.setInt(3, poke.getHP());
            st.setInt(4, poke.getAttack());
            st.setInt(5, poke.getDefense());
            st.setInt(6, poke.getSpattack());
            st.setInt(7, poke.getSpdefense());
            st.setInt(7, poke.getSpeed());
            st.setInt(8, poke.getDualtype());

            // Ejecutamos la consulta preparada (con las ventajas de seguridad y velocidad en el servidor de BBDD
            // nos devuelve el número de registros afectados. Al ser un Insert nos debe devolver 1 si se ha hecho correctamente
            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            return false;
        }
    }
    public Boolean borrarProducto(Pokemon poke) {

        int registrosAfectadosConsulta = 0;

        try {

            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "DELETE FROM pokemon "
                    + " WHERE IDpoke = ? ";

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);

            st.setInt(1, Integer.valueOf(poke.getIDpoke()));

            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            return false;
        }
    }
    public Boolean actualizarProducto(Pokemon poke) {

        int registrosAfectadosConsulta = 0;

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "UPDATE pokemon ("
                    + " IDpoke ,"
                    + " pokename ,"
                    + " HP ,"
                    + " attack ,"
                    + " defense ,"
                    + " spattack ,"
                    + " spdefense ,"
                    + " speed ,"
                    + " dualtype  )"
                    + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setInt(1, poke.getIDpoke());
            st.setString(2, poke.getPokename());
            st.setInt(3, poke.getHP());
            st.setInt(4, poke.getAttack());
            st.setInt(5, poke.getDefense());
            st.setInt(6, poke.getSpattack());
            st.setInt(7, poke.getSpdefense());
            st.setInt(7, poke.getSpeed());
            st.setInt(8, poke.getDualtype());

            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            return false;
        }
    }

}
