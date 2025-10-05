package br.com.chamou.chamou.controller.web;

import br.com.chamou.chamou.model.dto.SenhaDTO;
import br.com.chamou.chamou.model.entity.Senha;
import br.com.chamou.chamou.model.service.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/senhas")
public class SenhaWebController {

    @Autowired
    private SenhaService senhaService;

    @GetMapping
    public String cadastrar(Model model){
        model.addAttribute("senhaDTO", new SenhaDTO());
        return "senha";
    }

    @PostMapping
    public String salvar(Model model, SenhaDTO senhaDTO){
        Senha senha = senhaService.save(senhaDTO);
        model.addAttribute("senhaDTO", new SenhaDTO());
        model.addAttribute("senha", senha);
        return "senha";
    }
}
