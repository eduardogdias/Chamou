package br.com.chamou.chamou.controller.web;

import br.com.chamou.chamou.model.entity.Chamada;
import br.com.chamou.chamou.model.service.ChamadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/web/chamadas")
public class ChamadaWebController {

    @Autowired
    private ChamadaService chamadaService;

    @GetMapping
    public String exibir(Model model){
        List<Chamada> chamadas = chamadaService.listAll();

        if (chamadas.isEmpty()) {
            model.addAttribute("ultima", new Chamada());
            model.addAttribute("finalizadas", Collections.emptyList());
        } else {
            for (Chamada c : chamadas) {
                if(c.getAtual()){
                    model.addAttribute("ultima", c);
                }
            }
            List<Chamada> finalizadas = chamadaService.getUltimas3Finalizadas();

            model.addAttribute("finalizadas", finalizadas);
        }
        return "chamada";
    }

    @GetMapping("/proximo")
    public String proximo(){
        chamadaService.autoSave();
        return "redirect:/web/chamadas";
    }

}
