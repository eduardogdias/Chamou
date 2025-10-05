package br.com.chamou.chamou.controller;

import br.com.chamou.chamou.dto.PainelDTO;
import br.com.chamou.chamou.entity.Painel;
import br.com.chamou.chamou.service.PainelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paineis")
public class PainelController {

    @Autowired
    private PainelService painelService;

    Painel painel = new Painel();

    @GetMapping
    public ResponseEntity<List<Painel>> list(){
        return ResponseEntity.ok(painelService.listAll());
    }

    @PostMapping
    public ResponseEntity<Painel> upload(@RequestBody PainelDTO painelDTO){
        painel = painelService.save(painelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(painel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Painel> update(@PathVariable Long id, @RequestBody PainelDTO painelDTO){
        painel = painelService.edit(id, painelDTO.getAtual());
        return ResponseEntity.ok(painel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Painel> delete(@PathVariable Long id){
        painel = painelService.delete(id);
        return ResponseEntity.ok(painel);
    }
}
