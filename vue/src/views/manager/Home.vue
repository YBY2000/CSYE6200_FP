<template>
  <div>

    <div class="card" style="line-height: 30px">
      <div>Welcome，<span style="color: royalblue">{{ user.username }}</span>. Hope you enjoy your day！</div>
    </div>

  </div>
</template>

<script setup>
  import request from "@/utils/request";
  const user = JSON.parse(localStorage.getItem('login-user') || '{}')
</script>