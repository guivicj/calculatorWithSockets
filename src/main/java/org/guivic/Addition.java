package org.guivic;

public class Addition implements OperationOption {
    @Override
    public int execute(int v1, int v2) {
        return v1 + v2;
    }
}
