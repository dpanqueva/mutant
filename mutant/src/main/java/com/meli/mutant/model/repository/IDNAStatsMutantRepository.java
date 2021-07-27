package com.meli.mutant.model.repository;


import com.meli.mutant.model.entity.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDNAStatsMutantRepository extends JpaRepository<StatsEntity, Integer> {
}
