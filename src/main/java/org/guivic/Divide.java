package org.guivic;

public class Divide implements OperationOption {
    @Override
    public int execute(int v1, int v2) {
        if (v2 == 0) {
            System.out.println("No puedes dividir entre 0");
        }
        return v1 / v2;
    }
}
