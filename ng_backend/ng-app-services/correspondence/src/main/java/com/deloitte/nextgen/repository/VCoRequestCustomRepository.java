package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.VCoRequest;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VCoRequestCustomRepository {


    List<VCoRequest> findByCaseNumAndSort(@Param("caseNum") Long caseNum,
                                          @Param("reqDt") String reqDt,
                                          @Param("printDt") String printDt,
                                          @Param("ccProviderCertStartDt") String ccProviderCertStartDt,
                                          @Param("ccProviderCertEndDt") String ccProviderCertEndDt,
                                          @Param("ccProviderId") Long ccProviderId,
                                          @Param("t1DocId") String t1DocId,
                                          @Param("printMode") Character printMode,
                                          @Param("programCd") String programCd
                                                        );

    List<VCoRequest> findByAppNumAndSort(@Param("appNum") String appNum,
                                         @Param("reqDt") String reqDt,
                                         @Param("printDt") String printDt,
                                         @Param("ccProviderCertStartDt") String ccProviderCertStartDt,
                                         @Param("ccProviderCertEndDt") String ccProviderCertEndDt,
                                         @Param("ccProviderId") Long ccProviderId,
                                         @Param("t1DocId") String t1DocId,
                                         @Param("printMode") Character printMode,
                                         @Param("programCd") String programCd);

    List<VCoRequest> findByClientIdAndSort(@Param("clientId") Long clientId,
                                           @Param("reqDt") String reqDt,
                                           @Param("printDt") String printDt,
                                           @Param("ccProviderCertStartDt") String ccProviderCertStartDt,
                                           @Param("ccProviderCertEndDt") String ccProviderCertEndDt,
                                           @Param("ccProviderId") Long ccProviderId,
                                           @Param("t1DocId") String t1DocId,
                                           @Param("printMode") Character printMode,
                                           @Param("programCd") String programCd);

    List<VCoRequest> findByWorkerNameAndSort(@Param("workerName") String workerName,
                                             @Param("reqDt") String reqDt,
                                             @Param("printDt") String printDt,
                                             @Param("ccProviderCertStartDt") String ccProviderCertStartDt,
                                             @Param("ccProviderCertEndDt") String ccProviderCertEndDt,
                                             @Param("ccProviderId") Long ccProviderId,
                                             @Param("t1DocId") String t1DocId,
                                             @Param("printMode") Character printMode,
                                             @Param("programCd") String programCd);

    List<VCoRequest> findByWorkerIdAndSort(@Param("workerId") Long workerId,
                                           @Param("reqDt") String reqDt,
                                           @Param("printDt") String printDt,
                                           @Param("ccProviderCertStartDt") String ccProviderCertStartDt,
                                           @Param("ccProviderCertEndDt") String ccProviderCertEndDt,
                                           @Param("ccProviderId") Long ccProviderId,
                                           @Param("t1DocId") String t1DocId,
                                           @Param("printMode") Character printMode,
                                           @Param("programCd") String programCd);

    List<VCoRequest> findByReqSeqAndDetSeq(Long t2CoReqSeq, Long coDetSeq);

}
