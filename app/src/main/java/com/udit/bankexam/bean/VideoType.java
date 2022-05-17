package com.udit.bankexam.bean;

import java.io.Serializable;
import java.util.List;


public class VideoType implements Serializable{

	private static final long serialVersionUID = -5686284193203525210L;


	private boolean isExpand = false;


	private boolean isLast = false;

	private boolean isfirst = false;

	private String id;

	private String ordid;

	private String screen;

	private String pcscreen;

	private String allscreen;

	private String name;

	private String vtype;

	private String price;

	private String icount;

	private String isget;

	private String afile;

	private String videoIdAli;//視頻文件新地址

	private String cid;

	private String eid;

	private String points;

	private String vtime;

	private List<VideoType> list_file;

	public String getVideoIdAli() {
		return videoIdAli;
	}

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

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getPcscreen() {
		return pcscreen;
	}

	public void setPcscreen(String pcscreen) {
		this.pcscreen = pcscreen;
	}

	public String getAllscreen() {
		return allscreen;
	}

	public void setAllscreen(String allscreen) {
		this.allscreen = allscreen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIcount() {
		return icount;
	}

	public void setIcount(String icount) {
		this.icount = icount;
	}

	public String getIsget() {
		return isget;
	}

	public void setIsget(String isget) {
		this.isget = isget;
	}

	public String getAfile() {
		return afile;
	}

	public void setAfile(String afile) {
		this.afile = afile;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getVtime() {
		return vtime;
	}

	public void setVtime(String vtime) {
		this.vtime = vtime;
	}

	public List<VideoType> getList_file() {
		return list_file;
	}

	public void setList_file(List<VideoType> list_file) {
		this.list_file = list_file;
	}
}
