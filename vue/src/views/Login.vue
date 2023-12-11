<template>
  <div>
    <div class="login_container">
      <div class="login_box">
        <div style="font-weight: bold; font-size: 24px; text-align: center; margin-bottom: 30px">L O G I N</div>
        <el-form :model="data.form">
          <!-- username-->
          <el-form-item label="Username">
            <el-input prefix-icon="User" v-model="data.form.username" placeholder="Please enter username"/>
          </el-form-item>
          <!-- password-->
          <el-form-item label="Password">
            <el-input prefix-icon="Lock" v-model="data.form.username" placeholder="Please enter password"/>
          </el-form-item>
          <!-- login button-->
          <el-form-item>
            <el-button type="primary" style="width: 100%">LOGIN</el-button>
          </el-form-item>
          <div style="margin-top: 30px; text-align: center">
            No account? Go <a href="/register">SIGN UP</a>
          </div>
        </el-form>
      </div>

    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";

const data = reactive({
  form: {}
})
</script>

<style scoped>
.login_container {
  min-height: 100vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("@/assets/imgs/login_bg.jpg");
  background-size: cover;
}

.login_box {
  background-color: rgba(255, 255, 255, 95%);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 40px;
}
</style>