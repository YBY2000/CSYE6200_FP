<template>
  <div>
    <div class="card" style="margin-bottom: 10px">
      <el-input v-model="data.courseNum" class="search_input" placeholder="by course No." :prefix-icon="Search"/>
      <el-input v-model="data.subject" class="search_input" placeholder="by subject" :prefix-icon="Search"/>
      <el-input v-model="data.campus" class="search_input" placeholder="by campus" :prefix-icon="Search"/>
      <el-button style="margin-left: 10px" type="primary" @click="load">Search</el-button>
      <el-button style="margin: 0 10px" type="info" @click="reset">Reset</el-button>
    </div>

    <div class="card">
      <div style="margin-bottom: 20px">
        <el-button type="primary" @click="handleAdd">Add</el-button>
      </div>

      <div>
        <el-table :data="data.courseData" style="width: 100%">
          <el-table-column fixed prop="number" label="Course Number" width="125"/>
          <el-table-column prop="title" label="Title" width="170"/>
          <el-table-column prop="instructor" label="Instructor" width="130"/>
          <el-table-column prop="section" label="Section" width="100"/>
          <el-table-column prop="subject" label="Subject" width="120"/>
          <el-table-column prop="campus" label="Campus" width="100"/>
          <el-table-column prop="hours" label="Hours" width="80"/>
          <el-table-column prop="description" label="Description" style="overflow: scroll" width="400"/>
          <el-table-column prop="location" label="Location" width="250"/>
          <el-table-column prop="timetable" label="Scheduled" width="200"/>
          <el-table-column fixed="right" label="Operation" width="200">
            <template #default="scope">
              <el-button type="primary" plain>Edit</el-button>
              <el-button type="danger" plain>Delete</el-button>
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
import {ElMessage} from "element-plus";

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
  data.keyword = "";
  data.courseNum = "";
  data.title = "";
  data.instructor = "";
  data.subject = "";
  data.campus = "";
  load();
}

const data = reactive({
  courseNum: '',
  subject: '',
  campus: '',
  courseData: [],
  total: 0,
  pageNum: 1, // current page number
  pageSize: 5, // current page size
  isAddBoxVisible: false,
  form: {}
})

const load = () => {
  console.log("clicked")
  request.get('/course/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      courseNum: data.courseNum,
      subject: data.subject,
      campus: data.campus,
    }
  }).then(res => {
    data.courseData = res.data?.list || [];
    data.total = res.data?.total || 0;
  })
}
load();

const handleAdd = () => {
  data.isAddBoxVisible = true;
}
const saveCourse = () => {
  request.post('/course/add', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success("Course Saved!");
      data.isAddBoxVisible = false;
      load();
    } else {
      ElMessage.error(res.msg);
    }
  })
}
</script>