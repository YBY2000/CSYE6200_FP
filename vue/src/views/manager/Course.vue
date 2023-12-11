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
        <el-table :data="data.courseData" style="width: 100%" height="250">
          <el-table-column fixed prop="number" label="Course Number" width="130" />
          <el-table-column fixed prop="title" label="Title" width="120" />
<!--          <el-table-column prop="instructor" label="Instructor" width="120" />-->
<!--          <el-table-column prop="section" label="Section" width="100" />-->
<!--          <el-table-column prop="subject" label="Subject" width="120" />-->
<!--          <el-table-column prop="campus" label="Campus" width="100" />-->
<!--          <el-table-column prop="hours" label="Credit Hours" width="120" />-->
<!--          <el-table-column prop="description" label="Description" width="300" />-->
<!--          <el-table-column prop="location" label="Location" width="150" />-->
<!--          <el-table-column prop="timetable" label="Scheduled" width="150" />-->
          <el-table-column>
            <template  #default="scope">
              <el-button type="primary" plain>Edit</el-button>
              <el-button type="danger" plain>Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>

    <div class="card">
      <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 30, 50]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="sizes, prev, pager, next"
          :total="1000"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import { Search } from '@element-plus/icons-vue';

const currentPage = ref(10)
const pageSize = ref(10)
const small = ref(false)
const background = ref(false)
const disabled = ref(false)

// const handleSizeChange = (val: number) => {
//   console.log(`${val} items per page`)
// }
// const handleCurrentChange = (val: number) => {
//   console.log(`current page: ${val}`)
// }

const data = reactive({
  name:'',
  courseData: [
    {number:"6200", title:"java1"},
    {number:"6200", title:"java2"},
    {number:"6200", title:"java3"},
    {number:"6200", title:"java4"},
    {number:"6200", title:"java5"},
    {number:"6200", title:"java6"},
    {number:"6200", title:"java7"},
    {number:"6200", title:"java8"},
    {number:"6200", title:"java9"},
    {number:"6200", title:"java10"},
    {number:"6200", title:"java11"},
    {number:"6200", title:"java12"},
    {number:"6200", title:"java13"},
    {number:"6200", title:"java14"},
    {number:"6200", title:"java15"},
    {number:"6200", title:"java16"},
    {number:"6200", title:"java17"},
    {number:"6200", title:"java18"},
    {number:"6200", title:"java19"},
    {number:"6200", title:"java20"},
  ],
})
</script>