package team.pre004.stackoverflowclone.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.handler.exception.*;

@Slf4j
@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomNotContentByIdException.class)
    public ResponseEntity<?> customNotContentByIdException(CustomNotContentByIdException e) {
        String message = "해당하는 id에 대한 정보가 없습니다.";
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, message, null), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CustomDuplicationUsersException.class)
    public ResponseEntity<?> duplicationUsersException(CustomDuplicationUsersException e) {
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, e.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomNullPointUsersException.class)
    public ResponseEntity<?> nullPointUserUsersException(CustomNullPointUsersException e){
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomNotContentItemException.class)
    public ResponseEntity<?> nullPointUserItemsException(CustomNotContentItemException e){
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomNotAccessItemsException.class)
    public ResponseEntity<?> notAccessItemsException(CustomNotAccessItemsException e){
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, e.getMessage(), null), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(CustomLikesConflictException.class)
    public ResponseEntity<?> likesConflictException(CustomLikesConflictException e){
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, e.getMessage(), null), HttpStatus.CONFLICT);
    }


    //Security Test

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> httpMessageNotReadableException(IllegalStateException e){

         log.info(e.getCause().toString());
         log.info(e.getMessage());
         log.info(e.getClass().toString());


        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, e.getMessage(),null ), HttpStatus.BAD_REQUEST);
    }

}
