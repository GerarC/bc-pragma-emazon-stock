package com.emazon.stock.adapters.driven.feigns;

import com.emazon.stock.adapters.driven.feigns.dto.request.AuthorizationRequest;
import com.emazon.stock.adapters.driven.feigns.dto.response.AuthorizationResponse;
import com.emazon.stock.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-MICROSERVICE", url = "${emazon.user.base-url}", configuration = FeignClientConfiguration.class)
public interface AuthorizationFeign {
    @PostMapping(value = "/auth/authorize", consumes = MediaType.APPLICATION_JSON_VALUE)
    AuthorizationResponse authorize(@RequestBody AuthorizationRequest authorizationRequest);
}
