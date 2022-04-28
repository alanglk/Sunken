package org.modelo.tienda;

public enum ETipoArmamento {
    BOMBATAP("Bomba Tap"),
    RADAR3X3("Radar 3x3"),
    ;
    private String tipo;

    ETipoArmamento(String s) {
        this.tipo=s;
    }

    public String getTipo() {
        return tipo;
    }
}
