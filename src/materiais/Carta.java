package materiais;

import java.util.List;

public record Carta(Naipe naipe, ValorCarta valor) {

    public List<Integer> getValores(){
        return valor.getValores();
    }
}
