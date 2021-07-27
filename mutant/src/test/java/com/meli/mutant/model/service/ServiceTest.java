package com.meli.mutant.model.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meli.mutant.model.data.ModelDna;
import com.meli.mutant.model.dto.DNADto;
import com.meli.mutant.model.dto.ResponseDTO;
import com.meli.mutant.model.dto.StatsDTO;
import com.meli.mutant.model.entity.DNAEntity;
import com.meli.mutant.model.entity.StatsEntity;
import com.meli.mutant.model.repository.IDNAMutantRepository;
import com.meli.mutant.model.repository.IDNAStatsMutantRepository;
import com.meli.mutant.util.DNAUtil;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ServiceTest.class);

	@InjectMocks
	private DNAMutantServiceImpl dnaMutantService;

	@InjectMocks
	private StatsMutantService statsMutantService;

	@Mock
	private IDNAMutantService idnaMutantService;

	@Mock
	private IDNAMutantRepository dnaMutantRepository;

	@Mock
	private DNAUtil dnaVerifyUtil;

	@Mock
	private IDNAStatsMutantRepository adnStatsMutantRepository;

	private ResponseDTO responseFail;
	private DNADto dnaDto;
	private DNAEntity dnaEntityFail;
	private DNAEntity dnaEntity;

	private StatsDTO statsDto;
	private List<StatsEntity> lstStats;
	private List<StatsEntity> lstStatsNotNull;

	@BeforeEach
	public void buildObject() {
		ModelDna modelDna = new ModelDna();
		responseFail = modelDna.responseFailedDTO();
		dnaDto = modelDna.createDTODNA();
		dnaEntityFail = new DNAEntity();
		dnaEntity = new DNAEntity(10L, "Cadena", true);
		dnaEntity.setAdnMutant("OtraCadena");
		dnaEntity.setSnMutant(false);
		lstStats = modelDna.responseStatsRatio();
		logger.info("Load info ".concat(dnaEntity.getAdnMutant()).concat(dnaEntity.getSnMutant() + ""));
		statsDto = modelDna.responseStats();
		lstStatsNotNull = modelDna.responseStatsEnti();
		logger.info("Load info ".concat(statsDto.toString()));
	}

	@Test
	void mutantCaseOk() {
		when(dnaVerifyUtil.evaluationProcess(dnaDto)).thenReturn(true);
		ResponseDTO responseDTO = null;
		try {
			responseDTO = dnaMutantService.isMutant(dnaDto);
		} catch (Exception e) {
			responseDTO = responseFail;
		}

		assertEquals(responseDTO.getCode(), 200);
	}

	@Test
	 void mutantCaseFail() {
		when(dnaMutantRepository.findByAdnMutant(dnaDto.getDna().toString())).thenReturn(dnaEntityFail);
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			dnaMutantService.isMutant(dnaDto);
		} catch (Exception e) {
			responseDTO = responseFail;
		}
		assertEquals(responseDTO.getCode(), 403);
	}
	

	/**
	 * stats
	 */
	@Test
	void statsTest() {
		when(adnStatsMutantRepository.findAll()).thenReturn(lstStats);
		assertEquals(statsMutantService.stats().getCountHumanDNA(), 0);
	}

	@Test
	void statsTestNotNull() {
		when(adnStatsMutantRepository.findAll()).thenReturn(lstStatsNotNull);
		assertNotNull(statsMutantService.stats());
	}

}
