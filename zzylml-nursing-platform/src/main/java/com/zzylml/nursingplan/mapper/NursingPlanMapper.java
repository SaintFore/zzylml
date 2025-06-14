package com.zzylml.nursingplan.mapper;

import java.util.List;
import com.zzylml.nursingplan.domain.NursingPlan;
import com.zzylml.nursingplan.domain.NursingProjectPlan;

/**
 * 护理计划Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-06
 */
public interface NursingPlanMapper 
{
    /**
     * 查询护理计划
     * 
     * @param id 护理计划主键
     * @return 护理计划
     */
    public NursingPlan selectNursingPlanById(Long id);

    /**
     * 查询护理计划列表
     * 
     * @param nursingPlan 护理计划
     * @return 护理计划集合
     */
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan);

    /**
     * 新增护理计划
     * 
     * @param nursingPlan 护理计划
     * @return 结果
     */
    public int insertNursingPlan(NursingPlan nursingPlan);

    /**
     * 修改护理计划
     * 
     * @param nursingPlan 护理计划
     * @return 结果
     */
    public int updateNursingPlan(NursingPlan nursingPlan);

    /**
     * 删除护理计划
     * 
     * @param id 护理计划主键
     * @return 结果
     */
    public int deleteNursingPlanById(Long id);

    /**
     * 批量删除护理计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingPlanByIds(Long[] ids);

    /**
     * 批量删除护理计划和项目关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingProjectPlanByProjectIds(Long[] ids);
    
    /**
     * 批量新增护理计划和项目关联
     * 
     * @param nursingProjectPlanList 护理计划和项目关联列表
     * @return 结果
     */
    public int batchNursingProjectPlan(List<NursingProjectPlan> nursingProjectPlanList);
    

    /**
     * 通过护理计划主键删除护理计划和项目关联信息
     * 
     * @param id 护理计划ID
     * @return 结果
     */
    public int deleteNursingProjectPlanByProjectId(Long id);
}
