package com.wuxianggujun.tinasproutcore.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author WuXiangGuJun
 * @create 2023-04-05 8:49
 **/
@Data
public class ApiResult {
    private String status;

    @JSONField(name = "retcode")
    private int retCode;

    private Object data;

    private String echo;
}
