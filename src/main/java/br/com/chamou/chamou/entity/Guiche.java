package br.com.chamou.chamou.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
