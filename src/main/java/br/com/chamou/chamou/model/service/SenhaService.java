package br.com.chamou.chamou.model.service;

import br.com.chamou.chamou.model.dto.SenhaDTO;
import br.com.chamou.chamou.model.entity.Senha;
import br.com.chamou.chamou.model.enums.SenhaTipoEnum;
import br.com.chamou.chamou.model.repository.SenhaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("all")
public class SenhaService {

    @Autowired
    private SenhaRepository senhaRepository;


    private String gerarSenha(SenhaTipoEnum tipo){
        String numeroSenha;
        int num = (int) (Math.random() * 900) + 100; // números entre 100 e 999

        if(tipo == SenhaTipoEnum.COMUM){
            numeroSenha = "C" + num;
        } else {
            numeroSenha = "P" + num;
        }
        return numeroSenha;
    }

    public List<Senha> senhasNaoChamadas(){

        List<Senha> senhasCadastradas = new ArrayList<>(),
                senhasComuns = new ArrayList<>(),
                senhasPreferenciais = new ArrayList<>(),
                senhas = new ArrayList<>();

        senhasCadastradas = listAll();

        for (Senha s : senhasCadastradas) {
            if(!s.getAtendida()){       // se a senha não foi atendida, vamos separar entre Comum e Preferêncial
                if(s.getTipo() == SenhaTipoEnum.COMUM){
                    senhasComuns.add(s);
                } else {
                    senhasPreferenciais.add(s);
                }
            }

        }
        //senhas preferênciais são chamadas primeiro
        senhas.addAll(senhasPreferenciais);
        senhas.addAll(senhasComuns);
        System.out.println("Senhas: " + senhas);
        return senhas;
    }

    public List<Senha> listAll(){
        return senhaRepository.findAll();
    }

    public Senha findById(Long id){
        Senha senha = senhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Senha não encontrada"));
        return senha;
    }

    public Senha save(SenhaDTO senhaDTO){
        Senha senha = new Senha();

        senha.setTipo(senhaDTO.getTipo());
        senha.setNumero(gerarSenha(senhaDTO.getTipo()));
        senha.setDataEmissao(new java.sql.Date(System.currentTimeMillis())); // Data e hora atuais
        senha.setHoraEmissao(new java.sql.Time(System.currentTimeMillis()));
        senha.setAtendida(false);

        return senhaRepository.save(senha);
    }

    public Senha edit(Long id, Boolean atendida){
        Senha senha = findById(id);
        senha.setAtendida(atendida);
        return senhaRepository.save(senha);
    }

    public Senha delete(Long id){
        Senha senha = findById(id);
        senhaRepository.deleteById(id);
        return senha;
    }


}
