package br.com.chamou.chamou.model.service;

import br.com.chamou.chamou.model.dto.ChamadaDTO;
import br.com.chamou.chamou.model.entity.Chamada;
import br.com.chamou.chamou.model.entity.Guiche;
import br.com.chamou.chamou.model.entity.Senha;
import br.com.chamou.chamou.model.enums.SenhaTipoEnum;
import br.com.chamou.chamou.model.repository.ChamadaRepository;
import br.com.chamou.chamou.model.repository.GuicheRepository;
import br.com.chamou.chamou.model.repository.SenhaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("all")
public class ChamadaService {

    @Autowired
    private ChamadaRepository chamadaRepository;

    @Autowired
    private SenhaService senhaService;

    @Autowired
    private GuicheService guicheService;


    public void listAllChamada(){
        // define todas as chamadas anteriores do painel como 'antigas'
        List<Chamada> chamadas = listAll();
        for (Chamada c : chamadas) {
            edit(c.getId(), false);
        }
    }

    public List<Chamada> listAll(){
        return chamadaRepository.findAll();
    }

    public Chamada findById(Long id){
        Chamada chamada = chamadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamada não encontrada"));
        return chamada;
    }

    public Chamada save(ChamadaDTO chamadaDTO){

        Senha senha = senhaService.findById(chamadaDTO.getSenhaId());
        Guiche guiche = guicheService.findById(chamadaDTO.getGuicheId());

        Chamada chamada = new Chamada();
        chamada.setAtual(true);
        chamada.setData(new Date(System.currentTimeMillis()));
        chamada.setHora(new Time(System.currentTimeMillis()));
        chamada.setSenha(senha);
        chamada.setGuiche(guiche);
        
        // define senha como 'atendida' e guiche como 'ocupado'
        senhaService.edit(senha.getId(), true);
        guicheService.edit(guiche.getId(), false);

        listAllChamada();
        
        return chamadaRepository.save(chamada);
    }

    public Chamada autoSave(){ // define próxima senha e guichê disponíveis automaticamente

        List<Senha> senhas = senhaService.senhasNaoChamadas();
        List<Guiche> guiches = guicheService.guichesLivres();

        Chamada chamada = new Chamada();
        chamada.setAtual(true);
        chamada.setData(new Date(System.currentTimeMillis()));
        chamada.setHora(new Time(System.currentTimeMillis()));

        Senha senha = senhas.get(0);
        Guiche guiche = guiches.get(0);

        chamada.setSenha(senha);
        chamada.setGuiche(guiche);

        // define senha como 'atendida' e guiche como 'ocupado'
        senhaService.edit(senha.getId(), true);
        guicheService.edit(guiche.getId(), false);

        listAllChamada();

        return chamadaRepository.save(chamada);
    }

    public Chamada edit(Long id, Boolean atual){
        Chamada chamada = findById(id);
        chamada.setAtual(atual);
        return chamadaRepository.save(chamada);
    }

    public Chamada delete(Long id){
        Chamada chamada = findById(id);
        chamadaRepository.deleteById(id);
        return chamada;
    }

    public List<Chamada> getUltimas3Finalizadas() {
        return chamadaRepository.findTop3ByAtualFalseOrderByIdDesc();
    }

}
