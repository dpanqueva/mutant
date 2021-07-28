package com.meli.mutant.model.dto;

import com.meli.mutant.model.entity.StatsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author dpanquev
 * @version 2021-07-28
 * */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int countMutantDNA;
    private int countHumanDNA;
    private float ratio;

    public StatsDTO(StatsEntity statsEntity) {
        this.countHumanDNA = statsEntity.getCountHumanDNA();
        this.countMutantDNA = statsEntity.getCountMutantDNA();
        this.ratio = 0L;
    }

}
