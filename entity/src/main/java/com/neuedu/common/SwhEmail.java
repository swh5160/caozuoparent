package com.neuedu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 施子安
 * @create
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwhEmail {
    private String text;//内容
    private String to;//接收人
    private String subject;//主题
}
