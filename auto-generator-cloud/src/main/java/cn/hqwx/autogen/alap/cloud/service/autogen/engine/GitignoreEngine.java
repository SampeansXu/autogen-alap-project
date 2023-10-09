package cn.hqwx.autogen.alap.cloud.service.autogen.engine;

import java.io.File;
import java.io.IOException;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-25
 */
public class GitignoreEngine extends AbstractTemplateEngine {
    public String execute(String pathRoot) {
        String fullName = pathRoot + "/" + ".gitignore";
        try {
            File file = new File(fullName);
            super.saveFile(file, "gitignore.ftl", null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return fullName;
    }
}
