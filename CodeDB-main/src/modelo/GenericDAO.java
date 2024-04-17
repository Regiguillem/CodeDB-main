package modelo;

import java.util.List;

//Esto es una clase genérica que proporciona operaciones genéricas de acceso a datos para interactuar con la base de datos
public interface GenericDAO<T, K> {

    // Método para obtener un objeto por su ID
    T obtenerPorId(K id);

    // Método para obtener todos los objetos de la base de datos
    List<T> obtenerTodos();

    // Método para insertar un nuevo objeto en la base de datos
    void insertar(T entidad);

    // Método para actualizar un objeto existente en la base de datos
    void actualizar(T entidad);

    // Método para eliminar un objeto por su ID
    void eliminar(K id);
}

