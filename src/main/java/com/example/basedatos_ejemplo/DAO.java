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

    /**
     * Obtiene una lista observable de Pokemon a partir de una consulta a la base de datos.
     *
     * @return Una lista observable de Pokemon que contiene los datos obtenidos de la consulta.
     */
    public ObservableList<Pokemon> obtenerPokemon() {


        ObservableList<Pokemon> datosResultadoConsulta = FXCollections.observableArrayList();
        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "
                    + "FROM pokemon "
                    + "ORDER By IDpoke";

            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

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
    /**
     * Realiza una operación de alta (inserción) de un Pokemon en la base de datos.
     *
     * @param poke El objeto Pokemon que se va a dar de alta.
     * @return true si el alta se realiza correctamente, false en caso contrario.
     */
    public Boolean altaPokemon(Pokemon poke) {

        int registrosAfectadosConsulta = 0;

        try {
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
            st.setInt(8, poke.getSpeed());
            st.setInt(9, poke.getDualtype());

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
    /**
     * Realiza una operación de borrado de un Pokemon en la base de datos.
     *
     * @param poke El objeto Pokemon que se va a borrar.
     * @return true si el borrado se realiza correctamente, false en caso contrario.
     */
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
    /**
     * Realiza una operación de actualización de un Pokemon en la base de datos.
     *
     * @param poke El objeto Pokemon que se va a actualizar.
     * @return true si la actualización se realiza correctamente, false en caso contrario.
     */
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
