package com.meli.mutant.model.service;

import com.meli.mutant.model.dto.DNADto;
import com.meli.mutant.model.dto.ResponseDTO;

/**
 * @author dpanquev
 * @version 2021-07-28
 * */
public interface IDNAMutantService {

    public ResponseDTO isMutant(DNADto adnMutant);
}
