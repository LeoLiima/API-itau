package unicamp.itau.model;

import java.time.OffsetDateTime;
import java.time.Duration;

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

    public String estatistica(){
        OffsetDateTime agora = OffsetDateTime.now();
        int contador = 0;
        double valorTotal = 0;
        double min = 0;
        double max = 0;

        Transacao[] auxiliar = new Transacao[topo];

        int index = 0;
        Transacao trans;

        while ((trans = this.desempilhar()) != null) {
            OffsetDateTime dataHora = trans.getDataHora();

            long segundos = Duration.between(dataHora, agora).getSeconds();

            if (segundos == 60) {
                contador++;
                valorTotal += trans.valor;
                if(trans.valor > max){
                    max = trans.valor;

                }
                if(trans.valor < min){
                    min = trans.valor;
                }


            }

            auxiliar[index++] = trans;
        }


        for (int i = index - 1; i >= 0; i--) {
            this.empilhar(auxiliar[i]);
        }
        double avg = valorTotal / contador;

        return String.format(
                "{ \"count\": %d, \"sum\": %.2f, \"avg\": %.2f, \"min\": %.2f, \"max\": %.2f }",
                contador, valorTotal, avg, min, max
        );


    }

    public void delete(){
        while(transacoes[topo] != null) {
            desempilhar();
        }
    }

}
