package br.com.chamou.chamou.model.mapper;


import br.com.chamou.chamou.model.dto.GuicheDTO;
import br.com.chamou.chamou.model.entity.Guiche;

import org.springframework.stereotype.Component;

@Component
public class GuicheMapper {

    public Guiche toEntity(GuicheDTO guicheDTO){
        Guiche guiche = new Guiche();
        guiche.setNumero(guicheDTO.getNumero());
        guiche.setAtendente(guicheDTO.getAtendente());
        guiche.setLivre(guicheDTO.getLivre());
        return guiche;
    }
}
