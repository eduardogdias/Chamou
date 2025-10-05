package br.com.chamou.chamou.controller.web;

import br.com.chamou.chamou.model.dto.GuicheDTO;
import br.com.chamou.chamou.model.dto.SenhaDTO;
import br.com.chamou.chamou.model.entity.Guiche;
import br.com.chamou.chamou.model.entity.Senha;
import br.com.chamou.chamou.model.mapper.GuicheMapper;
import br.com.chamou.chamou.model.service.GuicheService;
import br.com.chamou.chamou.model.service.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/guiches")
public class GuicheWebController {

    @Autowired
    private GuicheService guicheService;

    @Autowired
    private GuicheMapper guicheMapper;

    @GetMapping
    public String listar(Model model){
        List<Guiche> guiches = guicheService.listAll();
        model.addAttribute("guiches", guiches);
        return "guiche";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("guiche", new Guiche());
        return "guiche-form";
    }

    @PostMapping
    public String salvar(Guiche guiche){
        GuicheDTO guicheDTO = guicheMapper.toDTO(guiche);

        if(guiche.getId() != null){
            guicheService.edit(guiche.getId(), guicheDTO);
        }
        else{
            guicheService.save(guicheDTO);
        }

        return "redirect:/web/guiches";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        Guiche guiche = guicheService.findById(id);
        model.addAttribute("guiche", guiche);
        return "guiche-form";
    }

    @GetMapping("/encerrar/{id}")
    public String encerrar(@PathVariable Long id){
        Guiche guiche = guicheService.findById(id);
        guicheService.edit(id, true);
        return "redirect:/web/guiches";
    }
}
