package com.zhn.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "display_name")
    private String displayName;

    private String email;

    private String mobile;

    private String password;
    
    @Transient
    private String role;

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "area_code")
    private String areaCode;

    private Byte status;

    @Column(name = "user_type")
    private Byte userType;

    /**
     * 是否后台用户
     */
    private boolean admin;

    private boolean deleted;

    @Column(name = "create_datetime")
    private Date createDatetime;

    @Column(name = "update_datetime")
    private Date updateDatetime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return display_name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return area_code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return user_type
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * 获取是否后台用户
     *
     * @return admin - 是否后台用户
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * 设置是否后台用户
     *
     * @param admin 是否后台用户
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @return deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return create_datetime
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * @param createDatetime
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * @return update_datetime
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * @param updateDatetime
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}