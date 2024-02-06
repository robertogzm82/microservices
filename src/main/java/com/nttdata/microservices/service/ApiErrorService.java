package com.nttdata.microservices.service;

import com.nttdata.microservices.model.ApiError;

import reactor.core.publisher.Mono;

public interface ApiErrorService {

	Mono<ApiError> apiError(int error, String mensaje);
	
}
