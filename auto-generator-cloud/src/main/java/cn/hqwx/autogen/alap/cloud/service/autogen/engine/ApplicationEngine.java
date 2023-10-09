package cn.hqwx.autogen.alap.cloud.service.autogen.engine;

import cn.hqwx.autogen.alap.cloud.model.ApplicationMetadata;

import java.io.File;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-26
 */
public class ApplicationEngine extends AbstractTemplateEngine {
    public String execute(String pathRoot, ApplicationMetadata applicationMetadata) {
        String fullName = pathRoot + "/" + applicationMetadata.getClassName() + ".java";
        try {
            File file = new File(fullName);
            super.saveFile(file, "application.ftl", applicationMetadata);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return fullName;
    }
}
