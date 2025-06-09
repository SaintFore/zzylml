package com.zzylml.nursingplan.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zzylml.common.annotation.Excel;
import com.zzylml.common.core.domain.BaseEntity;

/**
 * 护理计划对象 nursing_plan
 * 
 * @author ruoyi
 * @date 2025-06-06
 */
public class NursingPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 排序号 */
    @Excel(name = "排序号")
    private Long sortNo;

    /** 名称 */
    @Excel(name = "名称")
    private String planName;

    /** 状态 0禁用 1启用 */
    @Excel(name = "状态 0禁用 1启用")
    private Long status;

    /** 护理计划和项目关联信息 */
    private List<NursingProjectPlan> nursingProjectPlanList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setSortNo(Long sortNo) 
    {
        this.sortNo = sortNo;
    }

    public Long getSortNo() 
    {
        return sortNo;
    }

    public void setPlanName(String planName) 
    {
        this.planName = planName;
    }

    public String getPlanName() 
    {
        return planName;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public List<NursingProjectPlan> getNursingProjectPlanList()
    {
        return nursingProjectPlanList;
    }

    public void setNursingProjectPlanList(List<NursingProjectPlan> nursingProjectPlanList)
    {
        this.nursingProjectPlanList = nursingProjectPlanList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sortNo", getSortNo())
            .append("planName", getPlanName())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .append("nursingProjectPlanList", getNursingProjectPlanList())
            .toString();
    }
}
