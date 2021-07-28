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

/**
 * @author dpanquev
 * @version 2021-07-28
 * */
@Component
public class DNAUtil {

	private final Logger logger = LoggerFactory.getLogger(DNAUtil.class);

	private int cont = 0;

	/**
	 * Method to load initial mutant configuration
	 * @return
	 * */
	private List<String> initConf() {
		List<String> lstConf = new ArrayList<>();
		lstConf.add("AAAA");
		lstConf.add("CCCC");
		lstConf.add("TTTT");
		lstConf.add("GGGG");
		return lstConf;
	}


	/**
	 * Method to construct dna matrix to evaluate process
	 * @param adnMutant
	 * @return
	 * */
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

	/**
	 * Method to analyze diagonal
	 * @param dna
	 * @param strStructureDNA
	 * @return
	 * */
	private StringBuilder diagonalEvaluate(char[][] dna, StringBuilder strStructureDNA) {
		for (int i = 0; i < dna.length; i++) {
			strStructureDNA.append(dna[i][i]);
		}
		strStructureDNA.append(",");
		return strStructureDNA;
	}

	/**
	 * Method to analyze horizontal
	 * @param dna
	 * @param strStructureDNA
	 * @return
	 * */
	private StringBuilder horizontalEvaluate(char[][] dna, StringBuilder strStructureDNA) {
		for (char[] chars : dna) {
			for (int j = 0; j < dna.length; j++) {
				strStructureDNA.append(chars[j]);
			}
		}
		strStructureDNA.append(",");
		return strStructureDNA;
	}

	/**
	 * Method to analyze vertical
	 * @param dna
	 * @param strStructureDNA
	 * @return
	 * */
	private StringBuilder verticalEvaluate(char[][] dna, StringBuilder strStructureDNA) {
		for (int i = 0; i < dna.length; i++)
			for (char[] chars : dna) {
				strStructureDNA.append(chars[i]);
			}
		return strStructureDNA;
	}

	/**
	 * Method to analyze size Matrix
	 * @param vectorLength
	 * @param dnaRow
	 * @return
	 * */
	private void sizeMyMatrix(int vectorLength, String dnaRow) {
		if (dnaRow.length() != vectorLength) {
			logger.error("Size does not correspond to the matrix");
			throw new BadRequestException("Size does not correspond to the matrix");
		}
	}

	/**
	 * Method to analyze dna
	 * @param dna
	 * @return
	 * */
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

	/**
	 * Method to get the current date
	 * @param format
	 * @return
	 * */
	public static String actualDate(String format) {
		Instant timestamp = Instant.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
				.withLocale(Locale.forLanguageTag(ConstantsDNA.LOCATE)).withZone(ZoneId.of(ConstantsDNA.TIME_ZONE));
		return formatter.format(timestamp);
	}

}
