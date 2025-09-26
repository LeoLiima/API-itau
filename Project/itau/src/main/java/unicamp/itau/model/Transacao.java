package unicamp.itau.model;
import lombok.Getter;
import lombok.ToString;
import java.time.OffsetDateTime;

@Getter
@ToString
public class Transacao {

    double valor;
    OffsetDateTime dataHora;

    public Transacao(double valor){
        this.valor = valor;
        dataHora = OffsetDateTime.now();
    }

    public double getValor() {
        return this.getValor();
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
