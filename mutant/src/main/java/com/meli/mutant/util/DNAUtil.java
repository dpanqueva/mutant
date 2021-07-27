package com.meli.mutant.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meli.mutant.exception.BadRequestException;
import com.meli.mutant.model.dto.DNADto;

@Component
public class DNAUtil {

	private final Logger logger = LoggerFactory.getLogger(DNAUtil.class);

	private List<String> initConf() {
		List<String> lstConf = new ArrayList<>();
		lstConf.add("AAAA");
		lstConf.add("CCCC");
		lstConf.add("TTTT");
		lstConf.add("GGGG");
		return lstConf;
	}

	private int cont = 0;

	public boolean evaluationProcess(DNADto adnMutant) {
		StringBuilder strStructureDNA = new StringBuilder();
		int vectorLength = adnMutant.getDna().size();
		char[][] dna = new char[vectorLength][vectorLength];

		for (int i = 0; i < vectorLength; i++) {
			String dnaRow = adnMutant.getDna().get(i);
			sizeMyMatrix(vectorLength, dnaRow);
			dna[i] = dnaRow.toUpperCase().toCharArray();
		}
		return analyzeADN(
				verticalEvaluate(dna, horizontalEvaluate(dna, diagonalEvaluate(dna, strStructureDNA))).toString());
	}

	private StringBuilder diagonalEvaluate(char[][] dna, StringBuilder strStructureDNA) {
		for (int i = 0; i < dna.length; i++) {
			strStructureDNA.append(dna[i][i]);
		}
		strStructureDNA.append(",");
		return strStructureDNA;
	}

	private StringBuilder horizontalEvaluate(char[][] dna, StringBuilder strStructureDNA) {
		for (char[] chars : dna) {
			for (int j = 0; j < dna.length; j++) {
				strStructureDNA.append(chars[j]);
			}
		}
		strStructureDNA.append(",");
		return strStructureDNA;
	}

	private StringBuilder verticalEvaluate(char[][] dna, StringBuilder strStructureDNA) {
		for (int i = 0; i < dna.length; i++)
			for (char[] chars : dna) {
				strStructureDNA.append(chars[i]);
			}
		return strStructureDNA;
	}

	private void sizeMyMatrix(int vectorLength, String dnaRow) {
		if (dnaRow.length() != vectorLength) {
			logger.error("Size does not correspond to the matrix");
			throw new BadRequestException("Size does not correspond to the matrix");
		}
	}

	private boolean analyzeADN(String dna) {
		List<String> lstDg = Arrays.asList(dna.split(","));
		lstDg.forEach(dnaV -> initConf().stream().filter(adn -> dnaV.indexOf(adn) > -1).forEach(adn -> cont++));
		if (cont >= 3) {
			cont = 0;
			return true;
		}
		cont = 0;
		return false;
	}

	public static String actualDate(String format) {
		Instant timestamp = Instant.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
				.withLocale(Locale.forLanguageTag(ConstantsDNA.LOCATE)).withZone(ZoneId.of(ConstantsDNA.TIME_ZONE));
		return formatter.format(timestamp);
	}

}
