package br.com.chamou.chamou.service;

import br.com.chamou.chamou.dto.PainelDTO;
import br.com.chamou.chamou.entity.Guiche;
import br.com.chamou.chamou.entity.Painel;
import br.com.chamou.chamou.entity.Senha;
import br.com.chamou.chamou.repository.GuicheRepository;
import br.com.chamou.chamou.repository.PainelRepository;
import br.com.chamou.chamou.repository.SenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("all")
public class PainelService {

    @Autowired
    private PainelRepository painelRepository;

    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private GuicheRepository guicheRepository;

    public List<Painel> listAll(){
        return painelRepository.findAll();
    }

    public Painel findById(Long id){
        Painel painel = painelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Painel não encontrado"));
        return painel;
    }

    public Painel save(PainelDTO painelDTO){

        Senha senha = senhaRepository.findById(painelDTO.getSenhaId())
                .orElseThrow(() -> new RuntimeException("Senha não encontrada"));

        Guiche guiche = guicheRepository.findById(painelDTO.getGuicheId())
                .orElseThrow(() -> new RuntimeException("Guichê não encontrado"));

        Painel painel = new Painel();
        painel.setAtual(true);
        painel.setDataChamada(new java.sql.Date(System.currentTimeMillis()));
        painel.setHoraChamada(new java.sql.Time(System.currentTimeMillis()));
        painel.setSenha(senha);
        painel.setGuiche(guiche);
        
        // define senha como 'atendida' e guiche como 'ocupado' 
        senha.setAtendida(true);
        guiche.setLivre(false);
        senhaRepository.save(senha);
        guicheRepository.save(guiche);
        
        // define todas as chamadas anteriores do painel como 'antigas' 
        List<Painel> paineis = listAll();
        for (Painel p : paineis) {
			edit(p.getId(), false);
		}
        
        return painelRepository.save(painel);
    }

    public Painel edit(Long id, Boolean atual){
        Painel painel = findById(id);
        painel.setAtual(atual);
        return painelRepository.save(painel);
    }

    public Painel delete(Long id){
        Painel painel = findById(id);
        painelRepository.deleteById(id);
        return painel;
    }


}
