package br.com.chamou.chamou.entity;

import br.com.chamou.chamou.enums.PainelStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "tb_painel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Painel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PainelStatusEnum status;

    private Date dataChamada;
    private Time horaChamada;

    @OneToOne
    @JoinColumn(name = "senha_fk")
    private Senha senha;

    @ManyToOne
    @JoinColumn(name = "guiche_fk")
    private Guiche guiche;
}
