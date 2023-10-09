package cn.hqwx.autogen.alap.cloud.service.autogen.engine;

import cn.hqwx.autogen.alap.cloud.model.ApplicationMetadata;
import cn.hqwx.autogen.alap.cloud.model.ProjectMetadata;
import cn.hqwx.autogen.alap.cloud.service.autogen.utils.MetaDataUtil;

import java.io.File;
import java.util.Map;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-26
 */
public class PomEngine extends AbstractTemplateEngine {
    public String execute(String pathRoot, ApplicationMetadata applicationMetadata, ProjectMetadata projectMetadata) {
        String fullName = pathRoot + "/" + "pom.xml";
        try {
            Map<String, Object> pomDataModel = MetaDataUtil.toMap(applicationMetadata, projectMetadata);
            File file = new File(fullName);
            super.saveFile(file, "pom.ftl", pomDataModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return fullName;
    }
}
