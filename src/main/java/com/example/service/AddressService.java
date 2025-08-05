package com.example.service;

import com.example.dto.AddressDto;

import com.example.proxy.AddressProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressProxy addressProxy;

    public String findAddress(String query) {
        List<AddressDto> source = addressProxy.address(List.of(query));
        return source.get(0).getResult();
    }
}