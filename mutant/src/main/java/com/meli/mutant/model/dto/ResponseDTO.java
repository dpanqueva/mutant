package com.meli.mutant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author dpanquev
 * @version 2021-07-28
 * */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String dateAt;
    private String message;
}
