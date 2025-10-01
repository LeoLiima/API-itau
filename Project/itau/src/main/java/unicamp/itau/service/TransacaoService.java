package unicamp.itau.service;
import unicamp.itau.model.Transacao;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TransacaoService {

      private ConcurrentLinkedQueue<Transacao> transacoes = new ConcurrentLinkedQueue<>();


    public void PostTransacao(Transacao trans){
        transacoes.add(trans);
    }

    //metodo delete
    public void delete(){
       transacoes.clear();
    }


   public DoubleSummaryStatistics getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime limite = now.minusSeconds(60);

        DoubleSummaryStatistics stats = new DoubleSummaryStatistics();

        for (Transacao trans : transacoes) {
            if (trans.getDataHora().isAfter(limite)) {
                stats.accept(trans.getValor());
            }
        }

        return stats;
    }

}
