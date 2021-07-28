package com.meli.mutant.exception;


/**
 * @author dpanquev
 * @version 2021-07-28
 * */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.meli.mutant.model.dto.ResponseDTO;
import com.meli.mutant.util.ConstantsDNA;
import com.meli.mutant.util.DNAUtil;


@ControllerAdvice
public class ExceptionHandlerConf {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerConf.class);


    @org.springframework.web.bind.annotation.ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ResponseDTO> contentException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseDTO(HttpStatus.FORBIDDEN.value()
                , DNAUtil.actualDate(ConstantsDNA.DATE_FORMAT_1)
                , e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDTO> batRequestException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST.value()
                , DNAUtil.actualDate(ConstantsDNA.DATE_FORMAT_1)
                , e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> runException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value()
                        , DNAUtil.actualDate(ConstantsDNA.DATE_FORMAT_1)
                        , "Ups! An unexpected error occurred"));
    }

}
