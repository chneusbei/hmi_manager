package com.plc.hmi.annotation;

import com.google.common.collect.Lists;
import com.plc.hmi.FlowException;
import net.sf.oval.ConstraintViolation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.oval.Validator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class ValidatorTools {
    private static Logger logger = LoggerFactory.getLogger(ValidatorTools.class);

    /**
     * 验证方法
     * @param bean 需要验证的对象
     * @param profile 场景
     * @return
     */
    public static List<String> validateBean(Object bean, String profile) {
        List<String> result = Lists.newArrayList();
        logger.info("start validate :"+bean.getClass().getName());
        Validator validator = new Validator();
        List<ConstraintViolation> message = null;
        if(StringUtils.isEmpty(profile)) {
            //全量验证
            message=validator.validate(bean);
        } else {
            //按场景验证
            validator.disableAllProfiles();
            validator.enableProfile(profile);
            message=validator.validate(bean,profile);
        }

        if(!CollectionUtils.isEmpty(message)) {
            for(ConstraintViolation validResult : message) {
                result.add(validResult.getMessage());
            }
        }
        logger.info("validate end :"+bean.getClass().getName() + "  result :" +result.toString());
        return result;
    }

    public static void validate(Object bean) {
        validate(bean, null);
    }
    public static void validate(Object bean, String profile) {
        List<String> listString = ValidatorTools.validateBean(bean, profile);
        if(listString == null  || listString.isEmpty()) {
            return;
        }
        StringBuilder  stringBuilder = new StringBuilder();
       for(String str : listString) {
           stringBuilder.append(str).append(";");
       }
       throw new FlowException("ValidateCheck fail", stringBuilder.toString());

    }
}
