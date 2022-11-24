<template>
  <div class="form-group">
    <label>이메일</label>
    <div v-if="no == 'readonly'">
      <input
        type="email"
        class="form-control form-control-lg"
        v-model="userInfo.email"
        readonly
      />
    </div>
    <div v-else-if="no == 'modify'">
      <input
        type="email"
        class="form-control form-control-lg"
        v-model="userInfo.email"
      />
    </div>
    <div v-else>
      <input
        type="email"
        class="form-control form-control-lg"
        v-model="email"
        required
      />
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
const user = "user";
export default {
  props: {
    no: String,
  },
  computed: {
    ...mapState(user, ["userInfo"]),
  },
  data() {
    return {
      email: "",
    };
  },
  watch: {
    email() {
      this.SET_EMAIL(this.email);
    },
  },
  methods: {
    ...mapMutations(user, ["SET_EMAIL"]),
  },
};
</script>

<style></style>
