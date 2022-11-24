<template>
  <card class="card" title="회원 정보 수정">
    <form @submit="modify">
      <user-id no="readonly"></user-id>
      <user-name no="readonly"></user-name>
      <password-check></password-check>
      <user-email no="modify"></user-email>

      <button-item msg="수정 완료"></button-item>
    </form>
  </card>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import UserEmail from "@/components/user/item/UserEmail.vue";
import PasswordCheck from "@/components/user/item/PasswordCheck.vue";
import UserName from "@/components/user/item/UserName.vue";
import UserId from "@/components/user/item/UserId.vue";
import ButtonItem from "@/components/user/item/ButtonItem.vue";
const user = "user";
export default {
  name: "UserModify",
  computed: {
    ...mapGetters(user, ["userInfo", "userpwd", "isPwdCheck"]),
  },
  components: {
    UserName,
    UserId,
    PasswordCheck,
    UserEmail,
    ButtonItem,
  },
  methods: {
    ...mapActions(user, ["modifyUser"]),
    modify(event) {
      event.preventDefault();
      let user = {
        userid: this.userInfo.userid,
        userpwd: this.userpwd,
        email: this.userInfo.email,
      };
      if (!this.isPwdCheck) alert("비밀번호를 다시 확인해주세요.");
      else {
        this.modifyUser({
          user: user,
          callback: () => {
            this.$router.push({ name: "userinfo" });
          },
        });
      }
      // console.log(this.loginedUser);
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
