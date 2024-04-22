package modelo;

import util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SociosModeloDAO implements GenericDAO<SociosModelo, String>{
    private static final String SELECT_ALL = "SELECT * FROM SociosModelo";
    private static final String SELECT_FEDERADO = "SELECT * FROM SociosModelo WHERE tipo = 'Federado'";
    private static final String SELECT_ESTANDAR = "SELECT * FROM SociosModelo WHERE tipo = 'Estandar'";
    private static final String SELECT_INFANTIL = "SELECT * FROM SociosModelo WHERE tipo = 'Infantil'";
    private static final String INSERT = "INSERT INTO SociosModelo VALUES (?, ?, ?)";


    public SociosModelo obtenerPorId(String n_socio_SociosModelo) {
        SociosModelo SociosModelo = null;
        String query = "SELECT * FROM SociosModelo WHERE n_socio_SociosModelo = ?";

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, n_socio_SociosModelo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    SociosModelo = mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el Socios por numero de socio", e);
        }

        return SociosModelo;
    }

    public SociosModelo obtenerPorNombre(String nombre) {
        SociosModelo SociosModelo = null;
        String query2 = "SELECT * FROM SociosModelo WHERE nombre = ?";

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query2)) {

            preparedStatement.setString(1, nombre);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    SociosModelo = mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el Socios por nombre", e);
        }

        return SociosModelo;
    }

    public List<SociosModelo> obtenerTodos() {
        List<SociosModelo> SociosModelo = new ArrayList<>();

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                SociosModelo.add(mapearDesdeResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener todos los Socios", e);
        }

        return SociosModelo;
    }

    public List<SociosModelo> obtenerSociosFederados() {
        List<SociosModelo> SociosFederadosModelo = new ArrayList<>();

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FEDERADO);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipoSocio = resultSet.getString("tipo");

                if ("Federado".equalsIgnoreCase(tipoSocio)) {
                    SociosFederadosModelo.add(new SociosFederadosModelo(
                            resultSet.getString("n_socio"),//no se si ponerlo ya que si que esta en la clase super pero no en las demas
                            resultSet.getString("nombre"),
                            resultSet.getString("nif"),
                            resultSet.getString("federacion"),
                            resultSet.getString("descuento_cuota"),
                            resultSet.getString("descuento_exc"),
                        )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener Socios Federados", e);
        }

        return SociosFederadosModelo;
    }

    public List<SociosModelo> obtenerSocioEstandarModelo() {
        List<SociosModelo> SocioEstandarModelo = new ArrayList<>();

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ESTANDAR);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipoSocio = resultSet.getString("tipo");

                if ("estandar".equalsIgnoreCase(tipoSocio)) {
                    SocioEstandarModelo.add(new SocioEstandarModelo(
                            resultSet.getString("n_socio"),
                            resultSet.getString("nombre"),
                            resultSet.getString("nif"),
                            resultSet.getString("seguro"),

                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener Socios estándar", e);
        }

        return SocioEstandarModelo;
    }

    public List<SociosModelo> obtenerSocioInfantilModelo() {
        List<SociosModelo> SocioInfantilModelo = new ArrayList<>();

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_INFANTIL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipoSocio = resultSet.getString("tipo");

                if ("estandar".equalsIgnoreCase(tipoSocio)) {
                    SocioInfantilModelo.add(new SocioInfantilModelo(
                            resultSet.getString("n_socio"),
                            resultSet.getString("nombre"),
                            resultSet.getString("n_socioPadreMadre"),
                            resultSet.getString("descuento_cuota"),
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener Socios Infantiles", e);
        }

        return SocioInfantilModelo;
    }


    private SociosModelo mapearDesdeResultSet(ResultSet resultSet) throws SQLException {
        String tipoSocio = resultSet.getString("tipo");

        if ("Federado".equalsIgnoreCase(tipoSocio)) {
            return new SociosFederadosModelo(
                    resultSet.getString("n_socio"),
                    resultSet.getString("nombre"),
                    resultSet.getString("nif"),
                    resultSet.getString("federacion"),
                    resultSet.getString("descuento_cuota"),
                    resultSet.getString("descuento_exc"),

            );
        } if ("Estandar".equalsIgnoreCase(tipoSocio)) {
            return new SocioEstandarModelo(
                    resultSet.getString("n_socio"),
                    resultSet.getString("nombre"),
                    resultSet.getString("nif"),
                    resultSet.getString("seguro"),

            );
        }if ("Infantil".equalsIgnoreCase(tipoSocio)) {
            return new SocioInfantilModelo(
                    resultSet.getString("n_socio"),
                    resultSet.getString("nombre"),
                    resultSet.getString("n_socioPadreMadre"),
                    resultSet.getString("descuento_cuota"),

            );
        }
    }

    public void insertar(SociosModelo socio) {
    DataBaseConnection DatabaseConection;
    try (Connection connection = DataBaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT)) {

        mapearAStatement(statement, socio);

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error al insertar el socio", e);
    }
}
    private void mapearAStatement(PreparedStatement statement, SociosModelo Socio) throws SQLException {
        statement.setString(1, Socio.getN_socio());
        statement.setString(2, Socio.getNombre());
        statement.setDouble(3, Socio.getCuotaMensual());
        statement.setString(3, Socio.tipoSocio());

    }

}
