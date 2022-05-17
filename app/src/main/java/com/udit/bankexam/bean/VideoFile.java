package com.udit.bankexam.bean;

import java.io.Serializable;

public class VideoFile implements Serializable{

	private static final long serialVersionUID = 4421851050662524227L;


	private boolean isExpand = false;


	private boolean isLast = false;

	private boolean isfirst = false;
	private String cid;

	private String id;

	private String ordid;

	private String name;

	private String points;

	private String afile;

	private String eid;

	private String vtime;

	public boolean isExpand() {
		return isExpand;
	}

	public void setExpand(boolean expand) {
		isExpand = expand;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean last) {
		isLast = last;
	}

	public boolean isfirst() {
		return isfirst;
	}

	public void setIsfirst(boolean isfirst) {
		this.isfirst = isfirst;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrdid() {
		return ordid;
	}

	public void setOrdid(String ordid) {
		this.ordid = ordid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getAfile() {
		return afile;
	}

	public void setAfile(String afile) {
		this.afile = afile;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getVtime() {
		return vtime;
	}

	public void setVtime(String vtime) {
		this.vtime = vtime;
	}
}
