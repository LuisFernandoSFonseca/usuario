package com.lfsf.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "telefone")
@Builder
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero", length = 10)
    private String numero;
    @Column(name = "ddd", length = 3)
    private String ddd;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
//    private List<Endereco> enderecos;
}
