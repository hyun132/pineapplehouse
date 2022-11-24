<template>
  <div>
    <b-form-group
      :invalid-feedback="invalidFeedbackPwd"
      valid-feedback=""
      :state="statePwd"
    >
      <label>비밀번호</label>
      <b-form-input
        type="password"
        class="form-control form-control-lg"
        v-model="pwd"
        :state="statePwd"
        required
      ></b-form-input>
    </b-form-group>
    <b-form-group
      :invalid-feedback="invalidFeedbackPwdCheck"
      valid-feedback=""
      :state="statePwdCheck"
    >
      <label>비밀번호 확인</label>
      <b-form-input
        type="password"
        class="form-control form-control-lg"
        :state="statePwdCheck"
        v-model="userpwdcheck"
        required
      ></b-form-input>
    </b-form-group>
  </div>
</template>

<script>
const user = "user";
import { mapGetters, mapMutations } from "vuex";
export default {
  data() {
    return {
      pwd: "",
      userpwdcheck: "",
    };
  },
  computed: {
    ...mapGetters(user, ["userpwd", "isPwdCheck"]),
    statePwdCheck() {
      this.SET_USERPWD(this.pwd);
      if (this.userpwdcheck.length > 0 && this.userpwdcheck == this.pwd)
        this.SET_IS_PWD_CHECK(true);
      else this.SET_IS_PWD_CHECK(false);
      return this.userpwdcheck.length > 0 && this.userpwdcheck == this.pwd;
    },
    invalidFeedbackPwdCheck() {
      if (!this.userpwdcheck) return "비밀번호를 한 번 더 입력해주세요.";
      return "비밀번호가 일치하지 않습니다.";
    },
    statePwd() {
      return this.pwd.length >= 5;
    },
    invalidFeedbackPwd() {
      return "비밀번호 5자리 이상 입력해주세요.";
    },
  },
  methods: {
    ...mapMutations(user, ["SET_USERPWD", "SET_IS_PWD_CHECK"]),
  },
};
</script>

<style></style>
