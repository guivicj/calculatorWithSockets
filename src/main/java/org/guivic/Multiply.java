package org.guivic;

public class Multiply implements OperationOption {
    @Override
    public int execute(int v1, int v2) {
        return v1 * v2;
    }
}
