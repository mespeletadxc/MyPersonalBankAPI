package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.properties.PropertyValues;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDBRepo implements IClientesRepo {

    private static String db_url = null;

    public ClientesDBRepo() throws Exception {
        PropertyValues props = new PropertyValues();
        db_url = props.getPropValues().getProperty("db_url");
    }

    @Override
    public List<Cliente> getAll() throws Exception {

        List<Cliente> clientes = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cliente");
                ResultSet rs = stmt.executeQuery();
        ) {

            while (rs.next()) {
                System.out.println("dtype: " + rs.getString("dtype"));
                System.out.println("length dtype: " + rs.getString("dtype").length());
                // if (rs.getString("dtype") == "Personal                       ") {
                if (rs.getString("dtype").equals("Personal")) {
                    System.out.println("if 1");
                    Cliente cli = new Personal(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            rs.getDate("alta").toLocalDate(),
                            rs.getBoolean("activo"),
                            rs.getBoolean("moroso"),
                            rs.getString("dni")
                    );
                    clientes.add(cli);
                } else {
                    System.out.println("if 2");
                    Cliente cli = new Empresa(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            rs.getDate("alta").toLocalDate(),
                            rs.getBoolean("activo"),
                            rs.getBoolean("moroso"),
                            rs.getString("cif"),
                            new String[]{rs.getString("unidades_de_negocio")}
                    );
                    clientes.add(cli);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return clientes;
    }


    @Override
    public Cliente getClientById(Integer id) throws Exception {
        Cliente cli = null;
        try (
                Connection conn = DriverManager.getConnection(db_url);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM cliente c WHERE c.id='" + id + "'")
        ) {
            if (rs.next()) {
                if (rs.getString("dtype") == "Personal") {
                    cli = new Personal(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            rs.getDate("alta").toLocalDate(),
                            rs.getBoolean("activo"),
                            rs.getBoolean("moroso"),
                            rs.getString("dni")
                    );
                } else {
                    cli = new Empresa(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            rs.getDate("alta").toLocalDate(),
                            rs.getBoolean("activo"),
                            rs.getBoolean("moroso"),
                            rs.getString("cif"),
                            new String[]{rs.getString("unidades_de_negocio")}
                    );

                }

            }
            ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return cli;
    }

    @Override
    public Cliente addClient(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public boolean deleteClient(Cliente cliente) throws Exception {
        return false;
    }

    @Override
    public Cliente updateClient(Cliente cliente) throws Exception {
        return null;
    }
}
