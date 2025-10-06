package br.com.chamou.chamou.controller.api;

import br.com.chamou.chamou.model.dto.ChamadaDTO;
import br.com.chamou.chamou.model.entity.Chamada;
import br.com.chamou.chamou.model.service.ChamadaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamadas")
public class ChamadaController {

    @Autowired
    private ChamadaService chamadaService;

    Chamada chamada = new Chamada();

    @GetMapping
    public ResponseEntity<List<Chamada>> list(){
        return ResponseEntity.ok(chamadaService.listAll());
    }

    @PostMapping
    public ResponseEntity<Chamada> upload(@RequestBody(required = false) ChamadaDTO chamadaDTO){
        if(chamadaDTO == null){
            chamada = chamadaService.autoSave();
        } else {
            chamada = chamadaService.save(chamadaDTO);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(chamada);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Chamada> update(@PathVariable Long id, @RequestBody ChamadaDTO chamadaDTO){
        chamada = chamadaService.edit(id, chamadaDTO.getAtual());
        return ResponseEntity.ok(chamada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Chamada> delete(@PathVariable Long id){
        chamada = chamadaService.delete(id);
        return ResponseEntity.ok(chamada);
    }
}
