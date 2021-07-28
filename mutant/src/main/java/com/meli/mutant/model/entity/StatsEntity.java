package com.meli.mutant.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;


/**
 * @author dpanquev
 * @version 2021-07-28
 * */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Subselect("SELECT  COUNT(*) total_quantity ,(SELECT COUNT(mh.dna_mutant) FROM tbl_adn mh WHERE sn_mutant = TRUE) mutant ,(SELECT COUNT(mh.dna_mutant) FROM tbl_adn mh WHERE sn_mutant = FALSE) human FROM tbl_adn m")
public class StatsEntity {

    @Id
    @Column(name = "total_quantity")
    private int countTotalDNA;
    @Column(name = "mutant")
    private int countMutantDNA;
    @Column(name = "human")
    private int countHumanDNA;
}
