package com.scaler.splitwise.utils;

import com.scaler.splitwise.dtos.ResponseDTO;

import java.util.Optional;

public class ResponseUtils {

    public static <DTO_TYPE> ResponseDTO<DTO_TYPE> toResponse(Optional<DTO_TYPE> data) {
        return data.map(ResponseDTO::success).orElse(ResponseDTO.notFound());
    }
}
