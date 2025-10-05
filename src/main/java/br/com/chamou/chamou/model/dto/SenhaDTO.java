package br.com.chamou.chamou.model.dto;

import br.com.chamou.chamou.model.enums.SenhaTipoEnum;
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
