package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.properties.PropertyValues;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
public class ClientesDBRepo implements IClientesRepo {

    //private String db_url = null;
    private String db_url = null;

    /*public ClientesDBRepo() throws Exception {
        PropertyValues props = new PropertyValues();
        db_url = props.getPropValues().getProperty("db_url");
    }*/

    @Override
    public List<Cliente> getAll() throws Exception {

        List<Cliente> clientes = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cliente");
                ResultSet rs = stmt.executeQuery();
        ) {

            while (rs.next()) {
//                System.out.println("dtype: " + rs.getString("dtype"));
//                System.out.println("length dtype: " + rs.getString("dtype").length());
                // if (rs.getString("dtype") == "Personal                       ") {
                if (rs.getString("dtype").equals("Personal")) {
                    //System.out.println("if 1");
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
                    // System.out.println("if 2");
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
                    //Para recuperar string separados por , en array
                    //String unidades = String.join(",", cliente.getUnidadesNegocio());
                    //unidades.split(",");
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
    public Empresa addClientEmpresa(Empresa cliente) throws Exception {

        String sql = "INSERT INTO cliente values (?,NULL,?,?,?,?,?,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, "Empresa");
            stmt.setBoolean(2, cliente.isActivo());
            stmt.setString(3, cliente.getAlta().toString());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getEmail());
            stmt.setBoolean(6, cliente.isMoroso());
            stmt.setString(7, cliente.getNombre());
            stmt.setString(8, cliente.getCif());
            String unidades = String.join(",", cliente.getUnidadesNegocio());
            stmt.setString(9, unidades);
            stmt.setString(10, null);


            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                cliente.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Cliente Empresa creado erroneamente!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return cliente;
    }

    @Override
    public Personal addClientPersonal(Personal cliente) throws Exception {

        String sql = "INSERT INTO cliente values (?,NULL,?,?,?,?,?,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, "Personal");
            stmt.setBoolean(2, cliente.isActivo());
            stmt.setString(3, cliente.getAlta().toString());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getEmail());
            stmt.setBoolean(6, cliente.isMoroso());
            stmt.setString(7, cliente.getNombre());
            stmt.setString(8, null);
            stmt.setString(9, null);
            stmt.setString(10, cliente.getDni());

            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                cliente.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Cliente Personal creado erroneamente!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return cliente;
    }
//    @Override
//    public Cliente addClient(Cliente cliente) throws Exception {
//
//        String sql = "INSERT INTO cliente values (?,NULL,?,?,?,?,?,?,?,?,?)";
//
//        try (
//                Connection conn = DriverManager.getConnection(db_url);
//                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        ) {
//
//            stmt.setBoolean(3, cliente.isActivo());
//            stmt.setString(4, cliente.getAlta().toString());
//            stmt.setString(5, cliente.getDireccion());
//            stmt.setString(6, cliente.getEmail());
//            stmt.setBoolean(7, cliente.isMoroso());
//            stmt.setString(8, cliente.getNombre());
//
//            int rows = stmt.executeUpdate();
//
//            ResultSet genKeys = stmt.getGeneratedKeys();
//            if (genKeys.next()) {
//                cliente.setId(genKeys.getInt(2));
//            } else {
//                throw new SQLException("Cliente creado erroneamente!!!");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new Exception(e);
//        }
//
//        return cliente;
//    }

    @Override
    public boolean deleteClient(Cliente cliente) throws Exception {
        return false;
    }

    @Override
    public Cliente updateClient(Cliente cliente) throws Exception {
        return null;
    }
}
