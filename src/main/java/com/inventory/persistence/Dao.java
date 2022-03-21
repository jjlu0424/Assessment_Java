package com.inventory.persistence;

import com.inventory.domain.DTO;

import java.util.ArrayList;

/**
 * Data Access Object for CRUD operations
 * @param <T> The type of DTO {@link com.inventory.domain.DTO}
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public interface Dao <T extends DTO> {

    /**
     * Configures with database path
     * @param dbPath The path or url of the Database
     */
    boolean configure(String dbPath);

    /**
     * Get a single record with specified id
     * @param id The id of the sought resource
     * @return T The result of the query
     */
    T get(int id);

    /**
     * Get all records with specified id
     * @return ArrayList<T> The result of query in an ArrayList
     */
    ArrayList<T> getAll();

    /**
     * Inserts a record into the database
     * @param t The record to be inserted
     * @return boolean Whether the insertion is successful
     */
    boolean insert(T t);

    /**
     * Update a record in the database
     * @param t The record to be updated
     * @return boolean Whether the update is successful
     */
    boolean update(T t);

    /**
     * Delete a record from the database
     * @param id The id of the record to be deleted
     * @return boolean Whether the deletion is successful
     */
    boolean delete(int id);

}
