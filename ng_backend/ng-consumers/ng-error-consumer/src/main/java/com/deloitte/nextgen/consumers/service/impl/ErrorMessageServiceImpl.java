package com.deloitte.nextgen.consumers.service.impl;

import com.deloitte.nextgen.consumers.entities.ErrorContext;
import com.deloitte.nextgen.consumers.repository.ErrorContextRepository;
import com.deloitte.nextgen.consumers.service.ErrorMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nishmehta on 13/03/2021 12:27 AM
 * @project ng-consumers
 */

@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {

    @Autowired
    private ErrorContextRepository errorContextRepository;


    @Override
    public void insert(ErrorContext ec) {
        errorContextRepository.save(ec);
    }
}
