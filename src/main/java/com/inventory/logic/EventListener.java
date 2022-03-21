package com.inventory.logic;

/**
 * The observer interface for being notified for updates
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public interface EventListener  {

    /**
     * Update self when notified
     * @param updateType The type of update {@link UpdateType}
     * @param values The values after update
     * @param daoName Name of persistence service the update refers to
     */
    void update(UpdateType updateType, Object[] values, String daoName);
}
