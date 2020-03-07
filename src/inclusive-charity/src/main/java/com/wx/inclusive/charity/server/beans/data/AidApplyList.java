package com.wx.inclusive.charity.server.beans.data;

import com.wx.inclusive.charity.server.beans.AidApply;
import com.wx.inclusive.charity.server.beans.AidApplyRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: inclusive-charity
 * @description: donate
 * @author: Emily Ma
 * @create: 2020-03-07
 **/
@Data
public class AidApplyList {
    public static List<AidApply> aidApplyList = new ArrayList<AidApply>();

}
