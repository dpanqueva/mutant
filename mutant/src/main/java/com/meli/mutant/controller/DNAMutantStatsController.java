package com.meli.mutant.controller;

import com.meli.mutant.model.dto.StatsDTO;
import com.meli.mutant.model.service.IStatsMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author dpanquev
 * @version 2021-07-28
 * */
@RestController
@RequestMapping(("/api/V1"))
public class DNAMutantStatsController {

    @Autowired
    private IStatsMutantService statsMutantService;

    /**
     * Base class for job with the mutant stats
     * @return
     * */
    @GetMapping("/stats")
    public ResponseEntity<StatsDTO>stats(){
        return new ResponseEntity<>(statsMutantService.stats(), HttpStatus.OK);
    }
}
