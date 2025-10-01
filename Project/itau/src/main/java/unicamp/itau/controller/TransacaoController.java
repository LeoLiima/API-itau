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
import java.util.DoubleSummaryStatistics;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController

public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaResponse> getEstatistica(DoubleSummaryStatistics estatisticas){
        EstatisticaResponse stats = new EstatisticaResponse(estatisticas);
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> adicionarTransacao(@RequestBody Transacao transacao) {
        try {
            if (transacao == null) {
                return ResponseEntity.badRequest().build(); // 400
            }

           
            if (transacao.getValor() < 0 || transacao.getDataHora() == null) {
                return ResponseEntity.unprocessableEntity().build(); // 422
            }

            if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
                return ResponseEntity.unprocessableEntity().build(); // 422
            }

           
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
