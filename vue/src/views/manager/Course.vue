<template>
  <div>

    <div class="card" style="margin-bottom: 10px;">
      <el-input style="width: 300px; margin-right: 10px" placeholder="Input keywords to search"></el-input>
      <el-button type="primary">Search</el-button>
      <el-button type="info" style="margin: 0 10px">Reset</el-button>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">Add</el-button>
      </div>
      <el-table stripe :data="data.tableData">
        <el-table-column label="Title" prop="name"></el-table-column>
        <el-table-column label="Description" prop="descr"></el-table-column>
        <el-table-column label="Class_hour" prop="times"></el-table-column>
        <el-table-column label="Operation" align="center" width="160">
          <template v-slot="scope">
            <el-button type="primary" @click="handleEdit">Edit</el-button>
            <el-button type="danger" @click="handleDelete">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination background layout="prev, pager, next" :page-size="8" v-model:current-page="data.pageNum" :total="1000"/>
    </div>

    <el-dialog title="Information" width="40%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="data.form" label-width="100px" style="padding-right: 50px">
        <el-form-item label="Title" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Description" prop="descr">
          <el-input v-model="data.form.descr" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">CANCEL</el-button>
        <el-button type="primary" @click="data.formVisible = false">RESERVE</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive} from "vue";
import {ElMessageBox} from "element-plus";

request.get('/').then(res => {
  console.log(res)
})

const data = reactive({
  pageNum: 1,
  formVisible: false,
  form: {},
  tableData: [
    {name: 'CSYE6200', descr: 'CSYE6200 is difficult', times: '24 hours'},
    {name: 'CSYE6200', descr: 'CSYE6200 is difficult', times: '24 hours'},
    {name: 'CSYE6200', descr: 'CSYE6200 is difficult', times: '24 hours'},
    {name: 'CSYE6200', descr: 'CSYE6200 is difficult', times: '24 hours'},
    {name: 'CSYE6200', descr: 'CSYE6200 is difficult', times: '24 hours'},
    {name: 'CSYE6200', descr: 'CSYE6200 is difficult', times: '24 hours'},
    {name: 'CSYE6200', descr: 'CSYE6200 is difficult', times: '24 hours'},
    {name: 'CSYE6200', descr: 'CSYE6200 is difficult', times: '24 hours'},
  ]
})

const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}
const handleEdit = (row) => {
  let form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}
const handleDelete = (id) => {
  ElMessageBox.confirm('Data CANNOT recover after deleting. Are you sure to delete?', 'Deleting confirmed', { type: 'warning' }).then(res => {
    console.log('delete')
  }).catch(err => {
    console.error(err)
  })
}
</script>