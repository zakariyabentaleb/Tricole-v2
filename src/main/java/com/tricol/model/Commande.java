package com.tricol.model;


import com.tricol.enums.StatutCommande;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "commandes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="dateCommande")
    private LocalDateTime date;

    private StatutCommande statut;
    private double montantTotal;

    @ManyToOne
    @JoinColumn(name="id_fournisseur",nullable = false)
    private Fournisseur fournisseur;


}
