package br.com.chamou.chamou.dto;

import br.com.chamou.chamou.enums.SenhaTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SenhaDTO {
    private SenhaTipoEnum tipo;
    private Boolean atendida;
}
