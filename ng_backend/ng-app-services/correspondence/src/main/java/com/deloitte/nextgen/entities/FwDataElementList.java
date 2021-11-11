package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="FW_DATA_ELEMENT_LIST")
@Data
@NoArgsConstructor
public class FwDataElementList extends BaseEntity implements Serializable {

        /**
         * Field deAuthUser.
         */
        @Column(name = "DE_AUTH_USER")
        private String deAuthUser;
        /**
         * Field deDefaultValue.
         */
        @Column(name = "DE_DEFAULT_VALUE")
        private String deDefaultValue;
        /**
         * Field deElementId.
         */
        @Id
        @Column(name = "DE_ELEMENT_ID")
        private Long deElementId;
        /**
         * Field deLabelText.
         */
        @Column(name = "DE_LABEL_TEXT")
        private String deLabelText;
        /**
         * Field deLastChanged.
         */
        @Column(name = "DE_LAST_CHANGED")
        private Timestamp deLastChanged;
        /**
         * Field deMandatory.
         */
        @Column(name = "DE_MANDATORY")
        private Long deMandatory;
        /**
         * Field deNotes.
         */
        @Column(name = "DE_NOTES")
        private String deNotes;
        /**
         * Field dePackage.
         */
        @Column(name = "DE_PACKAGE")
        private String dePackage;
        /**
         * Field deScreenElementName.
         */
        @Column(name = "DE_SCREEN_ELEMENT_NAME")
        private String deScreenElementName;
        /**
         * Field deWidth.
         */
        @Column(name = "DE_WIDTH")
        private Integer deWidth;
        /**
         * Field langCd.
         */
        @Column(name = "LANG_CD")
        private String langCd;
        /**
         * Field rdRefId.
         */
        @Column(name = "RD_REF_ID")
        private Integer rdRefId;
}
