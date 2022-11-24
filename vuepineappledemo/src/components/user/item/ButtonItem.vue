<template>
  <button type="submit" class="btn btn-dark btn-lg btn-block">
    {{ msg }}
  </button>
</template>

<script>
export default {
  props: {
    msg: String,
  },
};
</script>

<style></style>
