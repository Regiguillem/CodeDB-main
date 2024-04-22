package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.DataBaseConnection;
import java.time.LocalDate;


import modelo.InscripcionesModelo;

public class InscripcionesModeloDAO implements GenericDAO<InscripcionesModelo, String> {

    // Constantes para consultas SQL
    private static final String SELECT_ALL = "SELECT * FROM inscripciones";
    private static final String INSERT = "INSERT INTO inscripciones (socio_id, excursion_id, fecha_inscripcion) VALUES (?, ?, ?)";


    @Override
    public InscripcionesModelo obtenerPorId(String id) {
        InscripcionesModelo inscripcion = null;
        // Implementa la lógica para obtener una inscripción por su ID
        String SELECT_BY_ID = "SELECT * FROM inscripciones WHERE id = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    inscripcion = mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la inscripción por ID", e);
        }
        return inscripcion;
    }


    // Método para obtener todas las inscripciones
    @Override
    public List<InscripcionesModelo> obtenerTodos() {
        List<InscripcionesModelo> inscripciones = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Mapea cada fila del resultado a un objeto InscripcionesModelo y agrégalo a la lista
                inscripciones.add(mapearDesdeResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener todas las inscripciones", e);
        }

        return inscripciones;
    }

    // Método para insertar una nueva inscripción
    @Override
    public void insertar(InscripcionesModelo inscripcion) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            // Establece los parámetros en la consulta SQL
            statement.setInt(1, inscripcion.getSocio().getN_socio());
            statement.setString(2, inscripcion.getExcursion().getCodigo());
            statement.setDate(3, java.sql.Date.valueOf(inscripcion.getFechaInscripcion()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar la inscripción", e);
        }
    }

    @Override
    public void actualizar(InscripcionesModelo entidad) {

    }

    @Override
    public void eliminar(String id) {

    }


    // Método para mapear una fila de ResultSet a un objeto InscripcionesModelo

    private InscripcionesModelo mapearDesdeResultSet(ResultSet resultSet) throws SQLException {
        try {
            int n_inscripcion = resultSet.getInt("n_inscripcion");

            // Obtener el objeto SocioModelo correspondiente al ID del socio en el ResultSet
            SociosModeloDAO socioModeloDAO = new SociosModeloDAO();
            SociosModelo socio = socioModeloDAO.obtenerPorId(resultSet.getString("socio"));

            // Obtener el objeto ExcursionModelo correspondiente al código de la excursión en el ResultSet
            ExcursionesModeloDAO excursionModeloDAO = new ExcursionesModeloDAO();
            ExcursionesModelo excursion = excursionModeloDAO.obtenerPorCodigo(resultSet.getString("excursion"));

            LocalDate fechaInscripcion = resultSet.getDate("fechaInscripcion").toLocalDate();

            return new InscripcionesModelo(n_inscripcion, socio, excursion, fechaInscripcion);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al mapear la inscripción desde ResultSet", e);
        }
    }



        //método para mapear un objeto inscripcionesmodeloDAO A UN PREPAREDsTATEMENT
    //Un PreparedStatement es una interfaz en Java que representa una sentencia SQL precompilada
    private void mapearAStatement(PreparedStatement statement, InscripcionesModelo inscripcion) throws SQLException {
        statement.setInt(1, inscripcion.getSocio().getN_socio());
        statement.setString(2, inscripcion.getExcursion().getCodigo());
        statement.setDate(3, java.sql.Date.valueOf(inscripcion.getFechaInscripcion()));

    }


}




