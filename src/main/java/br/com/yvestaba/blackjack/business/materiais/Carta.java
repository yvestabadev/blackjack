package br.com.yvestaba.blackjack.business.materiais;

import java.util.List;

public record Carta(Naipe naipe, ValorCarta valor) {

    public List<Integer> getValores(){
        return valor.getValores();
    }

    @Override
    public String toString() {
        return valor.name() + " de " + naipe.name();
    }
}
