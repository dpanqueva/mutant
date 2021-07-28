package com.meli.mutant.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutant.exception.BadRequestException;
import com.meli.mutant.model.dto.DNADto;
import com.meli.mutant.model.dto.ResponseDTO;
import com.meli.mutant.model.service.IDNAMutantService;

/**
 * @author dpanquev
 * @version 2021-07-28
 * */
@RestController
@RequestMapping(("/api/V1"))
public class DNAMutantController {

    @Autowired
    private IDNAMutantService adnMutantService;

    /**
     * Base class for job with the mutant dna
     * @param adnMutantDTO
     * @param result
     * @return
     * */
    @PostMapping("/mutant")
    public ResponseEntity<ResponseDTO> mutant(@RequestBody DNADto adnMutantDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            throw new BadRequestException(errors.toString());
        }
        return new ResponseEntity<>(adnMutantService.isMutant(adnMutantDTO), HttpStatus.OK);
    }
}
