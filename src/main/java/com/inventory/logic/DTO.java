package com.inventory.logic;

public abstract class DTO {
    public abstract Object[] intoObjectArray();
    public abstract boolean followsBusinessRule(Object[] objects);
}
