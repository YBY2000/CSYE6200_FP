<template>
  <div>

    <div class="card" style="line-height: 30px">
      <div>Welcome，{{ user.name }} Hope you enjoy your day！</div>
    </div>

  </div>
</template>

<script setup>
  import request from "@/utils/request";
  const user = JSON.parse(localStorage.getItem('student-user') || '{}')
</script>