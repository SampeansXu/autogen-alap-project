package cn.hqwx.autogen.alap.cloud;

import cn.hqwx.autogen.alap.cloud.model.ApplicationMetadata;
import cn.hqwx.autogen.alap.cloud.model.ProjectMetadata;
import cn.hqwx.autogen.alap.cloud.service.autogen.AutoGenerator;

public class AutoGeneratorCloudApp {

    public static void main(String[] args) {
        createDemo(args);
    }

    private static void createDemo(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        ApplicationMetadata applicationMetadata = new ApplicationMetadata();
//        applicationMetadata.setProjectPath(System.getProperty("user.dir"));
        applicationMetadata.setPackageName("com.example.demo");
        applicationMetadata.setClassName("DemoApp");
//        applicationMetadata.setHasTest(true);
//        applicationMetadata.setHasDB(true);
//        applicationMetadata.setHasCache(true);
        applicationMetadata.setHasUndertow(true); //使用undertow, 默认:true
        autoGenerator.setApplicationMetadata(applicationMetadata);

        ProjectMetadata projectMetadata = new ProjectMetadata();
        projectMetadata.setGroup("com.example");
        projectMetadata.setArtifact("demo-1");
        projectMetadata.setName("demo-1");
        projectMetadata.setDescription("This demo for autogenerator");
//        projectMetadata.setVersion("0.0.1-SNAPSHOT");
        projectMetadata.setAlias("用例");
        autoGenerator.setProjectMetadata(projectMetadata);

        autoGenerator.execute();
    }

}
