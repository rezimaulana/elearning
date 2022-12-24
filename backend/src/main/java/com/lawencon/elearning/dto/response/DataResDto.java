package com.lawencon.elearning.dto.response;

import lombok.Data;

@Data
public class DataResDto<T> {
    
    private T data;

}
