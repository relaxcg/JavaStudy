package org.relaxcg.sc.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author relaxcg
 * @date 2023/11/20 11:10
 */
@Component
public class MessageLocalTransfer {
    private static MessageSource MESSAGE_SOURCE;

    public MessageLocalTransfer(MessageSource messageSource) {
        MESSAGE_SOURCE = messageSource;
    }

    public static String transfer(String source) {
        return MESSAGE_SOURCE.getMessage(source, null, LocaleContextHolder.getLocale());
    }
}
