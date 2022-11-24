<template>
  <div
    class="row containerBody"
    style="
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    "
  >
    <router-view style="width: 400px"></router-view>
  </div>
</template>

<script>
export default {
  name: "User",
};
</script>

<style scoped>
.containerBody {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.body {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
