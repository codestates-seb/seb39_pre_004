package team.pre004.stackoverflowclone.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointItemsExeption;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointUsersException;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomNullPointUsersException.class)
    public ResponseEntity<?> nullPointUserUsersException(CustomNullPointUsersException e){
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomNullPointItemsExeption.class)
    public ResponseEntity<?> nullPointUserItemsException(CustomNullPointItemsExeption e){
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

}
