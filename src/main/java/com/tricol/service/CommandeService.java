package com.tricol.service;

import com.tricol.dto.CommandeDTO;
import com.tricol.mapper.CommandeMapper;
import com.tricol.model.CommandeFournisseur;
import com.tricol.model.Fournisseur;
import com.tricol.repository.CommandeRepository;
import com.tricol.repository.FournisseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final FournisseurRepository fournisseurRepository;

    //getAll commandes with pagination sorting by id ascending
    public Page<CommandeDTO> getAllCommandes(int page,int nbrElement){
        Pageable pageable= PageRequest.of(page,nbrElement, Sort.by("id").ascending());
        Page<CommandeFournisseur> commandes=commandeRepository.findAll(pageable);
        return commandes.map(commandeMapper::toDTO);
    }
    //getByid
    public CommandeDTO getById(int id){
        CommandeFournisseur commande =commandeRepository.findById(id).orElseThrow(()->new RuntimeException("Commande not found"));
        return commandeMapper.toDTO(commande);
    }

    //save
    public CommandeDTO createCommande(CommandeDTO commandeDTO){
        CommandeFournisseur commande=commandeMapper.toEntity(commandeDTO);
        CommandeFournisseur saved=commandeRepository.save(commande);
        return commandeMapper.toDTO(saved);
    }

    //update
    public CommandeDTO updateCommande(int id,CommandeDTO commandeDTO){
        CommandeFournisseur commande=commandeRepository.findById(id).orElseThrow(()->new RuntimeException("Commande not found"));
        Fournisseur fournisseur=fournisseurRepository.findById(commandeDTO.getFournisseurId()).orElseThrow(()->new RuntimeException("fournisseur not found"));
        commande.setDate(commandeDTO.getDate());
        commande.setStatut(commandeDTO.getStatut());
        commande.setMontantTotal(commandeDTO.getMontantTotal());
        commande.setFournisseur(fournisseur);
        CommandeFournisseur saved=commandeRepository.save(commande);
        return commandeMapper.toDTO(saved);
    }

    //delete
    public void deleteCommande(int id){
        commandeRepository.deleteById(id);
    }
}