package com.deloitte.nextgen.service;

public interface AuthService {
    String getToken(String username, String password);
}
