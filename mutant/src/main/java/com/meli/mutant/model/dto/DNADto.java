package com.meli.mutant.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DNADto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "the mutant dna must not be empty")
    @NotNull(message = "the mutant dna must not be null")
    private List<String> dna;

    @Override
    public String toString() {
        return "" + dna;
    }
}
