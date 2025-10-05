package br.com.chamou.chamou.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PainelDTO {
    private Boolean atual;
    private Long senhaId;
    private Long guicheId;
}
