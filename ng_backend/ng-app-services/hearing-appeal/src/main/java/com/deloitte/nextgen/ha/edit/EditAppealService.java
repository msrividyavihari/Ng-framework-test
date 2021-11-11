package com.deloitte.nextgen.ha.edit;



import com.deloitte.nextgen.ha.edit.dto.EditAppealDto;
import com.deloitte.nextgen.ha.entity.AmAplStatLog;
import com.deloitte.nextgen.ha.entity.AmAppealInfo;
import com.deloitte.nextgen.ha.entity.HaAppealInfo;
import com.deloitte.nextgen.ha.common.repository.AmAplStatLogRepository;
import com.deloitte.nextgen.ha.common.repository.AmAppealInfoRepository;
import com.deloitte.nextgen.ha.repository.HaAppealInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@Slf4j
@RequiredArgsConstructor
class EditAppealService {

    private final HaAppealInfoRepository haAppealInfoRepository;
    private final AmAplStatLogRepository amAplStatLogRepository;
    private final AmAppealInfoRepository amAppealInfoRepository;

    public void editAppealNum(EditAppealDto editAppealDto) {

        if(editAppealDto != null ){
            //Edit AmAppealInfo
            AmAppealInfo existingAmAppealInfoRecord = amAppealInfoRepository.findByAplNum(editAppealDto.getAppealNum());
            if(existingAmAppealInfoRecord != null){
                existingAmAppealInfoRecord.setLastStatus(editAppealDto.getAppealStatusCd());
                existingAmAppealInfoRecord.setLastStatusEffDt(editAppealDto.getAppealStatusEffectiveDt());
                if(editAppealDto.getResolvedDt() != null){
                    existingAmAppealInfoRecord.setResolvedDt(editAppealDto.getResolvedDt());
                }
                amAppealInfoRepository.save(existingAmAppealInfoRecord);
            }else{
                AmAppealInfo amAppealInfo = new AmAppealInfo();
                amAppealInfo.setAplNum(editAppealDto.getAppealNum());
                amAppealInfo.setLastStatus(editAppealDto.getAppealStatusCd());
                amAppealInfo.setLastStatusEffDt(editAppealDto.getAppealStatusEffectiveDt());
                amAppealInfo.setCreateUserId("Shubham1");
                if(editAppealDto.getResolvedDt() != null){
                    amAppealInfo.setResolvedDt(editAppealDto.getResolvedDt());
                }
                amAppealInfoRepository.save(amAppealInfo);
            }


            //Edit HaAppealInfo
            HaAppealInfo existingHaAppealInfoRecord = haAppealInfoRepository.findByAplNum(editAppealDto.getAppealNum());
            if(existingHaAppealInfoRecord != null){
                existingHaAppealInfoRecord.setLastStatus(editAppealDto.getAppealStatusCd());
                existingHaAppealInfoRecord.setLastStatusEffDt(editAppealDto.getAppealStatusEffectiveDt());
                if(editAppealDto.getResolvedDt() != null){
                    existingHaAppealInfoRecord.setResolvedDt(editAppealDto.getResolvedDt());
                }
                haAppealInfoRepository.save(existingHaAppealInfoRecord);
            }else{
                HaAppealInfo haAppealInfo = new HaAppealInfo();
                haAppealInfo.setAplNum(editAppealDto.getAppealNum());
                haAppealInfo.setLastStatus(editAppealDto.getAppealStatusCd());
                haAppealInfo.setLastStatusEffDt(editAppealDto.getAppealStatusEffectiveDt());
                haAppealInfo.setCreateUserId("Shubham2");
                if(editAppealDto.getResolvedDt() != null){
                    haAppealInfo.setResolvedDt(editAppealDto.getResolvedDt());
                }
                haAppealInfoRepository.save(haAppealInfo);
            }
            //Edit AmAplStatLog
            AmAplStatLog amAplStateLogEntity = new AmAplStatLog();
            amAplStateLogEntity.setAplNum(editAppealDto.getAppealNum());
            amAplStateLogEntity.setAplStatus(editAppealDto.getAppealStatusCd());
            amAplStateLogEntity.setDeleteInd("N");
            //amAplStateLogEntity.setStatusEffDt(editAppealDto.getAppealStatusEffectiveDt());
            amAplStateLogEntity.setStatusCreateDt(LocalDate.now());
            amAplStateLogEntity.setCreateUserId("Shubham3");
            AmAplStatLog AmAplStatLogCreated = amAplStatLogRepository.save(amAplStateLogEntity);

        }else{
            throw new IllegalStateException("Invalid Request");
        }
    }

}

