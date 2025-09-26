package unicamp.itau.service;
import unicamp.itau.model.Stack;
import unicamp.itau.model.Transacao;
import unicamp.itau.model.EstatisticaResponse;
import java.time.OffsetDateTime;
import java.time.Duration;

public class TransacaoService {

    Stack stack = new Stack(100);

    //Criar o objeto com os itens passado como parâmetro

    public void PostTransacao(double valor){
        Transacao trans = new Transacao(valor);
        stack.empilhar(trans);
    }

    //metodo delete
    public void delete(){
        stack.delete();
    }


    public EstatisticaResponse estatistica() {
        OffsetDateTime agora = OffsetDateTime.now();
        int contador = 0;
        double valorTotal = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        Transacao[] auxiliar = new Transacao[100]; // tamanho da pilha
        int index = 0;
        Transacao trans;

        while ((trans = this.stack.desempilhar()) != null
                && Duration.between(trans.getDataHora(), agora).getSeconds() < 60) {

            contador++;
            valorTotal += trans.getValor();

            if (trans.getValor() > max) {
                max = trans.getValor();
            }
            if (trans.getValor() < min) {
                min = trans.getValor();
            }

            auxiliar[index++] = trans;
        }

        for (int i = index - 1; i >= 0; i--) {
            this.stack.empilhar(auxiliar[i]);
        }

        double avg = (contador > 0) ? valorTotal / contador : 0;

        if (contador == 0) {
            min = 0;
            max = 0;
        }

        return new EstatisticaResponse(contador, valorTotal, avg, min, max);
    }
}
