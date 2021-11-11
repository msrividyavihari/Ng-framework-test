package  com.deloitte.nextgen.dto.vo;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class ViewHistoryDetailVO {
    private List attachedDocNames;
    private String originalRecipient;
    private List recipientWithCoRptSeqList;
    private boolean recipientFound;
}
