package cn.hqwx.autogen.alap.cloud.service.autogen;

import cn.hqwx.autogen.alap.cloud.constants.GlobalConstant;
import cn.hqwx.autogen.alap.cloud.model.ApplicationMetadata;
import cn.hqwx.autogen.alap.cloud.model.ProjectMetadata;
import cn.hqwx.autogen.alap.cloud.service.autogen.engine.*;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-26
 */
public class AutoGenerator {
    private ApplicationMetadata applicationMetadata = null;
    private ProjectMetadata projectMetadata = null;
    private String packagePath;
    private String projectPath;
    private String projectRootPath;
    private String projectResourcePath;
    private String projectTestPath;

    public void setApplicationMetadata(ApplicationMetadata applicationMetadata) {
        this.applicationMetadata = applicationMetadata;
    }

    public void setProjectMetadata(ProjectMetadata projectMetadata) {
        this.projectMetadata = projectMetadata;
    }

    public void execute() {
        if (Objects.isNull(this.applicationMetadata)) {
            throw new NullPointerException("ApplicationMetadata is null");
        }
        if (Objects.isNull(this.projectMetadata)) {
            throw new NullPointerException("ProjectMetadata is null");
        }

        System.out.println("==========================准备生成工程...==========================");

        //1.创建.gitignore
        if (!this.createGitignore()) {
            System.out.println("createGitignore failed");
            return;
        }

        //2.创建 pom.xml
        if (!this.createPom()) {
            System.out.println("createPom failed");
            return;
        }
        //3.创建application.java
        if (!this.createApplication()) {
            System.out.println("createApplication failed");
            return;
        }

        //4.创建bootstrap.yml
        if (!this.createBootstrapYml()) {
            System.out.println("createBootstrapYml failed");
            return;
        }
        if (!this.createResources()) {
            System.out.println("createResources failed");
            return;
        }

        //5.创建xxxTest.java
        if (!this.createTestApi()) {
            System.out.println("createTestApi failed");
            return;
        }

        //6.创建配置
        if (!this.createConfigs()) {
            System.out.println("createConfigs failed");
            return;
        }

        //7.创建DDD领域框架
        if (!this.createDDDModul()) {
            System.out.println("createDDDModul failed");
            return;
        }

        System.out.println();
        System.out.println(" 工程生成成功，目录: " + this.getProjectPath());
        System.out.println("==========================工程生成完成！！！==========================");
    }

    private String getPackagePath() {
        if (!StringUtils.hasText(this.packagePath)) {
            this.packagePath = this.applicationMetadata.getPackageName().replace(".", "/");
        }

        return this.packagePath;
    }

    private String getProjectPath() {
        if (!StringUtils.hasText(this.projectPath)) {
            this.projectPath = this.applicationMetadata.getProjectPath() + "/"
                    + this.projectMetadata.getArtifact();
        }

        return this.projectPath;
    }

    private String getProjectRootPath() {
        if (!StringUtils.hasText(this.projectRootPath)) {
            this.projectRootPath = this.getProjectPath()
                    + GlobalConstant.BasePath_Src + "/"
                    + this.getPackagePath();
        }

        return this.projectRootPath;
    }

    private String getProjectResourcePath() {
        if (!StringUtils.hasText(this.projectResourcePath)) {
            this.projectResourcePath = this.getProjectPath()
                    + GlobalConstant.BasePath_Resource;
        }

        return this.projectResourcePath;
    }

    private String getProjectTestPath() {
        if (!StringUtils.hasText(this.projectTestPath)) {
            this.projectTestPath = this.getProjectPath()
                    + GlobalConstant.BasePath_TestSrc + "/"
                    + this.getPackagePath();
        }

        return this.projectTestPath;
    }


    // beg 创建项目工程  /////////////////////////////////////////////////////////////////////////////////////
    private boolean createGitignore() {
        GitignoreEngine engine = new GitignoreEngine();
        String fileName = engine.execute(this.getProjectPath());
        System.out.println("Gitignore: " + fileName);
        return StringUtils.hasText(fileName);
    }

    private boolean createPom() {
        PomEngine engine = new PomEngine();
        String fileName = engine.execute(this.getProjectPath(), this.applicationMetadata, this.projectMetadata);
        System.out.println("Pom: " + fileName);
        return StringUtils.hasText(fileName);
    }

    private boolean createApplication() {
        ApplicationEngine engine = new ApplicationEngine();
        String fileName = engine.execute(this.getProjectRootPath(), this.applicationMetadata);
        System.out.println("Application: " + fileName);
        return StringUtils.hasText(fileName);
    }

    private boolean createBootstrapYml() {
        YmlEngine engine = new YmlEngine();
        String fileName = engine.execute(this.getProjectResourcePath(), this.applicationMetadata, this.projectMetadata);
        System.out.println("bootstrap.yml: " + fileName);
        return StringUtils.hasText(fileName);
    }

    private boolean createResources(){
        ResourcesEngine engine = new ResourcesEngine();
        String filePath = engine.execute(this.getProjectResourcePath(), this.applicationMetadata, this.projectMetadata);
        System.out.println("Config Path: " + filePath);
        return StringUtils.hasText(filePath);
    }

    private boolean createTestApi() {
        if (!this.applicationMetadata.getHasTest()) {
            return true;
        }

        TestApiEngine engine = new TestApiEngine();
        String fileName = engine.execute(this.getProjectTestPath(), this.applicationMetadata);
        System.out.println("Test: " + fileName);
        return StringUtils.hasText(fileName);
    }

    private boolean createConfigs() {
        ConfigsEngine engine = new ConfigsEngine();
        String filePath = engine.execute(this.getProjectRootPath(), this.applicationMetadata, this.projectMetadata);
        System.out.println("Config Path: " + filePath);
        return StringUtils.hasText(filePath);
    }

    private boolean createDDDModul() {
        DDDModulEngine engine = new DDDModulEngine();
        String filePath = engine.execute(this.getProjectRootPath(), this.applicationMetadata, this.projectMetadata);
        System.out.println("DDD Modul " + filePath);
        return StringUtils.hasText(filePath);
    }
    // end 创建项目工程  /////////////////////////////////////////////////////////////////////////////////////
}
