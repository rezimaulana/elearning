package com.lawencon.elearning.dto.response;

import lombok.Data;

@Data
public class TransactionResDto<T> {
    
    private T data;
    private String message;

}
