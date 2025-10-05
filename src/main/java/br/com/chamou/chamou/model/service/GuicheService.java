package br.com.chamou.chamou.model.service;

import br.com.chamou.chamou.model.dto.GuicheDTO;
import br.com.chamou.chamou.model.entity.Guiche;
import br.com.chamou.chamou.model.entity.Senha;
import br.com.chamou.chamou.model.mapper.GuicheMapper;
import br.com.chamou.chamou.model.repository.GuicheRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("all")
public class GuicheService {

    @Autowired
    private GuicheRepository guicheRepository;

    @Autowired
    private GuicheMapper guicheMapper;

    public List<Guiche> listAll(){
        return guicheRepository.findAll();
    }

    public Guiche findById(Long id){
        Guiche guiche = guicheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guichê não encontrado"));
        return guiche;
    }

    public Guiche save(GuicheDTO guicheDTO){
        return guicheRepository.save(guicheMapper.toEntity(guicheDTO));
    }

    public Guiche edit(Long id, GuicheDTO guicheDTO){
        Guiche guiche = findById(id);
        guiche.setNumero(guicheDTO.getNumero());
        guiche.setAtendente(guicheDTO.getAtendente());
        guiche.setLivre(guicheDTO.getLivre());
        return guicheRepository.save(guiche);
    }

    public Guiche edit(Long id, Boolean livre){
        Guiche guiche = findById(id);
        guiche.setLivre(livre);
        return guicheRepository.save(guiche);
    }

    public Guiche delete(Long id){
        Guiche guiche = findById(id);
        guicheRepository.deleteById(id);
        return guiche;
    }


}
