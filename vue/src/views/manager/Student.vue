<template>
  <div>
    <div class="card" style="margin-bottom: 10px">
      <el-input v-model="data.username" class="search_input" placeholder="search by username" :prefix-icon="Search"/>
<!--      <el-input v-model="data.lastName" class="search_input" placeholder="search by last name" :prefix-icon="Search"/>-->
      <el-input v-model="data.email" class="search_input" placeholder="search by email" :prefix-icon="Search"/>
      <el-button style="margin-left: 10px" type="primary" @click="load">Search</el-button>
      <el-button style="margin: 0 10px" type="info" @click="reset">Reset</el-button>
    </div>

    <div class="card">
      <div style="margin-bottom: 20px">
        <el-button type="primary" @click="showDialog">Add</el-button>
      </div>

      <div>
        <el-table :data="data.studentData" style="width: 100%">
          <el-table-column fixed prop="id" label="id" width="80"/>
          <el-table-column prop="username" label="username" width="170"/>
          <el-table-column prop="firstName" label="First name" width="150"/>
          <el-table-column prop="lastName" label="Last name" width="150"/>
          <el-table-column prop="email" label="Email" width="200"/>
          <el-table-column prop="gender" label="Gender" width="100"/>
          <el-table-column prop="avatar" label="Avatar" width="150"/>
          <el-table-column fixed="right" label="Operation" width="200">
            <template #default="scope">
              <el-button type="primary" plain @click="handleEdit(scope.row)">Edit</el-button>
              <el-button type="danger" plain @click="handleDelete(scope.row.id)">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>

    <div class="card">
      <el-pagination
          v-model:current-page="data.pageNum"
          v-model:page-size="data.pageSize"
          :page-sizes="[5, 10, 20, 50]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="sizes, prev, pager, next"
          :total="data.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>


    <el-dialog width="35%" v-model="data.isAddBoxVisible" title="Add new course">
      <el-form :model="data.form" label-width="100px" style="padding-right: 30px">
        <!-- Course Title-->
        <el-form-item label="Title">
          <el-input v-model="data.form.title" autocomplete="off"/>
        </el-form-item>
        <!-- Course Number-->
        <el-form-item label="Number">
          <el-input v-model="data.form.number" autocomplete="off"/>
        </el-form-item>
        <!-- Course Instructor-->
        <el-form-item label="Instructor">
          <el-input v-model="data.form.instructor" autocomplete="off"/>
        </el-form-item>
        <!-- Course Section-->
        <el-form-item label="Section">
          <el-input v-model="data.form.section" autocomplete="off"/>
        </el-form-item>
        <!-- Course Subject-->
        <el-form-item label="Subject">
          <el-input v-model="data.form.subject" autocomplete="off"/>
        </el-form-item>
        <!-- Campus -->
        <el-form-item label="Campus">
          <el-input v-model="data.form.campus" autocomplete="off"/>
        </el-form-item>
        <!-- Credit Hours -->
        <el-form-item label="Hours">
          <el-input v-model="data.form.hours" autocomplete="off"/>
        </el-form-item>
        <!-- Course Description-->
        <el-form-item label="Description">
          <el-input v-model="data.form.description" autocomplete="off"/>
        </el-form-item>
        <!-- Course Location -->
        <el-form-item label="Location">
          <el-input v-model="data.form.location" autocomplete="off"/>
        </el-form-item>
        <!-- Course Timetable -->
        <el-form-item label="Timetable">
          <el-input v-model="data.form.timetable" autocomplete="off"/>
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="data.isAddBoxVisible = false">Cancel</el-button>
            <el-button type="primary" @click="saveCourse">
              Save
            </el-button>
          </span>
      </template>
    </el-dialog>

  </div>
</template>


<style scoped>
.search_input {
  width: 10vw;
  margin-right: 10px;
}
</style>


<script setup>
import {reactive, ref} from "vue";
import {Search} from '@element-plus/icons-vue';
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const small = ref(false)
const background = ref(false)
const disabled = ref(false)

const handleSizeChange = (pageNum) => {
  // reload the data when page size changed
  load();
}
const handleCurrentChange = (pageNum) => {
  // reload the data when page changed
  load();
}

const reset = () => {
  data.username = "";
  data.email = "";
  load();
}

const data = reactive({
  username: '',
  // lastName: '',
  email: '',
  studentData: [],
  total: 0,
  pageNum: 1, // current page number
  pageSize: 5, // current page size
  isAddBoxVisible: false,
  form: {}
})

const load = () => {
  request.get('/student/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      username: data.username,
      email: data.email,
      // lastName: data.lastName,
    }
  }).then(res => {
    data.studentData = res.data?.list || [];
    data.total = res.data?.total || 0;
  })
}
load();

const showDialog = () => {
  data.isAddBoxVisible = true;
}

// save data to database (include add and update)
const saveCourse = () => {
  request.request({
    url: data.form.id ? '/course/update' : '/course/add',
    method: data.form.id ? 'PUT' : 'POST',
    data: data.form
  }).then(res => {
    if (res.code === '200') {
      ElMessage.success("Course Saved!");
      data.isAddBoxVisible = false;
      load();
    } else {
      ElMessage.error(res.msg);
    }
  })
}

const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row));
  data.isAddBoxVisible = true;
}


const handleDelete = (id) => {
  ElMessageBox.confirm("Will permanently delete the course. Continue?", "Confirm", { type: 'warning' }).then(() => {
    request.put('course/pseudo-delete/'+id).then(res => {
      if (res.code === '200') {
        ElMessage.success("Course Deleted!");
        load();
      } else {
        ElMessage.error(res.msg);
      }
    })
  }).catch(res => {
    ElMessage({
      type: 'info',
      message: 'Delete Canceled'
    })
  })
}
</script>