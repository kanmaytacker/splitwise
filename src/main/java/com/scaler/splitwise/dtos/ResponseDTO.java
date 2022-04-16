package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseDTO<T> {
    private HttpStatus status;
    private T data;

    public static <T> ResponseDTO<T> success(T data) {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setData(data);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> notFound() {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus(HttpStatus.NOT_FOUND);
        return responseDTO;
    }


}
