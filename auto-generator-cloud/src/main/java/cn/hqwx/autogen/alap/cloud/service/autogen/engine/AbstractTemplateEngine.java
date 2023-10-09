package cn.hqwx.autogen.alap.cloud.service.autogen.engine;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-25
 */
public abstract class AbstractTemplateEngine {
    private static final String ENCODING = "UTF-8";
    private static Configuration config;

    static {
        try {
            config = new Configuration(Configuration.VERSION_2_3_30);
            File templates = new File(System.getProperty("user.dir") + "/auto-generator-cloud/src/main/resources/templates");
            config.setDirectoryForTemplateLoading(templates);
            config.setDefaultEncoding(ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Template getTemplate(String ftl) throws IOException {
        return config.getTemplate(ftl, ENCODING);
    }

    protected void createDir(String path) {
        if (!StringUtils.hasText(path)) {
            return;
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    protected void saveFile(File file, String ftl, Object dataModel) throws IOException, TemplateException {
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
        try {
            this.getTemplate(ftl).process(dataModel, out);
        } finally {
            out.flush();
            out.close();
        }
    }
}
