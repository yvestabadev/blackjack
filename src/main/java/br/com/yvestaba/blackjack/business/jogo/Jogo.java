package br.com.yvestaba.blackjack.business.jogo;

import br.com.yvestaba.blackjack.business.materiais.Baralho;
import br.com.yvestaba.blackjack.business.regras.StatusJogo;
import br.com.yvestaba.blackjack.business.regras.StatusMao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Jogo {

    private final Apostador apostador = new Apostador();
    private final Iterator<Jogador> jogadorIterator;
    private final List<Jogador> jogadores;
    private Baralho baralho = new Baralho();
    private Jogador jogadorDaVez;

    public Jogo(List<Jogador> jogadores){
        this.jogadorIterator = jogadores.iterator();
        this.baralho.embaralhar();
        this.jogadorDaVez = jogadorIterator.next();
        this.jogadores = jogadores;
    }

    /**
     * Prossegue com o jogo caso o jogador da vez queira puxar ou parar.
     * @return se tem um próximo passo
     */
    public boolean proximoPasso(boolean puxar){
        if(puxar){
            jogadorDaVez.puxar(baralho);
            return true;
        }
        if(jogadorIterator.hasNext()){
            jogadorDaVez = jogadorIterator.next();
            return true;
        }
        if(jogadorDaVez.equals(apostador)){
            return false;
        }
        jogadorDaVez = apostador;
        apostador.iniciarPuxadas(baralho);
        if(StatusMao.getStatusMaoByJogador(apostador) == StatusMao.BUST){
            return false;
        }
        return true;
    }

    public Map<StatusJogo, List<Jogador>> fimDeJogo(){
        return jogadores.stream().collect(Collectors.groupingBy(j -> StatusJogo.getStatusJogador(j, apostador)));
    }
}
