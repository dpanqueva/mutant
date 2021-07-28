package com.meli.mutant.model.service;

import com.meli.mutant.model.dto.StatsDTO;
import com.meli.mutant.model.repository.IDNAStatsMutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatsMutantService implements IStatsMutantService {

    @Autowired
    private IDNAStatsMutantRepository dnaStatsMutantRepository;

    @Override
    public StatsDTO stats() {
        StatsDTO statsDTO = new StatsDTO(dnaStatsMutantRepository.findAll().get(0));
        if (statsDTO.getCountMutantDNA() > 0 && statsDTO.getCountHumanDNA() > 0) {
            float ratio = (float) statsDTO.getCountMutantDNA() / statsDTO.getCountHumanDNA();
            statsDTO.setRatio(ratio);
        }
        return statsDTO;
    }
}
