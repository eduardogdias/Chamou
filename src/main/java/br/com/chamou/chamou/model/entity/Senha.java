package br.com.chamou.chamou.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

import br.com.chamou.chamou.model.enums.SenhaTipoEnum;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataEmissao;

    @DateTimeFormat(pattern = "HH:mm")
    private Time horaEmissao;
    private Boolean atendida;

}


