package br.com.chamou.chamou.mapper;


import br.com.chamou.chamou.dto.GuicheDTO;
import br.com.chamou.chamou.entity.Guiche;
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
