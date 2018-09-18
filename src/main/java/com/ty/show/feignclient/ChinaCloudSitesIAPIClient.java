package com.ty.show.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(url="https://",name="chinaCloudSitesIAPIClient")
public interface ChinaCloudSitesIAPIClient {

}