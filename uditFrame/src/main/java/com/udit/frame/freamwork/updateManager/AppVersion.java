package com.udit.frame.freamwork.updateManager;

import java.io.Serializable;

public class AppVersion implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6900225380904926014L;
    private String id;

    private String name;

    private String url;

    private String version;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
