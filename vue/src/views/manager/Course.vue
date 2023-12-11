<template>
  <div>
    <div class="card" style="margin-bottom: 10px">
      <el-input v-model="data.name" style="width: 20vw" placeholder="Input the keyword" :prefix-icon="Search" />
      <el-button style="margin-left: 10px" type="primary">Search</el-button>
      <el-button style="margin: 0 10px" type="info">Reset</el-button>
    </div>

    <div class="card">
      <div style="margin-bottom: 20px">
        <el-button type="primary">Add</el-button>
      </div>

      <div>
        <el-table :data="data.courseData" style="width: 100%">
          <el-table-column fixed prop="number" label="Course Number" width="125" />
          <el-table-column prop="title" label="Title" width="170" />
          <el-table-column prop="instructor" label="Instructor" width="130" />
          <el-table-column prop="section" label="Section" width="100" />
          <el-table-column prop="subject" label="Subject" width="120" />
          <el-table-column prop="campus" label="Campus" width="100" />
          <el-table-column prop="hours" label="Hours" width="80" />
          <el-table-column prop="description" label="Description" style="overflow: scroll" width="400" />
          <el-table-column prop="location" label="Location" width="250" />
          <el-table-column prop="timetable" label="Scheduled" width="200" />
          <el-table-column fixed="right" label="Operation" width="200" >
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
          :page-sizes="[10, 20, 30, 50]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="sizes, prev, pager, next"
          :total="data.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>





<script setup>
import { reactive, ref } from "vue";
import { Search } from '@element-plus/icons-vue';
import request from "@/utils/request";

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

const data = reactive({
  name:'',
  courseData: [],
  total: 0,
  pageNum: 1, // current page number
  pageSize: 5 // current page size
})

const load = () => {
  request.get('/course/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
    }
  }).then(res => {
    data.courseData = res.data?.list || [];
    data.total = res.data?.total || 0;
  })
}
// get the data
load();
</script>