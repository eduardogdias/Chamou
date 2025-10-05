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
        guiche.setLivre(true);
        return guiche;
    }

    public GuicheDTO toDTO(Guiche guiche){
        GuicheDTO guicheDTO = new GuicheDTO();
        guicheDTO.setNumero(guiche.getNumero());
        guicheDTO.setAtendente(guiche.getAtendente());
        guicheDTO.setLivre(guiche.getLivre());
        return guicheDTO;
    }
}
