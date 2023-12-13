<template>
  <div>
    <div class="card" style="margin-bottom: 10px">
      <el-input v-model="data.title" class="search_input" placeholder="by title" :prefix-icon="Search"/>
      <el-input v-model="data.targetMuscle" class="search_input" placeholder="by target muscle" :prefix-icon="Search"/>
      <el-input v-model="data.difficultyLevel" class="search_input" placeholder="by difficulty level" :prefix-icon="Search"/>
      <el-button style="margin-left: 10px" type="primary" @click="load">Search</el-button>
      <el-button style="margin: 0 10px" type="info" @click="reset">Reset</el-button>
    </div>

    <div class="card">
      <div style="margin-bottom: 20px">
        <el-button type="primary" @click="showDialog">Add</el-button>
      </div>

      <div>
        <el-table :data="data.exerciseData" style="width: 100%">
          <el-table-column fixed prop="id" label="ID" width="85"/>
          <el-table-column prop="title" label="Title" width="200"/>
          <el-table-column prop="targetMuscle" label="targetMuscle" width="170"/>
          <el-table-column prop="equipment" label="equipment" width="100"/>
          <el-table-column prop="description" label="description" width="250"/>
          <el-table-column prop="difficultyLevel" label="difficultyLevel" width="150"/>
          <el-table-column prop="repetitionRange" label="repetitionRange" width="180"/>
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


    <el-dialog width="40%" v-model="data.isAddBoxVisible" title="Add new exercise">
      <el-form :model="data.form" label-width="130px" style="padding-right: 30px">
        <!-- Exercise Title-->
        <el-form-item label="Title">
          <el-input v-model="data.form.title" autocomplete="off"/>
        </el-form-item>
        <!-- Target Muscle -->
        <el-form-item label="Target Muscle">
          <el-input v-model="data.form.targetMuscle" autocomplete="off"/>
        </el-form-item>
        <!-- Equipment-->
        <el-form-item label="Equipment">
          <el-input v-model="data.form.equipment" autocomplete="off"/>
        </el-form-item>
        <!-- Description-->
        <el-form-item label="Description">
          <el-input v-model="data.form.description" autocomplete="off"/>
        </el-form-item>
        <!-- Difficulty Level-->
        <el-form-item label="Difficulty Level">
          <el-input v-model="data.form.difficultyLevel" autocomplete="off"/>
        </el-form-item>
        <!-- Repetition Range -->
        <el-form-item label="Repetition Range">
          <el-input v-model="data.form.repetitionRange" autocomplete="off"/>
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="data.isAddBoxVisible = false">Cancel</el-button>
            <el-button type="primary" @click="saveExercise">
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
  isAddBoxVisible: false,
  form: {}
})

const load = () => {
  request.get('/exercise/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      title: data.title,
      targetMuscle: data.targetMuscle,
      difficultyLevel: data.difficultyLevel,
    }
  }).then(res => {
    data.exerciseData = res.data?.list || [];
    data.total = res.data?.total || 0;
  })
}
load();

const showDialog = () => {
  data.form = {};
  data.isAddBoxVisible = true;
}

// save data to database (include add and update)
const saveExercise = () => {
  request.request({
    url: data.form.id ? '/exercise/update' : '/exercise/add',
    method: data.form.id ? 'PUT' : 'POST',
    data: data.form
  }).then(res => {
    if (res.code === '200') {
      ElMessage.success("Exercise Saved!");
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
  ElMessageBox.confirm("Will permanently delete the exercise. Continue?", "Confirm", { type: 'warning' }).then(() => {
    request.put('exercise/pseudo-delete/'+id).then(res => {
      if (res.code === '200') {
        ElMessage.success("Exercise Deleted!");
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