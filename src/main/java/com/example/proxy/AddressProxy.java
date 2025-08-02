package com.example.proxy;

import com.example.config.AddressConfig;
import com.example.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient( name = "dadata-client", url = "${dadata.api.url}", configuration = AddressConfig.class)
public interface AddressProxy {
    @PostMapping("address")
    List<AddressDto> address(@RequestBody List<String> query);
}
