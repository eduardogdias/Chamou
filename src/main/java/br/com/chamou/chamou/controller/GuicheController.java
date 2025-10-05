package br.com.chamou.chamou.controller;

import br.com.chamou.chamou.dto.GuicheDTO;
import br.com.chamou.chamou.entity.Guiche;
import br.com.chamou.chamou.service.GuicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guiches")
public class GuicheController {

    @Autowired
    private GuicheService guicheService;

    Guiche guiche = new Guiche();

    @GetMapping
    public ResponseEntity<List<Guiche>> list(){
        return ResponseEntity.ok(guicheService.listAll());
    }

    @PostMapping
    public ResponseEntity<Guiche> upload(@RequestBody GuicheDTO guicheDTO){
        guiche = guicheService.save(guicheDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(guiche);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guiche> update(@PathVariable Long id, @RequestBody GuicheDTO guicheDTO){
        guiche = guicheService.edit(id, guicheDTO);
        return ResponseEntity.ok(guiche);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Guiche> delete(@PathVariable Long id){
        guiche = guicheService.delete(id);
        return ResponseEntity.ok(guiche);
    }
}
