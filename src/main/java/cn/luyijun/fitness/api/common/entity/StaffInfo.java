package cn.luyijun.fitness.api.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Qi on 2018/04/16.
 * 员工信息表
 */
public class StaffInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 业主账号ID */
	private Integer staffId;

	/** 所属集团公司编号 */
	private Integer ownerId;


	private String ownerName;

	/** 联系人名称 */
	private String staffName;

	private String staffPosition;

	/** 联系人性别 */
	private Integer staffSex;

	/** 联系人固定电话 */
	private String staffTel;

	/** 联系人手机，用于登录 */
	private String staffMobile;

	/** 联系人EMAIL */
	private String staffEmail;

	/** 头像 */
	private String staffPhoto;

	/** 超级管理员标识  0：否 1：是 */
	private Integer staffType;

	/** 账号状态，0未启用，1启用，2停用，手机号码在启用状态时唯一 */
	private Integer staffStatus;

	/** 上次登录时间 */
	private Date lastLoginTime;

	/** 最后登录IP */
	private String lastLoginIp;

	/** 创建人ID */
	private Integer customCrtId;

	private String crtName;


	/** 创建时间 */
	private Date customCrtTime;

	/** 最后修改人ID */
	private Integer lastCustomModifyId;

	/** 最后修改时间 */
	private Date lastCustomModifyTime;


	/** 最后修改名称 */
	private String lastModifyName;

	private String privilegeContent;

	/**
	 * 员工代码,手动输入
	 */
	private String staffCode;
	/**
	 * 部门
	 */
	private String staffDept;

	private String smsCode;

	private Integer paasStatus;//是否开通paas

	public StaffInfo() {

	}

	public StaffInfo(Integer staffId, String lastLoginIp) {
		this.staffId = staffId;
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getStaffSex() {
		return staffSex;
	}

	public void setStaffSex(Integer staffSex) {
		this.staffSex = staffSex;
	}

	public String getStaffTel() {
		return staffTel;
	}

	public void setStaffTel(String staffTel) {
		this.staffTel = staffTel;
	}

	public String getStaffMobile() {
		return staffMobile;
	}

	public void setStaffMobile(String staffMobile) {
		this.staffMobile = staffMobile;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public String getStaffPhoto() {
		return staffPhoto;
	}

	public void setStaffPhoto(String staffPhoto) {
		this.staffPhoto = staffPhoto;
	}

	public Integer getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(Integer staffStatus) {
		this.staffStatus = staffStatus;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getCustomCrtTime() {
		return customCrtTime;
	}

	public void setCustomCrtTime(Date customCrtTime) {
		this.customCrtTime = customCrtTime;
	}

	public Date getLastCustomModifyTime() {
		return lastCustomModifyTime;
	}

	public void setLastCustomModifyTime(Date lastCustomModifyTime) {
		this.lastCustomModifyTime = lastCustomModifyTime;
	}

	public String getLastModifyName() {
		return lastModifyName;
	}

	public void setLastModifyName(String lastModifyName) {
		this.lastModifyName = lastModifyName;
	}

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCrtName() {
        return crtName;
    }

    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

	public String getStaffPosition() {
		return staffPosition;
	}

	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}

	public Integer getCustomCrtId() {
		return customCrtId;
	}

	public void setCustomCrtId(Integer customCrtId) {
		this.customCrtId = customCrtId;
	}

	public Integer getLastCustomModifyId() {
		return lastCustomModifyId;
	}

	public void setLastCustomModifyId(Integer lastCustomModifyId) {
		this.lastCustomModifyId = lastCustomModifyId;
	}

	public String getPrivilegeContent() {
		return privilegeContent;
	}

	public void setPrivilegeContent(String privilegeContent) {
		this.privilegeContent = privilegeContent;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffDept() {
		return staffDept;
	}

	public void setStaffDept(String staffDept) {
		this.staffDept = staffDept;
	}

	public Integer getStaffType() {
		return staffType;
	}

	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public Integer getPaasStatus() {
		return paasStatus;
	}

	public void setPaasStatus(Integer paasStatus) {
		this.paasStatus = paasStatus;
	}
}

