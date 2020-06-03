package cn.luyijun.fitness.api.service.entity;


import java.util.Date;

public class MallShopActivity {
    //活动案例编号
    private Integer activityId;
    //活动案例编号
    private String activityName;
    //商铺编号
    private String shopCode;
    //品牌名称
    private String brandName;
    //品牌编号
    private String brandCode;
    //业态
    private String businessTypeCode;
    //活动类型
    private String activityPurpose;
    //当前档期开始日期（包含本日期）
    private Date beginDate;
    //当前档期结束日期（包含本日期）
    private Date endDate;
    //活动点评
    private String activityComment;
    //活动列表图片
    private String activityListPic;
    //活动图片
    private String activityPic;
    //排序，越大越在前
    private Integer sortOrder;
    //最近一次审核记录
    private Integer acticityMiddleId;
    //最后修改人编号
    private Integer lastModifyId;
    //最后修改时间
    private Date lastModifyTime;
    //saas创建人编号
    private Integer customCrtId;
    //saas创建时间
    private Date customCrtTime;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBusinesstypeCode() {
        return businessTypeCode;
    }

    public void setBusinesstypeCode(String businesstypeCode) {
        this.businessTypeCode = businesstypeCode;
    }

    public String getActivityPurpose() {
        return activityPurpose;
    }

    public void setActivityPurpose(String activityPurpose) {
        this.activityPurpose = activityPurpose;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getActivityComment() {
        return activityComment;
    }

    public void setActivityComment(String activityComment) {
        this.activityComment = activityComment;
    }

    public String getActicityListPic() {
        return activityListPic;
    }

    public void setActicityListPic(String acticityListPic) {
        this.activityListPic = acticityListPic;
    }

    public String getActicityPic() {
        return activityPic;
    }

    public void setActicityPic(String acticityPic) {
        this.activityPic = acticityPic;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getActicityMiddleId() {
        return acticityMiddleId;
    }

    public void setActicityMiddleId(Integer acticityMiddleId) {
        this.acticityMiddleId = acticityMiddleId;
    }

    public Integer getLastModifyId() {
        return lastModifyId;
    }

    public void setLastModifyId(Integer lastModifyId) {
        this.lastModifyId = lastModifyId;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getCustomCrtId() {
        return customCrtId;
    }

    public void setCustomCrtId(Integer customCrtId) {
        this.customCrtId = customCrtId;
    }

    public Date getCustomCrtTime() {
        return customCrtTime;
    }

    public void setCustomCrtTime(Date customCrtTime) {
        this.customCrtTime = customCrtTime;
    }
}
