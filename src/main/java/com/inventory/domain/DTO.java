package com.inventory.domain;

/**
 * Data Transfer Objects for transferring between program data
 * and SQL data in the database
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public abstract class DTO {
    /**
     * Turns a object into Object array to be displayed in JTable class
     * @return Object[] The object array containing object information
     */
    public abstract Object[] intoObjectArray();

    /**
     * Determines whether this instance follows the business rule
     * @return boolean The validation result
     */
    public abstract boolean followsBusinessRule();
}
