// 定义包路径，用于组织项目结构，指明这个类是控制器层的一部分
package com.zzylml.nursing.controller;

// 导入Java标准库中的List接口，用于存储护理项目列表数据
import java.util.List;
// 导入Java Servlet HTTP响应类，用于处理HTTP响应，特别是在导出Excel时使用
import javax.servlet.http.HttpServletResponse;
// 导入Spring Security的预授权注解，用于方法级别的权限控制
import org.springframework.security.access.prepost.PreAuthorize;
// 导入Spring自动装配注解，用于注入服务实例
import org.springframework.beans.factory.annotation.Autowired;
// 导入Spring MVC的GET请求映射注解，用于处理HTTP GET请求
import org.springframework.web.bind.annotation.GetMapping;
// 导入Spring MVC的POST请求映射注解，用于处理HTTP POST请求
import org.springframework.web.bind.annotation.PostMapping;
// 导入Spring MVC的PUT请求映射注解，用于处理HTTP PUT请求
import org.springframework.web.bind.annotation.PutMapping;
// 导入Spring MVC的DELETE请求映射注解，用于处理HTTP DELETE请求
import org.springframework.web.bind.annotation.DeleteMapping;
// 导入Spring MVC的路径变量注解，用于获取URL中的参数
import org.springframework.web.bind.annotation.PathVariable;
// 导入Spring MVC的请求体注解，用于接收JSON格式的请求体数据
import org.springframework.web.bind.annotation.RequestBody;
// 导入Spring MVC的请求映射注解，用于定义控制器的基础URL路径
import org.springframework.web.bind.annotation.RequestMapping;
// 导入Spring MVC的REST控制器注解，标识这是一个RESTful风格的控制器
import org.springframework.web.bind.annotation.RestController;
// 导入自定义日志注解，用于记录操作日志
import com.zzylml.common.annotation.Log;
// 导入基础控制器类，提供通用的控制器功能
import com.zzylml.common.core.controller.BaseController;
// 导入统一响应结果类，用于封装API响应数据
import com.zzylml.common.core.domain.AjaxResult;
// 导入业务操作类型枚举，用于日志记录中指定操作类型
import com.zzylml.common.enums.BusinessType;
// 导入护理项目领域模型类，表示护理项目的数据结构
import com.zzylml.nursing.domain.NursingProject;
// 导入护理项目服务接口，定义护理项目的业务逻辑
import com.zzylml.nursing.service.INursingProjectService;
// 导入Excel工具类，用于导出Excel文件
import com.zzylml.common.utils.poi.ExcelUtil;
// 导入表格数据信息类，用于封装分页查询结果
import com.zzylml.common.core.page.TableDataInfo;

/**
 * 护理项目Controller
 * 
 * @author ruoyi
 * @date 2025-06-04
 */
// 标识这是一个RESTful风格的控制器，响应会自动序列化为JSON格式
@RestController
// 定义这个控制器处理的基础URL路径为"/nursing/project"
@RequestMapping("/nursing/project")
// 定义护理项目控制器类，继承自基础控制器，获得分页、返回结果等通用功能
public class NursingProjectController extends BaseController
{
    // 使用Spring自动装配注入护理项目服务实例，用于处理业务逻辑
    @Autowired
    private INursingProjectService nursingProjectService;

    /**
     * 查询护理项目列表
     */
    // 权限校验：只有拥有"nursing:project:list"权限的用户才能访问此方法
    @PreAuthorize("@ss.hasPermi('nursing:project:list')")
    // 映射GET请求的"/list"路径，用于获取护理项目列表
    @GetMapping("/list")
    // 定义list方法，接收护理项目查询条件，返回表格数据信息
    public TableDataInfo list(NursingProject nursingProject)
    {
        // 启动分页查询，从BaseController继承的方法，用于设置分页参数
        startPage();
        // 调用服务层方法，根据查询条件获取护理项目列表
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        // 将查询结果封装为表格数据信息并返回，包含分页信息和数据列表
        return getDataTable(list);
    }

    /**
     * 导出护理项目列表
     */
    // 权限校验：只有拥有"nursing:project:export"权限的用户才能访问此方法
    @PreAuthorize("@ss.hasPermi('nursing:project:export')")
    // 记录日志：标题为"护理项目"，业务类型为"导出"
    @Log(title = "护理项目", businessType = BusinessType.EXPORT)
    // 映射POST请求的"/export"路径，用于导出护理项目列表
    @PostMapping("/export")
    // 定义export方法，接收HTTP响应对象和护理项目查询条件，无返回值（数据通过响应流返回）
    public void export(HttpServletResponse response, NursingProject nursingProject)
    {
        // 调用服务层方法，根据查询条件获取护理项目列表
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        // 创建Excel工具实例，指定处理的实体类为NursingProject
        ExcelUtil<NursingProject> util = new ExcelUtil<NursingProject>(NursingProject.class);
        // 调用Excel工具的导出方法，将列表数据导出为Excel文件，并通过HTTP响应返回
        util.exportExcel(response, list, "护理项目数据");
    }

    /**
     * 获取护理项目详细信息
     */
    // 权限校验：只有拥有"nursing:project:query"权限的用户才能访问此方法
    @PreAuthorize("@ss.hasPermi('nursing:project:query')")
    // 映射GET请求的"/{id}"路径，用于获取指定ID的护理项目详情，路径中的id为参数
    @GetMapping(value = "/{id}")
    // 定义getInfo方法，接收路径中的id参数，返回统一响应结果
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        // 调用服务层方法，根据ID查询护理项目，并使用success方法包装为统一成功响应结果返回
        return success(nursingProjectService.selectNursingProjectById(id));
    }

    /**
     * 新增护理项目
     */
    // 权限校验：只有拥有"nursing:project:add"权限的用户才能访问此方法
    @PreAuthorize("@ss.hasPermi('nursing:project:add')")
    // 记录日志：标题为"护理项目"，业务类型为"新增"
    @Log(title = "护理项目", businessType = BusinessType.INSERT)
    // 映射POST请求的根路径，用于新增护理项目
    @PostMapping
    // 定义add方法，接收请求体中的护理项目对象，返回统一响应结果
    public AjaxResult add(@RequestBody NursingProject nursingProject)
    {
        // 调用服务层方法，插入护理项目，并使用toAjax方法将操作结果（受影响行数）转换为统一响应结果返回
        return toAjax(nursingProjectService.insertNursingProject(nursingProject));
    }

    /**
     * 修改护理项目
     */
    // 权限校验：只有拥有"nursing:project:edit"权限的用户才能访问此方法
    @PreAuthorize("@ss.hasPermi('nursing:project:edit')")
    // 记录日志：标题为"护理项目"，业务类型为"修改"
    @Log(title = "护理项目", businessType = BusinessType.UPDATE)
    // 映射PUT请求的根路径，用于修改护理项目
    @PutMapping
    // 定义edit方法，接收请求体中的护理项目对象，返回统一响应结果
    public AjaxResult edit(@RequestBody NursingProject nursingProject)
    {
        // 调用服务层方法，更新护理项目，并使用toAjax方法将操作结果（受影响行数）转换为统一响应结果返回
        return toAjax(nursingProjectService.updateNursingProject(nursingProject));
    }

    /**
     * 删除护理项目
     */
    // 权限校验：只有拥有"nursing:project:remove"权限的用户才能访问此方法
    @PreAuthorize("@ss.hasPermi('nursing:project:remove')")
    // 记录日志：标题为"护理项目"，业务类型为"删除"
    @Log(title = "护理项目", businessType = BusinessType.DELETE)
    // 映射DELETE请求的"/{ids}"路径，用于删除指定ID的护理项目，路径中的ids为参数
	@DeleteMapping("/{ids}")
    // 定义remove方法，接收路径中的ids参数（可以是多个ID），返回统一响应结果
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        // 调用服务层方法，根据ID数组批量删除护理项目，并使用toAjax方法将操作结果（受影响行数）转换为统一响应结果返回
        return toAjax(nursingProjectService.deleteNursingProjectByIds(ids));
    }
}
