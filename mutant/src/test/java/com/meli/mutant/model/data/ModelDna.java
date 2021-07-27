package com.meli.mutant.model.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.meli.mutant.model.dto.DNADto;
import com.meli.mutant.model.dto.ResponseDTO;
import com.meli.mutant.model.dto.StatsDTO;
import com.meli.mutant.model.entity.StatsEntity;
import com.meli.mutant.util.ConstantsDNA;
import com.meli.mutant.util.DNAUtil;

@Component
public class ModelDna {

	public DNADto createDTODNA() {
		return new DNADto(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));
	}

	public DNADto createDTODNAOpc() {
		DNADto dnaDto = new DNADto();
		dnaDto.setDna(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));
		return dnaDto;
	}

	public DNADto createDTODNAFailed() {
		return new DNADto(Arrays.asList("STGCGA", "CAGTGC", "TTATGT", "ACAACG", "CCFCTA", "TCACTG"));
	}

	public DNADto createDTODNAMat() {
		return new DNADto(Arrays.asList("ATGA", "CATGC", "TATGT", "AGAAGG", "CCCCTA", "TCTG"));
	}

	public ResponseDTO responseFailedDTO() {
		return new ResponseDTO(403, DNAUtil.actualDate(ConstantsDNA.DATE_FORMAT_1), "DNA previously evaluated");
	}

	public ResponseDTO responseFailedDTOBR() {
		return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), DNAUtil.actualDate(ConstantsDNA.DATE_FORMAT_1),
				"DNA previously evaluated");
	}

	public StatsDTO responseStats() {
		StatsDTO statsDto = new StatsDTO();
		statsDto.setCountHumanDNA(100);
		statsDto.setCountMutantDNA(40);
		statsDto.setRatio(statsDto.getCountMutantDNA() / statsDto.getCountHumanDNA());
		return statsDto;
	}

	public List<StatsEntity> responseStatsEnti() {
		StatsEntity statsEntity = new StatsEntity();
		List<StatsEntity> lstStats = new ArrayList<>();
		statsEntity.setCountHumanDNA(100);
		statsEntity.setCountMutantDNA(40);
		statsEntity.setCountTotalDNA(1000);
		lstStats.add(statsEntity);
		return lstStats;
	}
	
	public List<StatsEntity> responseStatsRatio() {
		StatsEntity statsEntity = new StatsEntity();
		List<StatsEntity> lstStats = new ArrayList<>();
		statsEntity.setCountHumanDNA(0);
		statsEntity.setCountMutantDNA(0);
		statsEntity.setCountTotalDNA(0);
		lstStats.add(statsEntity);
		return lstStats;
	}
}
