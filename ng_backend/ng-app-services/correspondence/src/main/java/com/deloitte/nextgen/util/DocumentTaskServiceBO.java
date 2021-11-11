package com.deloitte.nextgen.util;

import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.repository.InDisDocMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentTaskServiceBO {

    @Autowired
    InDisDocMasterRepository inDisDocMasterRepository;

    /**
     * validates is it duplicate
     *
     * @param inDisDocMasterCargo
     * @return
     * @throws Exception
     */
    public boolean isDuplicate(InDisDocMaster inDisDocMasterCargo)
            throws Exception {

        boolean isDuplicate = false;
        List<InDisDocMaster> inDisDocMasterList;
        inDisDocMasterList = inDisDocMasterRepository.findDuplicate(inDisDocMasterCargo);

        if (inDisDocMasterList.size() == 1) {
            isDuplicate = true;
        }

        return isDuplicate;
    }
}
