<template>
  <div class="form-group">
    <label>이름</label>
    <div v-if="no == 'readonly'">
      <input
        type="text"
        class="form-control form-control-lg"
        v-model="userInfo.username"
        readonly
      />
    </div>
    <div v-else>
      <input
        type="text"
        class="form-control form-control-lg"
        v-model="name"
        required
      />
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
const user = "user";
export default {
  data() {
    return {
      name: "",
    };
  },
  props: {
    no: String,
  },
  computed: {
    ...mapState(user, ["userInfo"]),
  },
  watch: {
    name() {
      this.SET_USERNAME(this.name);
    },
  },
  methods: {
    ...mapMutations(user, ["SET_USERNAME"]),
  },
};
</script>

<style></style>
