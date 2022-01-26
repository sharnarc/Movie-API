package com.example.movie.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieErrorResponse extends RuntimeException {

    private int status;
    private String message;
    private long timeStamp;
}
