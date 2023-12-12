<template>
  <div>
    <div class="signup_container">
      <div class="signup_box">
        <div class="signup_title">S I G N U P</div>
        <el-form :model="data.form" ref="formRef" :rules="rules" class="signup_form demo-ruleForm">
          <!-- email-->
          <el-form-item label="E-mail" prop="email">
            <el-input v-model="data.form.email" placeholder="Please enter email" class="input_area"/>
          </el-form-item>
          <!-- username-->
          <el-form-item label="Username" prop="username">
            <el-input v-model="data.form.username" placeholder="Please enter username" class="input_area"/>
          </el-form-item>
          <!-- password-->
          <el-form-item label="Password" prop="password">
            <el-input
                type="password"
                show-password v-model="data.form.password"
                placeholder="Please enter password" class="input_area"/>
          </el-form-item>
          <!-- gender-->
          <el-form-item label="Gender" prop="resource">
            <el-radio-group v-model="data.form.gender">
              <el-radio label="Male"/>
              <el-radio label="Female"/>
              <el-radio label="Other"/>
            </el-radio-group>
          </el-form-item>
          <!-- first name -->
          <el-form-item label="First Name" prop="firstName">
            <el-input v-model="data.form.firstName" placeholder="Please enter first name" class="input_area"/>
          </el-form-item>
          <!-- last name -->
          <el-form-item label="Last Name" prop="lastName">
            <el-input v-model="data.form.lastName" placeholder="Please enter last name" class="input_area"/>
          </el-form-item>
          <!-- signup button-->
          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="signup">SIGNUP</el-button>
          </el-form-item>
          <div class="signup_goLogin">
            Already have account? Go <a href="/login" style="color: blue;">LOGIN</a>
          </div>
        </el-form>
      </div>

    </div>
  </div>
</template>


<style scoped>
.signup_title {
  font-weight: bold;
  font-size: 24px;
  text-align: center;
  margin-bottom: 30px;
}

.signup_form {
  width: 18vw;
  margin: 0 auto;
}

.signup_container {
  min-height: 100vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("@/assets/imgs/signup_bg.jpg");
  background-size: cover;
}

.signup_box {
  background-color: rgba(255, 255, 255, 95%);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 30px;
}

.signup_goLogin {
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
  firstName: [
    {required: true, message: 'Please input Password', trigger: 'blur'},
  ],
  lastName: [
    {required: true, message: 'Please input Password', trigger: 'blur'},
  ],
  email: [
    {required: true, message: 'Please input Password', trigger: 'blur'},
  ],
  gender: [
    {required: true, message: 'Please select', trigger: 'blur'},
  ],
})

// form validation while click signup
const formRef = ref();

const signup = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      request.post('/signup', data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success('Signup Succeed!');
          router.push('/login');    // go to login
        } else {
          ElMessage.error(res.msg);
        }
      })
    }
  })
}
</script>