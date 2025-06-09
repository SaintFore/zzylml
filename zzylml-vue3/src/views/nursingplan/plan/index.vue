<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="排序号" prop="sortNo">
        <el-input
          v-model="queryParams.sortNo"
          placeholder="请输入排序号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="planName">
        <el-input
          v-model="queryParams.planName"
          placeholder="请输入名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['nursingplan:plan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['nursingplan:plan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['nursingplan:plan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['nursingplan:plan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="排序号" align="center" prop="sortNo" />
      <el-table-column label="名称" align="center" prop="planName" />
      <el-table-column label="状态 0禁用 1启用" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['nursingplan:plan:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['nursingplan:plan:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改护理计划对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="planRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="排序号" prop="sortNo">
          <el-input v-model="form.sortNo" placeholder="请输入排序号" />
        </el-form-item>
        <el-form-item label="名称" prop="planName">
          <el-input v-model="form.planName" placeholder="请输入名称" />
        </el-form-item>
        <el-divider content-position="center">护理计划和项目关联信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddNursingProjectPlan">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteNursingProjectPlan">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="nursingProjectPlanList" :row-class-name="rowNursingProjectPlanIndex" @selection-change="handleNursingProjectPlanSelectionChange" ref="nursingProjectPlan">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="计划id" prop="planId" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.planId" placeholder="请输入计划id" />
            </template>
          </el-table-column>
          <el-table-column label="计划执行时间" prop="executeTime" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.executeTime" placeholder="请输入计划执行时间" />
            </template>
          </el-table-column>
          <el-table-column label="执行周期 0 天 1 周 2月" prop="executeCycle" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.executeCycle" placeholder="请输入执行周期 0 天 1 周 2月" />
            </template>
          </el-table-column>
          <el-table-column label="执行频次" prop="executeFrequency" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.executeFrequency" placeholder="请输入执行频次" />
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="remark" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.remark" placeholder="请输入备注" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Plan">
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/nursingplan/plan"

const { proxy } = getCurrentInstance()

const planList = ref([])
const nursingProjectPlanList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const checkedNursingProjectPlan = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    sortNo: null,
    planName: null,
    status: null,
  },
  rules: {
    planName: [
      { required: true, message: "名称不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态 0禁用 1启用不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询护理计划列表 */
function getList() {
  loading.value = true
  listPlan(queryParams.value).then(response => {
    planList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    sortNo: null,
    planName: null,
    status: null,
    createTime: null,
    updateTime: null,
    createBy: null,
    updateBy: null,
    remark: null
  }
  nursingProjectPlanList.value = []
  proxy.resetForm("planRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加护理计划"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getPlan(_id).then(response => {
    form.value = response.data
    nursingProjectPlanList.value = response.data.nursingProjectPlanList
    open.value = true
    title.value = "修改护理计划"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["planRef"].validate(valid => {
    if (valid) {
      form.value.nursingProjectPlanList = nursingProjectPlanList.value
      if (form.value.id != null) {
        updatePlan(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addPlan(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除护理计划编号为"' + _ids + '"的数据项？').then(function() {
    return delPlan(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 护理计划和项目关联序号 */
function rowNursingProjectPlanIndex({ row, rowIndex }) {
  row.index = rowIndex + 1
}

/** 护理计划和项目关联添加按钮操作 */
function handleAddNursingProjectPlan() {
  let obj = {}
  obj.planId = ""
  obj.executeTime = ""
  obj.executeCycle = ""
  obj.executeFrequency = ""
  obj.remark = ""
  nursingProjectPlanList.value.push(obj)
}

/** 护理计划和项目关联删除按钮操作 */
function handleDeleteNursingProjectPlan() {
  if (checkedNursingProjectPlan.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的护理计划和项目关联数据")
  } else {
    const nursingProjectPlans = nursingProjectPlanList.value
    const checkedNursingProjectPlans = checkedNursingProjectPlan.value
    nursingProjectPlanList.value = nursingProjectPlans.filter(function(item) {
      return checkedNursingProjectPlans.indexOf(item.index) == -1
    })
  }
}

/** 复选框选中数据 */
function handleNursingProjectPlanSelectionChange(selection) {
  checkedNursingProjectPlan.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('nursingplan/plan/export', {
    ...queryParams.value
  }, `plan_${new Date().getTime()}.xlsx`)
}

getList()
</script>
