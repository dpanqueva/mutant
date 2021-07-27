package com.meli.mutant.model.service;

import com.meli.mutant.model.dto.DNADto;
import com.meli.mutant.model.dto.ResponseDTO;

public interface IDNAMutantService {

    public ResponseDTO isMutant(DNADto adnMutant);
}
