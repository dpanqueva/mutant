package com.meli.mutant.model.entity;


import com.meli.mutant.model.dto.DNADto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_adn")
public class DNAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "dna_mutant")
    private String adnMutant;

    @Column(name = "sn_mutant")
    private Boolean snMutant;

    public DNAEntity(DNADto dnaDto) {
        this.adnMutant = dnaDto.getDna().toString();
    }
}
