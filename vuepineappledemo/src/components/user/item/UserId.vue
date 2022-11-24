<template>
  <div class="form-group">
    <label>아이디</label>
    <div v-if="no == 'readonly'">
      <input
        type="text"
        class="form-control form-control-lg"
        v-model="userInfo.userid"
        readonly
      />
    </div>
    <div v-else-if="no == 'login' || no == 'find'">
      <input
        type="text"
        class="form-control form-control-lg"
        v-model="userid"
      />
    </div>
    <b-form-group
      v-else
      :invalid-feedback="invalidFeedbackIdCheck"
      valid-feedback=""
      :state="stateIdCheck"
    >
      <b-form-input
        type="text"
        class="form-control form-control-lg"
        :state="stateIdCheck"
        v-model="userid"
        required
      ></b-form-input>
    </b-form-group>
  </div>
</template>

<script>
import { mapMutations, mapGetters, mapActions } from "vuex";
const user = "user";
export default {
  props: {
    no: String,
  },
  computed: {
    ...mapGetters(user, ["userInfo", "isIdCheck"]),
    stateIdCheck() {
      if (this.userid) this.idCheck(this.userid);
      return this.isIdCheck;
    },
    invalidFeedbackIdCheck() {
      if (this.userid.length > 0 && !this.isIdCheck)
        return "중복된 아이디입니다.";
    },
  },
  data() {
    return {
      userid: "",
    };
  },
  watch: {
    userid() {
      this.SET_USERID(this.userid);
    },
  },
  methods: {
    ...mapMutations(user, ["SET_USERID"]),
    ...mapActions(user, ["idCheck"]),
  },
};
</script>

<style></style>
