package com.inventory.logic;

/**
 * The publisher that notifies its observer {@link com.inventory.logic.EventListener} for updates
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public interface SubjectBase {
    /**
     * Notifies the subscibers for updates
     *
     * @param eventListener The subscriber of the current subject
     */
    void register(EventListener eventListener);

    /**
     * Notify subscribers for updates
     *
     * @param updateType The type of update {@link UpdateType}
     * @param values     The values after update
     * @param daoName    Name of persistence service the update refers to
     */
    public void publish(UpdateType updateType, Object[] values, String daoName);
}