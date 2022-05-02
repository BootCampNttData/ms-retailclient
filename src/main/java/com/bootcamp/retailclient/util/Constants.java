package com.bootcamp.retailclient.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
public class Constants {
    @Value("${constants.url.server}")
    public static String gwServer;

}
