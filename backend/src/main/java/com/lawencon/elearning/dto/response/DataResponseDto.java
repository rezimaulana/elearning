package com.lawencon.elearning.dto.response;

import lombok.Data;

@Data
public class DataResponseDto<T> {
    
    private T data;

}
