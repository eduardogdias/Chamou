package br.com.chamou.chamou.service;

import br.com.chamou.chamou.entity.Painel;
import br.com.chamou.chamou.repository.PainelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PainelService {

    @Autowired
    private PainelRepository painelRepository;

    public List<Painel> listAll(){
        return painelRepository.findAll();
    }

    public void save(Painel painel){
        painelRepository.save(painel);
    }

    public void edit(Long id, Painel painel){
        Optional<Painel> entity = painelRepository.findById(id);

        if(entity.isPresent()){
            entity.get().setStatus(painel.getStatus());
            painelRepository.save(entity.get());
        }

    }

    public void delete(Long id){
        Optional<Painel> entity = painelRepository.findById(id);
        if(entity.isPresent()){
            painelRepository.deleteById(id);
        }
    }


}
