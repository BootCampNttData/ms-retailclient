package com.bootcamp.retailclient.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Constants {
    @Value("${constants.url.server}")
    private String gatewayUrl;

}
