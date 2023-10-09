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
public class ConfigsEngine extends AbstractTemplateEngine {
    public String execute(String pathRoot, ApplicationMetadata applicationMetadata, ProjectMetadata projectMetadata) {
        String configPath = pathRoot + "/config";
        try {
            Map<String, Object> metaDataMap = MetaDataUtil.toMap(applicationMetadata, projectMetadata);

            //1. swagger配置
            String swaggerConfig = pathRoot + "/config/swagger/" + "SwaggerConfig.java";
            File scfgFile = new File(swaggerConfig);
            Map<String, Object> scfgDataModel = metaDataMap;
            super.saveFile(scfgFile, "swaggerconfig.ftl", scfgDataModel);

            //2. jetcache等cache配置
            if(applicationMetadata.getHasCache()){
                String cacheConfig = pathRoot + "/config/cache/" + "CacheConfig.java";
                File ccfgFile = new File(cacheConfig);
                Map<String, Object> ccfgDataModel = metaDataMap;
                super.saveFile(ccfgFile, "cacheconfig.ftl", ccfgDataModel);
            }

            //3. mybatisplus等db配置
            if(applicationMetadata.getHasDB()){
                String mybpConfig = pathRoot + "/config/mybatisplus/" + "MybatisPlusConfig.java";
                File mybpfgFile = new File(mybpConfig);
                Map<String, Object> mybpDataModel = metaDataMap;
                super.saveFile(mybpfgFile, "mybatisplusconfig.ftl", mybpDataModel);
            }

            //TODO 其它配置
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return configPath;
    }
}
