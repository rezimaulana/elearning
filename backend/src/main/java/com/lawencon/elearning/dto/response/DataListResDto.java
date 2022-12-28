package com.lawencon.elearning.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class DataListResDto<T> {
    
    private List<T> data;

}
