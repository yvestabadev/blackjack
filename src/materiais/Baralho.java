package materiais;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class Baralho {

    private final List<Carta> cartas;

    public Baralho(){
        cartas = new ArrayList<>();
        for(var naipe : EnumSet.allOf(Naipe.class)){
            for(var valor : EnumSet.allOf(ValorCarta.class)){
                cartas.add(new Carta(naipe, valor));
            }
        }
    }

    public void embaralhar(){
        Collections.shuffle(cartas);
    }

    public Carta puxar(){
        var carta = cartas.get(0);
        cartas.remove(0);
        return carta;
    }
}
