package com.meli.mutant.model.service;

import com.meli.mutant.exception.BadRequestException;
import com.meli.mutant.exception.ForbiddenException;
import com.meli.mutant.model.dto.DNADto;
import com.meli.mutant.model.dto.ResponseDTO;
import com.meli.mutant.model.entity.DNAEntity;
import com.meli.mutant.model.repository.IDNAMutantRepository;
import com.meli.mutant.util.ConstantsDNA;
import com.meli.mutant.util.DNAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author dpanquev
 * @version 2021-07-28
 * */
@Service
@Transactional(noRollbackFor = {ForbiddenException.class})
public class DNAMutantServiceImpl implements IDNAMutantService {

    private static final Logger logger = LoggerFactory.getLogger(DNAMutantServiceImpl.class);

    @Autowired
    private IDNAMutantRepository dnaMutantRepository;

    @Autowired
    private DNAUtil dnaVerifyUtil;

    /**
     * Method to analyze mutant DNA
     * @param  adnMutant
     * @return
     * */
    @Override
    public ResponseDTO isMutant(DNADto adnMutant) {
        if (dnaMutantRepository.findByAdnMutant(adnMutant.toString()) == null) {
            boolean evaluationDna = dnaVerifyUtil.evaluationProcess(adnMutant);
            saveHumanMutant(adnMutant, evaluationDna);
            logger.info("Analysis has been saved");
            if (!evaluationDna) {
                throw new ForbiddenException("The evaluated dna belongs to a human");
            }
        } else {
            throw new BadRequestException("DNA previously evaluated");
        }

        return new ResponseDTO(200
                , DNAUtil.actualDate(ConstantsDNA.DATE_FORMAT_1)
                , "Successfully evaluated DNA");
    }

    /**
     * method to save the analyze mutant DNA
     * @param adnMutant
     * @param isMutant
     * @return
     * */
    void saveHumanMutant(DNADto adnMutant, boolean isMutant) {
        try {
            DNAEntity dnaEntity = new DNAEntity(adnMutant);
            dnaEntity.setSnMutant(isMutant);
            dnaMutantRepository.saveAndFlush(dnaEntity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

}
