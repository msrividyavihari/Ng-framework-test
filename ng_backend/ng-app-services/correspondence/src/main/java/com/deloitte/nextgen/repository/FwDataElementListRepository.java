package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.FwDataElementList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FwDataElementListRepository extends JpaRepository<FwDataElementList,Long> {

    String SELECT = "SELECT DE_AUTH_USER, DE_DEFAULT_VALUE, DE_ELEMENT_ID, DE_LABEL_TEXT, DE_LAST_CHANGED, " +
            "  DE_MANDATORY, DE_NOTES, DE_PACKAGE, DE_SCREEN_ELEMENT_NAME, DE_WIDTH, LANG_CD, RD_REF_ID " +
            " FROM FW_DATA_ELEMENT_LIST ";

    String SELECT_1 = "SELECT DE_ELEMENT_ID FROM FW_DATA_ELEMENT_LIST ";
//    @Query(
//        value = "SELECT * FROM FW_DATA_ELEMENT_LIST where DE_ELEMENT_ID =(:elementId)",nativeQuery = true
//    )
    @Query(
            value = "from FwDataElementList WHERE DE_ELEMENT_ID =(:elementId) ", nativeQuery = false
    )
    List<FwDataElementList> findByElementId(Long elementId);
}
