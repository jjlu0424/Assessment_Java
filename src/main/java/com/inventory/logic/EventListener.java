package com.inventory.logic;

public interface EventListener  {
    void update(UpdateType updateType, Object[] values, String daoName);
}
