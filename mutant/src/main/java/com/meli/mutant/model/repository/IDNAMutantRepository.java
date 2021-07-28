package com.meli.mutant.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.mutant.model.entity.DNAEntity;


/**
 * @author dpanquev
 * @version 2021-07-28
 * */
@Repository
public interface IDNAMutantRepository extends JpaRepository<DNAEntity, String> {

    DNAEntity findByAdnMutant(String dnaMutant);

}
