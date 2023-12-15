<template>
  <div>
    <div class="card" style="text-align: center">
      <p><h2>My training plan</h2></p>

    </div>
    <div class="card" style="margin-bottom: 10px">
      <el-input v-model="data.title" class="search_input" placeholder="by title" :prefix-icon="Search"/>
      <el-input v-model="data.targetMuscle" class="search_input" placeholder="by target muscle" :prefix-icon="Search"/>
      <el-input v-model="data.difficultyLevel" class="search_input" placeholder="by difficulty level" :prefix-icon="Search"/>
      <el-button style="margin-left: 10px" type="primary" @click="load">Search</el-button>
      <el-button style="margin: 0 10px" type="info" @click="reset">Reset</el-button>
    </div>

    <div class="card">
      <div>
        <el-table :data="data.exerciseData" style="width: 100%">
          <el-table-column prop="title" label="Title" width="200"/>
          <el-table-column label="Operation" width="200">
            <template #default="scope">
              <el-button type="danger" @click="removeParticipate(scope.row.id)">Remove</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>

    <div class="card">
      <el-pagination
          v-model:current-page="data.pageNum"
          v-model:page-size="data.pageSize"
          :page-sizes="[5, 10, 20]"
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
  data.title = "";
  data.targetMuscle = "";
  data.difficultyLevel = "";
  load();
}

const data = reactive({
  title: '',
  targetMuscle: '',
  difficultyLevel: '',
  exerciseData: [],
  total: 0,
  pageNum: 1, // current page number
  pageSize: 5, // current page size
  user: JSON.parse(localStorage.getItem('login-user') || '{}')
})

const load = () => {
  request.get('/userParticipate/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      title: data.title,
      userId: data.user.id
    }
  }).then(res => {
    data.exerciseData = res.data?.list || [];
    data.total = res.data?.total || 0;
  })
}
load();

const removeParticipate = (id) => {
  ElMessageBox.confirm("Will permanently delete. Continue?", "Confirm", {type: 'warning'}).then(() => {
    request.delete('userParticipate/delete/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success("Deleted!");
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