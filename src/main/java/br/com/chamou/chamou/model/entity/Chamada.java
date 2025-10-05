package br.com.chamou.chamou.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "tb_chamada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean atual; // 1 -> atual | 0 -> antiga
    private Date data;
    private Time hora;

    @OneToOne
    @JoinColumn(name = "senha_fk", nullable = false)
    private Senha senha;

    @ManyToOne
    @JoinColumn(name = "guiche_fk", nullable = false)
    private Guiche guiche;
}
