package com.deloitte.nextgen.dto.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoEDMSUploadDTO {
        private String title;
        private String category_id;
        private byte[] file;
        private CoEDMSMetadata metadata;
}
