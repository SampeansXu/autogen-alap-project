package cn.hqwx.autogen.alap.cloud.service.autogen.engine;

import cn.hqwx.autogen.alap.cloud.constants.GlobalConstant;
import cn.hqwx.autogen.alap.cloud.model.ApplicationMetadata;
import cn.hqwx.autogen.alap.cloud.model.ProjectMetadata;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-26
 */
public class DDDModulEngine extends AbstractTemplateEngine {
    public String execute(String pathRoot, ApplicationMetadata applicationMetadata, ProjectMetadata projectMetadata) {
        try {
            final String DemoName = GlobalConstant.Default_DemoName;

            // api 层
            Map<String, Object> apiDataModel = new HashMap<>();
            apiDataModel.put("packageName", applicationMetadata.getPackageName() + ".api." + DemoName.toLowerCase());
            apiDataModel.put("demoName", DemoName);
            super.saveFile(new File(pathRoot + "/api/" + DemoName.toLowerCase() + "/" + DemoName + "Controller.java"),
                    "controller.ftl", apiDataModel);

            // application 层
            String appPath = pathRoot + "/application";
            super.createDir(appPath + "/" + DemoName.toLowerCase() + "/dto");
            super.createDir(appPath + "/" + DemoName.toLowerCase() + "/query/impl");
            super.createDir(appPath + "/" + DemoName.toLowerCase() + "/query/param");
            super.createDir(appPath + "/" + DemoName.toLowerCase() + "/command/impl");
            super.createDir(appPath + "/" + DemoName.toLowerCase() + "/command/param");
//            Map<String, String> appDataModel = new HashMap<>();
//            appDataModel.put("packageName", applicationMetadata.getPackageName() + ".application." + projectMetadata.getName());
//            super.saveFile(new File(pathRoot + "application/package-info.java"), "package-info.ftl", appDataModel);

            // domain 层
            String domainPath = pathRoot + "/domain";
            super.createDir(domainPath + "/" + DemoName.toLowerCase());

            // infrastructure 层
            String infraPath = pathRoot + "/infrastructure";
            super.createDir(infraPath + "/repository/model");
            super.createDir(infraPath + "/repository/mapper");
            super.createDir(infraPath + "/repository/dto");
            super.createDir(infraPath + "/repository/impl");

            // shared 层
            String sharedPath = pathRoot + "/shared";
            super.createDir(sharedPath + "/utils");
            super.createDir(sharedPath + "/constants");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return "code structure: api application domain infrastructure shared";
    }
}
