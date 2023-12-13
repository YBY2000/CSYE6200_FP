<template>
  <div>
    <div class="card profile-card">
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
        <!-- Save Button -->
        <el-form-item>
          <el-button type="primary" @click="updateProfile">Save</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style>
.profile-card {
  width: 50%;
  padding: 40px 20px;
  margin: 20px auto;
}
</style>

<script setup>
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import request from "@/utils/request";
import router from "@/router";

const data = reactive({
  // get the current logged-in user's information
  form: JSON.parse(localStorage.getItem('login-user') || '{}')
})

const updateProfile = () => {
  request.put('/user/update', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success("User Account Saved!");
      router.push('/login')
      load();
    } else {
      ElMessage.error(res.msg);
    }
  })
}
</script>