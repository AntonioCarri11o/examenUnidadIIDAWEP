package mx.edu.utez.server.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomResponse<T> {
    T data;
    Boolean error;
    int StatusCode;
    String message;
}
