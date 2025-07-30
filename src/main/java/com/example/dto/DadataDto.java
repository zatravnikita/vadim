package com.example.dto;

import lombok.Data;
import java.util.List;

public class DadataResponse {

    @Data
    public class DadataResponse{
        private List<DadataAddress> suggestions;
    }

    @Data
    public class DadataAddress{
        private String result;
    }
}
