<template>
  <div>

    <div class="card" style="line-height: 30px">
      <div>Welcome，<span style="color: royalblue">{{ user.username }}</span>. Hope you enjoy your day！</div>
    </div>
    <div class="card container">
      <el-row>
        <el-col :span="5" v-for="(o, index) in data.card_data" :key="o" :offset="index > 0 ? 3 : 0">
          <el-card :body-style="{ padding: '0px' }">
            <img :src="o.image" class="image">
            <div style="padding: 14px;">
              <span>{{ o.title }}</span>
              <div class="bottom clearfix">
                <time class="time">{{ data.currentDate }}</time>
                <el-button type="text" class="button">Operating</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

  </div>
</template>


<style scoped>
.container {
  width: 1300px;
  margin: 30px auto;
}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>


<script setup>
  import request from "@/utils/request";
  import {reactive} from "vue";
  const data = reactive({
    currentDate: new Date().toLocaleString(),
    card_data: [
      {
        title: 'RESEARCH',
        image: 'https://raw.githubusercontent.com/likun945/INFO1650_FP/main/Frontend/public/business-meeting.png',
        description: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure nam expedita exercitationem?',
      },
      {
        title: 'DESIGN',
        image: 'https://raw.githubusercontent.com/likun945/INFO1650_FP/main/Frontend/public/business-meeting.png',
        description: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure nam expedita exercitationem?',
      },
      {
        title: 'DEVELOP',
        image: 'https://raw.githubusercontent.com/likun945/INFO1650_FP/main/Frontend/public/business-meeting.png',
        description: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure nam expedita exercitationem?',
      },
    ]
  });


  const user = JSON.parse(localStorage.getItem('login-user') || '{}')
</script>