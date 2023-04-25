package br.com.yvestaba.blackjack.business.materiais;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public enum ValorCarta {

    AS(asList(1, 11)), DOIS(singletonList(2)), TRES(singletonList(3)), QUATRO(singletonList(4)), CINCO(singletonList(5)),
    SEIS(singletonList(6)), SETE(singletonList(7)), OITO(singletonList(8)), NOVE(singletonList(9)),
    DEZ(singletonList(10)), VALETE(singletonList(10)), DAMA(singletonList(10)), REI(singletonList(10));


    private final List<Integer> valores;
    ValorCarta(List<Integer> valores) {
        this.valores = valores;
    }

    public List<Integer> getValores() {
        return valores;
    }
}
