<template>
  <div class="form-group">
    <label>비밀번호</label>
    <input
      type="password"
      class="form-control form-control-lg"
      v-model="userpwd"
      required
    />
  </div>
</template>

<script>
import { mapMutations } from "vuex";
const user = "user";
export default {
  data() {
    return {
      userpwd: "",
    };
  },
  watch: {
    userpwd() {
      this.SET_USERPWD(this.userpwd);
    },
  },
  methods: {
    ...mapMutations(user, ["SET_USERPWD"]),
  },
};
</script>

<style></style>
