package br.com.chamou.chamou.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_guiche")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Guiche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;
    private String atendente;
    private Boolean livre; //1 -> livre | 0 -> ocupado

}
