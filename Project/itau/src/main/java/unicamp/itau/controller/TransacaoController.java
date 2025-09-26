package unicamp.itau.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import unicamp.itau.model.EstatisticaResponse;
import unicamp.itau.model.Transacao;
import unicamp.itau.service.TransacaoService;
import java.time.OffsetDateTime;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaResponse> getEstatistica(){
        EstatisticaResponse stats = service.estatistica();
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> adicionarTransacao(@RequestBody valor) {
        try {
            if (transacao == null) {
                return ResponseEntity.badRequest().build(); // 400
            }

            // Validar campos obrigatórios
            if (transacao.getValor() < 0 || transacao.getDataHora() == null) {
                return ResponseEntity.unprocessableEntity().build(); // 422
            }

            // Não aceitar transações no futuro
            if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
                return ResponseEntity.unprocessableEntity().build(); // 422
            }

            // Se passar nas validações, adiciona na pilha
            service.PostTransacao(transacao);

            return ResponseEntity.status(HttpStatus.CREATED).build(); // 201
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // 400
        }
    }

    @DeleteMapping("transacao")
    public ResponseEntity<Void> deleteTransacao(){
        service.delete();
        return ResponseEntity.ok().build(); // 200 OK
    }





}
