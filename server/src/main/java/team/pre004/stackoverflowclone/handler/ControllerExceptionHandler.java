package team.pre004.stackoverflowclone.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.handler.exception.*;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomNotContentByIdException.class)
    public ResponseEntity<?> customNotContentByIdException() {
        String message = "해당하는 id에 대한 정보가 없습니다.";
        return new ResponseEntity<>(new CMRespDto<>(ResponseCode.ERROR, message, null), HttpStatus.NO_CONTENT);
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

}
