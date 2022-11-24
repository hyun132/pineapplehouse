<template>
  <card class="card">
    <form @submit="reset">
      <div class="mb-3 font">비밀번호 재설정</div>
      <password-check></password-check>
      <button-item msg="비밀번호 재설정"></button-item>
    </form>
  </card>
</template>

<script>
import PasswordCheck from "@/components/user/item/PasswordCheck.vue";
import ButtonItem from "@/components/user/item/ButtonItem.vue";
import { mapActions, mapGetters, mapMutations } from "vuex";
const user = "user";
export default {
  name: "UserResetPassword",
  components: {
    PasswordCheck,
    ButtonItem,
  },
  computed: {
    ...mapGetters(user, ["userid", "userpwd", "isPwdCheck"]),
  },
  methods: {
    ...mapActions(user, ["resetPassword"]),
    ...mapMutations(user, ["CLEAR_FIND_USER"]),
    reset(event) {
      event.preventDefault();
      // console.log(this.findUserId);
      // console.log(this.userpwd);
      if (!this.isPwdCheck) alert("비밀번호를 다시 확인해주세요.");
      else {
        this.resetPassword({
          userinfo: {
            userid: this.userid,
            userpwd: this.userpwd,
          },
          callback: () => {
            this.$router.push({ name: "login" });
          },
        });
      }
    },
  },
};
</script>

<style scoped>
.box {
  display: flex;
  width: 50%;
  height: 60vh;
  vertical-align: center;
}
.font {
  font-size: 3.5ch;
  font-weight: bold;
}
</style>
