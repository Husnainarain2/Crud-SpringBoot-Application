package com.Practices.Crud_SpringBoot_Application.ExceptionHandler;

import com.Practices.Crud_SpringBoot_Application.Dto.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler {

@ExceptionHandler(ResourcesNotFoundException.class)
public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(HttpServletRequest req, ResourcesNotFoundException ex){
    ExceptionResponseDto responseDto=new ExceptionResponseDto(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            req.getRequestURI()
    );
    return ResponseEntity.
            status(HttpStatus.NOT_FOUND).
            body(responseDto);
}

@ExceptionHandler(RuntimeException.class)
public ResponseEntity<ExceptionResponseDto> runtimeExceptionHandler(HttpServletRequest req,RuntimeException ex){
   ExceptionResponseDto responseDto=new ExceptionResponseDto(
           LocalDateTime.now(),
           HttpStatus.INTERNAL_SERVER_ERROR.value(),
           HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
           ex.getMessage(),
           req.getRequestURI()
   );
   return ResponseEntity.
           status(HttpStatus.INTERNAL_SERVER_ERROR).
           body(responseDto);
}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleException(Exception ex, HttpServletRequest request) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

return ResponseEntity.
        status(HttpStatus.INTERNAL_SERVER_ERROR).
        body(exceptionResponseDto);
    }
}
