package com.tricol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tricol.enums.StatutCommande;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDTO {
    private int id;
    private LocalDateTime dateCommande;
    private StatutCommande statut;

    // allow incoming montantTotal
    private Double montantTotal;

    // keep fournisseurId for service layer lookups
    private int fournisseurId;

    // accept nested fournisseur object in incoming JSON (e.g. "fournisseur": {"id":1})
    private FournisseurDTO fournisseur;

    // When a nested fournisseur is provided, extract its id into fournisseurId
    @JsonProperty("fournisseur")
    public void setFournisseur(FournisseurDTO fournisseur) {
        this.fournisseur = fournisseur;
        if (fournisseur != null) {
            this.fournisseurId = fournisseur.getId();
        }
    }
}