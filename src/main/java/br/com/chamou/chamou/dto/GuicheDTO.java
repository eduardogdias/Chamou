package br.com.chamou.chamou.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuicheDTO {
    private String numero;
    private String atendente;
    private Boolean livre;
}
