package com.zhn.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String signature;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_name")
    private String parentName;

    private Integer type;

    private String url;

    private Integer level;

    private String description;

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
     * @return signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
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
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return parent_name
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * @param parentName
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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