package com.neuedu.core;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.neuedu.common.ResultJson;

/**
 * @author 施子安
 * @create
 */
public class SentinelUtil {
    public static ResultJson handlerException(Integer pageNo, Integer pageSize, String value, BlockException blockException){
        System.out.println(pageNo);
        blockException.printStackTrace();
        return ResultJson.failed("崩了");
    }
}
