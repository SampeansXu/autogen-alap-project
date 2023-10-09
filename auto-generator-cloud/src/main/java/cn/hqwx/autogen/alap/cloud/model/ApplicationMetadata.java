package cn.hqwx.autogen.alap.cloud.model;

import org.springframework.util.StringUtils;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-23
 */
public class ApplicationMetadata {
    private String packageName;
    private String className;
    private String projectPath;
    private Boolean hasTest = false;
    private Boolean hasDB = false;
    private Boolean hasCache = false;
    private Boolean hasUndertow = true;

    @Override
    public String toString() {
        return "ApplicationMetadata{" +
                "packageName='" + packageName + '\'' +
                ", className='" + className + '\'' +
                ", projectPath='" + projectPath + '\'' +
                ", hasTest='" + hasTest + '\'' +
                ", hasDB='" + hasTest + '\'' +
                ", hasCache='" + hasTest + '\'' +
                ", hasUndertow='" + hasUndertow + '\'' +
                '}';
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProjectPath() {
        if (!StringUtils.hasText(this.projectPath)) {
            this.projectPath = System.getProperty("user.dir");
        }

        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public Boolean getHasTest() {
        return hasTest;
    }

    public void setHasTest(Boolean hasTest) {
        this.hasTest = hasTest;
    }

    public Boolean getHasDB() {
        return hasDB;
    }

    public void setHasDB(Boolean hasDB) {
        this.hasDB = hasDB;
    }

    public Boolean getHasCache() {
        return hasCache;
    }

    public void setHasCache(Boolean hasCache) {
        this.hasCache = hasCache;
    }

    public Boolean getHasUndertow() {
        return hasUndertow;
    }

    public void setHasUndertow(Boolean hasUndertow) {
        this.hasUndertow = hasUndertow;
    }
}
