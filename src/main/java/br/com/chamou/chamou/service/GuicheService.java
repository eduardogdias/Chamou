package br.com.chamou.chamou.service;

import br.com.chamou.chamou.entity.Guiche;
import br.com.chamou.chamou.repository.GuicheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuicheService {

    @Autowired
    private GuicheRepository guicheRepository;

    public List<Guiche> listAll(){
        return guicheRepository.findAll();
    }

    public void save(Guiche guiche){
        guicheRepository.save(guiche);
    }

    public void edit(Long id, Guiche guiche){
        Optional<Guiche> entity = guicheRepository.findById(id);

        if(entity.isPresent()){
            entity.get().setAtendente(guiche.getAtendente());
            entity.get().setStatus(guiche.getStatus());
            guicheRepository.save(entity.get());
        }
    }

    public void delete(Long id){
        Optional<Guiche> entity = guicheRepository.findById(id);
        if(entity.isPresent()){
            guicheRepository.deleteById(id);
        }
    }


}
