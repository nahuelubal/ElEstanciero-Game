package ar.edu.utn.frc.tup.lciii.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseHandler {
    Boolean success;
    String message;
}
