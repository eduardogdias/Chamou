package br.com.chamou.chamou.entity;

import br.com.chamou.chamou.enums.SenhaTipoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "tb_senha")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;

    @Enumerated(EnumType.STRING)
    private SenhaTipoEnum tipo;
    private Date dataEmissao;
    private Time horaEmissao;
    private Boolean atendida;

}


