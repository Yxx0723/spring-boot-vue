<template>
  <div style="padding: 10px">
<!--    功能区-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
<!--    搜索框-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入内容" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
<!--    主体内容-->
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column label="ID" sortable="" prop="id" />
      <el-table-column label="用户名" prop="username" />
      <el-table-column label="昵称" prop="nickName" />
      <el-table-column label="年龄" prop="age" />
      <el-table-column label="性别" prop="sex" />
      <el-table-column label="地址" prop="address" />
      <el-table-column label="操作">
        <template #default="scope">
            <el-button @click="handleEdit(scope.row)">修改</el-button>
            <el-popconfirm @confirm="handleDelete(scope.row.id, scope.$index)" title="确定要删除吗?">
                <template #reference>
                    <el-button type="danger">删除</el-button>
                </template>
            </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
<!--      分页-->
      <div style="margin: 10px 0">
          <el-pagination
                  v-model:currentPage="currentPage"
                  v-model:page-size="pageSize"
                  :page-sizes="[5, 10, 15, 20]"
                  :total="total"
                  layout="total, sizes, prev, pager, next, jumper"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
          />

          <el-dialog v-model="dialogVisible" title="提示" width="30%">
              <el-form :model="form" label-width="120px">
                  <el-form-item label="用户名">
                      <el-input v-model="form.username" style="width: 80%" />
                  </el-form-item>
                  <el-form-item label="昵称">
                      <el-input v-model="form.nickName" style="width: 80%" />
                  </el-form-item>
                  <el-form-item label="年龄">
                      <el-input v-model="form.age" style="width: 80%" />
                  </el-form-item>
                  <el-form-item label="性别">
                      <el-radio v-model="form.sex" label="1" >男</el-radio>
                      <el-radio v-model="form.sex" label="2" >女</el-radio>
                      <el-radio v-model="form.sex" label="3" >未知</el-radio>
                  </el-form-item>
                  <el-form-item label="地址">
                      <el-input v-model="form.address" style="width: 80%" />
                  </el-form-item>
              </el-form>
              <template #footer>
                  <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save">确 定</el-button>
                  </span>
              </template>
          </el-dialog>
      </div>
  </div>
</template>


<script>
import request from "@/util/request";

export default {
  name: 'HomeView',
  components: {

  },
  data(){
    return {
        search :  '',
        dialogVisible : false,
        form : {},
        total: 0,
        currentPage: 1,  //显示当前页数
        pageSize: 5, //每页多少条数据
        tableData:[]
    }
  },
    created() {
        this.load()
    },
    methods: {
      load() {
          request.get("/api/user",{
              params:{
                  pageNum: this.currentPage,
                  pageSize: this.pageSize,
                  search: this.search
              }
          }).then(res => {
              this.tableData = res.data.records
              this.total = res.data.total
          });
      },
        add() {
            this.dialogVisible = true;
            this.form = {};
        },
        save() {
          if (this.form.id){
              request.put("/api/user", this.form).then(res => {
                  if (res.code === '0'){
                    this.$message({
                        type: "success",
                        message: "更新成功"
                    })
                  } else {
                      this.$message({
                          type: "error",
                          message: res.msg
                      })
                  }
                  this.load()
                  this.dialogVisible = false
              })
          } else {
              request.post("/api/user", this.form).then(res => {
                  if (res.code === '0'){
                      this.$message({
                          type: "success",
                          message: "新增成功"
                      })
                  } else {
                      this.$message({
                          type: "error",
                          message: res.msg
                      })
                  }
              })
              this.load()
              this.dialogVisible = false
          }
        },
        handleEdit(row) {
            this.form = JSON.parse(JSON.stringify(row)) // 深拷贝
            this.dialogVisible = true
        },
        handleSizeChange() {   //改变每页的个数时触发
            this.load()
        },
        handleCurrentChange() {   //改变页码时触发
          this.load()
        },
        handleDelete(id,index) {
          request.delete("/api/user/" + id).then(res => {
              if (res.code === '0') {
                  this.$message({
                      type: "success",
                      message: "删除成功"
                  })
              } else {
                  this.$message({
                      type: "error",
                      message: "删除失败"
                  })
              }
          })
            this.load()
        }
    }
}
</script>

