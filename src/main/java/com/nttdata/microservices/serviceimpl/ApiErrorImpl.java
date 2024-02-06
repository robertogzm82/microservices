package com.nttdata.microservices.serviceimpl;

import org.springframework.stereotype.Service;

import com.nttdata.microservices.model.ApiError;
import com.nttdata.microservices.service.ApiErrorService;

import reactor.core.publisher.Mono;

@Service
public class ApiErrorImpl implements ApiErrorService{

	@Override
	public Mono<ApiError> apiError(int error, String mensaje) {
		return Mono.just(new ApiError(error,mensaje));
	}

	
}
