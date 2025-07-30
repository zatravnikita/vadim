package com.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class DadataDto {
    private List<Suggestion> suggestions;

    @Data
    public static class Suggestion {
        private AddressData data;
    }

    @Data
    public static class AddressData {
        private String result;
    }
}
