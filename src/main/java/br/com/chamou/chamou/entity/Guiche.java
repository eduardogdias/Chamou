package br.com.chamou.chamou.entity;

import br.com.chamou.chamou.enums.GuicheStatusEnum;
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
    private String atendente;

    @Enumerated(EnumType.STRING)
    private GuicheStatusEnum status;

    @OneToMany(mappedBy = "guiche")
    private List<Painel> chamadas;
}
