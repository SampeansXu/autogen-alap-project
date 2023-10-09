package cn.hqwx.autogen.alap.cloud.service.autogen.engine;

import cn.hqwx.autogen.alap.cloud.model.ApplicationMetadata;
import cn.hqwx.autogen.alap.cloud.model.ProjectMetadata;
import cn.hqwx.autogen.alap.cloud.service.autogen.utils.MetaDataUtil;

import java.io.File;
import java.util.Map;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-10-09
 */
public class ResourcesEngine extends AbstractTemplateEngine {
    public String execute(String pathRoot, ApplicationMetadata applicationMetadata, ProjectMetadata projectMetadata) {
        String configPath = pathRoot;
        try {
            Map<String, Object> metaDataMap = MetaDataUtil.toMap(applicationMetadata, projectMetadata);

            //1. log配置
            String logConfig = pathRoot + "/" + "logback-spring.xml";
            File logFile = new File(logConfig);
            Map<String, Object> logDataModel = metaDataMap;
            super.saveFile(logFile, "logback-spring.ftl", logDataModel);

            //TODO 其它配置
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return configPath;
    }
}
