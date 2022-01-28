package com.wracle.cakemgr.cakemanager.ui.controller;

import com.wracle.cakemgr.cakemanager.ui.model.request.UsernamePasswordLoginRequestModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	@ApiOperation("Cake manager user login")
    @ApiResponses(value = {
    @ApiResponse(code = 200, 
            message = "Response Headers, contains Authorization which should be used in other endpoints",
            responseHeaders = {
                @ResponseHeader(name = "Authorization",
                        description = "Bearer <JWT value here>")
            })
    })
	@PostMapping("/users/login")
	public void theFakeLogin(@RequestBody UsernamePasswordLoginRequestModel loginRequestModel)
	{
		throw new IllegalStateException("This method should not be called. This method is implemented by Spring Security");
	}
}
