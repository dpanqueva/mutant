package com.meli.mutant.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.mutant.model.data.ModelDna;
import com.meli.mutant.model.dto.DNADto;
import com.meli.mutant.model.dto.ResponseDTO;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UtilTest {

	@InjectMocks
	private DNAUtil dnaUtil;

	private DNADto dnaDto;
	private DNADto dnaDtoFailed;
	private DNADto dnaDtoDiff;
	private ResponseDTO responseFail;
	private ResponseDTO responseBR;

	@BeforeEach
	public void buildObject() {
		ModelDna modelDna = new ModelDna();
		dnaDto = modelDna.createDTODNAOpc();
		dnaDtoFailed = modelDna.createDTODNAFailed();
		dnaDtoDiff = modelDna.createDTODNAMat();
		responseFail = modelDna.responseFailedDTO();
		responseBR = modelDna.responseFailedDTOBR();
	}

	@Test
	public void mutantCaseOk() {
		assertTrue(dnaUtil.evaluationProcess(dnaDto));
	}

	@Test
	public void analyzeADN() {
		boolean res = dnaUtil.evaluationProcess(dnaDtoFailed);
		assertFalse(res);
	}

	@Test
	public void matrixEval() {
		ResponseDTO response = null;
		try {
			dnaUtil.evaluationProcess(dnaDtoDiff);
		} catch (Exception e) {
			response = responseBR;
		}
		assertEquals(response.getCode(), 400);
	}

}
