package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.DataBaseConnection;
import modelo.InscripcionesModelo;

public class InscripcionesModeloDAO implements GenericDAO<InscripcionesModelo, String> {

    // Constantes para consultas SQL
    private static final String SELECT_ALL = "SELECT * FROM inscripciones";
    private static final String INSERT = "INSERT INTO inscripciones (socio_id, excursion_id, fecha_inscripcion) VALUES (?, ?, ?)";


    // Método para obtener una inscripción por su ID
    @Override
    public InscripcionesModelo obtenerPorId(String id) {
        // Implementa la lógica para obtener una inscripción por su ID
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
        // Implementa la lógica para mapear una fila de ResultSet a un objeto InscripcionesModelo
    }

}
