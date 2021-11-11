package com.deloitte.nextgen.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *This class is used to keep the list of page Ids which need to disable Central Button
 */

@Component
public class DisableCPCButtonDocs {

    //create static instance of array list
    public static List disableCPCButtonPageIdList = new ArrayList();
    public static List disableLPButtonPageIdList = new ArrayList();
    //make a static list of page ids for disabling CPC button (Local Print only)

    static {

        disableCPCButtonPageIdList.add("CO099"); //DHS-4762
        disableCPCButtonPageIdList.add("CO238"); //DHS-849
        disableCPCButtonPageIdList.add("CO203"); //DHS-849
        disableCPCButtonPageIdList.add("CO003"); //DHS-0834
        disableCPCButtonPageIdList.add("CO055"); //DHS-0049-B
        disableCPCButtonPageIdList.add("CO303"); //DHS-0049-BU
        disableCPCButtonPageIdList.add("CO157"); //DHS-0049-A
        disableCPCButtonPageIdList.add("CO245"); //DHS-0049-A-E
        disableCPCButtonPageIdList.add("CO329"); //DHS-4663
        disableCPCButtonPageIdList.add("CO331"); //DHS-0093-A
        disableCPCButtonPageIdList.add("CO330"); //DHS-0093-A
        disableCPCButtonPageIdList.add("CO183"); //DHS-1354
        disableCPCButtonPageIdList.add("CO341"); //DHS-1354-A
        disableCPCButtonPageIdList.add("CO180"); //DHS-3050
        disableCPCButtonPageIdList.add("CO332"); //DHS-0517
        disableCPCButtonPageIdList.add("CO611"); //DHS-0611
        disableCPCButtonPageIdList.add("CO375"); //DHS-0619
        disableCPCButtonPageIdList.add("CO376"); //DHS-0619-SP
        disableCPCButtonPageIdList.add("CO378");  // DHS-1538

        disableCPCButtonPageIdList.add("CO365"); //DHS-3043
        disableCPCButtonPageIdList.add("CO366"); //DHS-1291
        disableCPCButtonPageIdList.add("CO367"); //DHS-0045
        disableCPCButtonPageIdList.add("CO004"); //DHS-0048

        disableCPCButtonPageIdList.add("CO368"); //DHS-0078
        disableCPCButtonPageIdList.add("CO227"); //DHS-4772
        disableCPCButtonPageIdList.add("CO108"); //DHS-4585
        disableCPCButtonPageIdList.add("CO169"); //DHS-4586

        disableCPCButtonPageIdList.add("CO018"); //DHS-805
        disableCPCButtonPageIdList.add("CO381"); //DHS-4749
        disableCPCButtonPageIdList.add("CO382"); //DHS-839
    }

}
