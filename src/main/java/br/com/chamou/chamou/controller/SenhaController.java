package br.com.chamou.chamou.controller;

import br.com.chamou.chamou.dto.SenhaDTO;
import br.com.chamou.chamou.entity.Senha;
import br.com.chamou.chamou.service.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/senhas")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    Senha senha = new Senha();

    @GetMapping
    public ResponseEntity<List<Senha>> list(){
        return ResponseEntity.ok(senhaService.listAll());
    }

    @PostMapping
    public ResponseEntity<Senha> upload(@RequestBody SenhaDTO senhaDTO){
        senha = senhaService.save(senhaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(senha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Senha> update(@PathVariable Long id, @RequestBody SenhaDTO senhaDTO){
        senha = senhaService.edit(id, senhaDTO.getAtendida());
        return ResponseEntity.ok(senha);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Senha> delete(@PathVariable Long id){
        senha = senhaService.delete(id);
        return ResponseEntity.ok(senha);
    }
}
