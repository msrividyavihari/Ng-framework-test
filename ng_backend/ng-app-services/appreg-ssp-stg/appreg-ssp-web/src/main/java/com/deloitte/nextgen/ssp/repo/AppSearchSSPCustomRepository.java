package com.deloitte.nextgen.ssp.repo;

import com.deloitte.nextgen.ssp.request.AppSearchSSPRequest;

import java.util.List;

public interface AppSearchSSPCustomRepository {
    List<Object[]> findApplications(AppSearchSSPRequest appSearchVO);
    List<Object[]> findConflictApps(String appNUm);

    List<String>  findByAppNum(String appNum);
    List<Object[]>  findAddrDtl(String appNum);
    List<Object[]>  findWeekDayContactDtl(String appNum);
    List<Object[]>  findPhnDtl(String appNum);
    List<Object[]>  findEmailDtl(String appNum);
    List<Object[]>  findAuthRepAddrDtl(String appNum);


}
