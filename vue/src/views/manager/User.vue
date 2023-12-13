<template>
  <div>
    <div class="card" style="margin-bottom: 10px">
      <el-input v-model="data.username" class="search_input" placeholder="by username" :prefix-icon="Search"/>
      <el-input v-model="data.firstName" class="search_input" placeholder="by first name" :prefix-icon="Search"/>
      <el-input v-model="data.lastName" class="search_input" placeholder="by last name" :prefix-icon="Search"/>
      <el-input v-model="data.email" class="search_input" placeholder="by email" :prefix-icon="Search"/>
      <el-button style="margin-left: 10px" type="primary" @click="load">Search</el-button>
      <el-button style="margin: 0 10px" type="info" @click="reset">Reset</el-button>
    </div>

    <div class="card">
      <div style="margin-bottom: 20px">
        <el-button type="primary" @click="showDialog">Add</el-button>
      </div>

      <div>
        <el-table :data="data.userData" style="width: 100%">
          <el-table-column fixed prop="id" label="id" width="80"/>
          <el-table-column prop="username" label="username" width="170"/>
          <el-table-column prop="firstName" label="First name" width="150"/>
          <el-table-column prop="lastName" label="Last name" width="150"/>
          <el-table-column prop="email" label="Email" width="200"/>
          <el-table-column prop="gender" label="Gender" width="100"/>
          <el-table-column prop="avatar" label="Avatar" width="150">
            <template #default="scope">
              <el-image v-if="scope.row.avatar" :src="scope.row.avatar" preview-src-list="[scope.row.avatar"
                        style="width: 40px;height: 40px;border-radius: 5px"></el-image>
            </template>
          </el-table-column>
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


    <el-dialog width="35%" v-model="data.isAddBoxVisible" title="Add user account" distroy-on-closed>
      <el-form :model="data.form" label-width="100px" style="padding-right: 30px">
        <!-- Username-->
        <el-form-item label="Username">
          <el-input v-model="data.form.username" autocomplete="off"/>
        </el-form-item>
        <!-- Password-->
        <el-form-item label="Password">
          <el-input show-password v-model="data.form.password" autocomplete="off"/>
        </el-form-item>
        <!-- First Name-->
        <el-form-item label="First Name">
          <el-input v-model="data.form.firstName" autocomplete="off"/>
        </el-form-item>
        <!-- Last Name-->
        <el-form-item label="Last Name">
          <el-input v-model="data.form.lastName" autocomplete="off"/>
        </el-form-item>
        <!-- Email-->
        <el-form-item label="Email">
          <el-input v-model="data.form.email" autocomplete="off"/>
        </el-form-item>
        <!-- Phone -->
        <el-form-item label="Phone">
          <el-input v-model="data.form.phone" autocomplete="off"/>
        </el-form-item>
        <!-- Gender -->
        <el-form-item label="Gender" prop="resource">
          <el-radio-group v-model="data.form.gender">
            <el-radio label="Male" value="Male"/>
            <el-radio label="Female" value="Female"/>
            <el-radio label="Other" value="Other"/>
          </el-radio-group>
        </el-form-item>
        <!-- Avatar -->
        <el-form-item label="Avatar">
          <el-input v-model="data.form.avatar" autocomplete="off" placeholder="Please input a valid url !"/>
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="data.isAddBoxVisible = false">Cancel</el-button>
            <el-button type="primary" @click="saveUser">
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
  data.firstName = "";
  data.lastName = "";
  data.email = "";
  load();
}

const data = reactive({
  username: '',
  firstName: '',
  lastName: '',
  email: '',
  userData: [],
  total: 0,
  pageNum: 1, // current page number
  pageSize: 5, // current page size
  isAddBoxVisible: false,
  form: {}
})

const load = () => {
  request.get('/user/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      username: data.username,
      email: data.email,
      firstName: data.firstName,
      lastName: data.lastName,
    }
  }).then(res => {
    data.userData = res.data?.list || [];
    data.total = res.data?.total || 0;
  })
}
load();

const showDialog = () => {
  data.isAddBoxVisible = true;
}

// save data to database (include add and update)
const saveUser = () => {
  request.request({
    url: data.form.id ? '/user/update' : '/user/add',
    method: data.form.id ? 'PUT' : 'POST',
    data: data.form
  }).then(res => {
    if (res.code === '200') {
      ElMessage.success("User Account Saved!");
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
  ElMessageBox.confirm("Will permanently delete the user account. Continue?", "Confirm", {type: 'warning'}).then(() => {
    request.put('user/pseudo-delete/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success("User Account Deleted!");
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