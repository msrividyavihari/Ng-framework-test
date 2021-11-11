package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.client.request.AppSearchRequest;

import java.util.List;

public interface AppSearchCustomRepository {

    List<Object[]> findApplications(AppSearchRequest appSearchRequest);
}
