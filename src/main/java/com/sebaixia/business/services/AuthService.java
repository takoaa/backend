package com.sebaixia.business.services;

import com.sebaixia.business.dto.SignupRequest;
import com.sebaixia.business.entities.Customer;


public interface AuthService {
    Customer createCustomer(SignupRequest signupRequest);

	
}
