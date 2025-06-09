package com.zzylml.nursingplan.service.impl;

import java.util.List;
import com.zzylml.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.zzylml.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.zzylml.nursingplan.domain.NursingProjectPlan;
import com.zzylml.nursingplan.mapper.NursingPlanMapper;
import com.zzylml.nursingplan.domain.NursingPlan;
import com.zzylml.nursingplan.service.INursingPlanService;

/**
 * 护理计划Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-06
 */
@Service
public class NursingPlanServiceImpl implements INursingPlanService 
{
    @Autowired
    private NursingPlanMapper nursingPlanMapper;

    /**
     * 查询护理计划
     * 
     * @param id 护理计划主键
     * @return 护理计划
     */
    @Override
    public NursingPlan selectNursingPlanById(Long id)
    {
        return nursingPlanMapper.selectNursingPlanById(id);
    }

    /**
     * 查询护理计划列表
     * 
     * @param nursingPlan 护理计划
     * @return 护理计划
     */
    @Override
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan)
    {
        return nursingPlanMapper.selectNursingPlanList(nursingPlan);
    }

    /**
     * 新增护理计划
     * 
     * @param nursingPlan 护理计划
     * @return 结果
     */
    @Transactional
    @Override
    public int insertNursingPlan(NursingPlan nursingPlan)
    {
        nursingPlan.setCreateTime(DateUtils.getNowDate());
        int rows = nursingPlanMapper.insertNursingPlan(nursingPlan);
        insertNursingProjectPlan(nursingPlan);
        return rows;
    }

    /**
     * 修改护理计划
     * 
     * @param nursingPlan 护理计划
     * @return 结果
     */
    @Transactional
    @Override
    public int updateNursingPlan(NursingPlan nursingPlan)
    {
        nursingPlan.setUpdateTime(DateUtils.getNowDate());
        nursingPlanMapper.deleteNursingProjectPlanByProjectId(nursingPlan.getId());
        insertNursingProjectPlan(nursingPlan);
        return nursingPlanMapper.updateNursingPlan(nursingPlan);
    }

    /**
     * 批量删除护理计划
     * 
     * @param ids 需要删除的护理计划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNursingPlanByIds(Long[] ids)
    {
        nursingPlanMapper.deleteNursingProjectPlanByProjectIds(ids);
        return nursingPlanMapper.deleteNursingPlanByIds(ids);
    }

    /**
     * 删除护理计划信息
     * 
     * @param id 护理计划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNursingPlanById(Long id)
    {
        nursingPlanMapper.deleteNursingProjectPlanByProjectId(id);
        return nursingPlanMapper.deleteNursingPlanById(id);
    }

    /**
     * 新增护理计划和项目关联信息
     * 
     * @param nursingPlan 护理计划对象
     */
    public void insertNursingProjectPlan(NursingPlan nursingPlan)
    {
        List<NursingProjectPlan> nursingProjectPlanList = nursingPlan.getNursingProjectPlanList();
        Long id = nursingPlan.getId();
        if (StringUtils.isNotNull(nursingProjectPlanList))
        {
            List<NursingProjectPlan> list = new ArrayList<NursingProjectPlan>();
            for (NursingProjectPlan nursingProjectPlan : nursingProjectPlanList)
            {
                nursingProjectPlan.setProjectId(id);
                list.add(nursingProjectPlan);
            }
            if (list.size() > 0)
            {
                nursingPlanMapper.batchNursingProjectPlan(list);
            }
        }
    }
}
