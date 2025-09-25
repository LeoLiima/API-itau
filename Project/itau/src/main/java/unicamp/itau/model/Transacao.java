package unicamp.itau.model;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
public class Transacao {

    double valor;
    OffsetDateTime dataHora;

    public Transacao(double valor){
        this.valor = valor;
        dataHora = OffsetDateTime.now();
    }

    public OffsetDateTime getDataHora(){
        return dataHora;
    }
    public double getValor(){
        return valor;
    }


}
