package edu.backendservlet.exception;

import edu.backendservlet.DTO.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExcepyionHander {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.EX_NOT_FOUND.getCode());
        apiResponse.setMessage(ErrorCode.EX_NOT_FOUND.getMessage() + "\n" + ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }
    @ExceptionHandler(value = Appexcption.class)
    public ResponseEntity<ApiResponse> handleAppException(Appexcption e){
        ErrorCode errorCode =e.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponse);

    }


}
