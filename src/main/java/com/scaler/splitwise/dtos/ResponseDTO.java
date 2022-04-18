package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseDTO<DTO_TYPE> {
    private HttpStatus status;
    private DTO_TYPE data;

    public static <DTO_TYPE> ResponseDTO<DTO_TYPE> notFound() {
        ResponseDTO<DTO_TYPE> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public static <DTO_TYPE> ResponseDTO<DTO_TYPE> success(DTO_TYPE data) {
        ResponseDTO<DTO_TYPE> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.OK);
        response.setData(data);
        return response;
    }


}
