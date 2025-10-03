package br.com.chamou.chamou.service;

import br.com.chamou.chamou.entity.Senha;
import br.com.chamou.chamou.repository.SenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SenhaService {

    @Autowired
    private SenhaRepository senhaRepository;

    public List<Senha> listAll(){
        return senhaRepository.findAll();
    }

    public void save(Senha senha){
        senhaRepository.save(senha);
    }

    public void edit(Long id, Senha senha){
        Optional<Senha> entity = senhaRepository.findById(id);

        if(entity.isPresent()){ 
            entity.get().setAtendida(senha.getAtendida());
            senhaRepository.save(entity.get());
        }
    }

    public void delete(Long id){
        Optional<Senha> entity = senhaRepository.findById(id);
        if(entity.isPresent()){
            senhaRepository.deleteById(id);
        }
    }


}
