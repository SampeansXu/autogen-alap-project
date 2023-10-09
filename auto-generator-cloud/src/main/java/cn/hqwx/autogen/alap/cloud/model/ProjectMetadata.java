package cn.hqwx.autogen.alap.cloud.model;

import cn.hqwx.autogen.alap.cloud.service.autogen.utils.CamelUtil;
import org.springframework.util.StringUtils;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-23
 */
public class ProjectMetadata {
    private String group;
    private String artifact;
    private String version = "0.0.1-SNAPSHOT";
    private String name;
    private String description = "";
    private String camelName;
    private String alias;

    @Override
    public String toString() {
        return "ProjectMetadata{" +
                "group='" + group + '\'' +
                ", artifact='" + artifact + '\'' +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", camelName='" + this.getCamelName() + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getVersion() {
        if (!StringUtils.hasText(this.version)) {
            this.version = "0.0.1-SNAPSHOT";
        }
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCamelName() {
        if (!StringUtils.hasText(this.camelName)) {
            if (StringUtils.hasText(this.name)) {
                this.camelName = CamelUtil.lineToCamel(this.name);
            }else if(StringUtils.hasText(this.artifact)){
                this.camelName = CamelUtil.lineToCamel(this.artifact);
            }
        }
        return this.camelName;
    }

    public void setCamelName(String camelName) {
        this.camelName = camelName;
    }

    public String getAlias() {
        if (!StringUtils.hasText(this.alias)) {
            this.alias = this.getName();
        }
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
