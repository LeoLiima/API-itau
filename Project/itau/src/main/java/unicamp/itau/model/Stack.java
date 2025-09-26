package unicamp.itau.model;

import java.time.OffsetDateTime;
import java.time.Duration;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Stack {

    private Transacao[] transacoes;
    private int topo = 0;

    public Stack(int size){
        transacoes = new Transacao[size];
    }

    public void empilhar(Transacao trans){
        transacoes[topo] = trans;
        topo++;
    }

    public Transacao desempilhar(){
        if (topo == 0) return null;
        topo--;
        Transacao trans = transacoes[topo];
        transacoes[topo] = null;
        return trans;
    }


    public void delete(){
        while(transacoes[topo] != null) {
            desempilhar();
        }
    }

}
