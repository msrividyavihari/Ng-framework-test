package com.deloitte.nextgen.service;

import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence;

public interface CoIESBaseAssembler {
   IESCorrespondence getIESCorrespondence(COCorrespondence coCorrespondence) throws Exception;
}
