<template>
  <div>
    <div class="login_container">
      <div class="login_box">
        <div class="login_title">L O G I N</div>
        <el-form :model="data.form" ref="formRef" :rules="rules" class="login_form">
          <!-- username-->
          <el-form-item label="Username" prop="username">
            <el-input prefix-icon="User" v-model="data.form.username" placeholder="Please enter username"/>
          </el-form-item>
          <!-- password-->
          <el-form-item label="Password" prop="password">
            <el-input
                type="password"
                show-password prefix-icon="Lock" v-model="data.form.password"
                placeholder="Please enter password"/>
          </el-form-item>
          <!-- role -->
          <el-form-item prop="role">
            <el-radio-group v-model="data.form.role" style="margin: 10px auto">
              <el-radio value="STUDENT" label="STUDENT" size="large">Student</el-radio>
              <el-radio value="ADMIN" label="ADMIN" size="large">Admin</el-radio>
            </el-radio-group>
          </el-form-item>
          <!-- login button-->
          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="login">LOGIN</el-button>
          </el-form-item>
          <div class="login_goSignUp">
            Have no account? Go <a href="/signup" style="color: blue;">SIGN UP</a>
          </div>
        </el-form>
      </div>

    </div>
  </div>
</template>


<style scoped>
.login_title {
  font-weight: bold;
  font-size: 24px;
  text-align: center;
  margin-bottom: 30px;
}

.login_form {
  width: 18vw;
  margin: 0 auto;
}

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
  padding: 30px;
}

.login_goSignUp {
  margin-top: 20px;
  text-align: center;
}
</style>


<script setup>
import {reactive, ref} from "vue";
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";

const data = reactive({
  form: {}
})

// validation rules for input area
const rules = reactive({
  username: [
    {required: true, message: 'Please input Username', trigger: 'blur'},
    // { min: 5, max: 12, message: 'Length should be 5 to 12', trigger: 'blur' },
  ],
  password: [
    {required: true, message: 'Please input Password', trigger: 'blur'},
  ],
})

// form validation while click login
const formRef = ref();

const login = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      request.post('/login', data.form).then(res => {
        if (res.code === '200') {
          localStorage.setItem('student-user', JSON.stringify(res.data));
          ElMessage.success('Login Succeed!');
          router.push('/home');    // go to home page
        } else {
          ElMessage.error(res.msg);
        }
      })
    }
  })
}
</script>