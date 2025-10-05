package br.com.chamou.chamou.model.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChamadaDTO {
    private Boolean atual;
    private Long senhaId;
    private Long guicheId;
}
