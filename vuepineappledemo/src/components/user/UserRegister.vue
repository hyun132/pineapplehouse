<template>
  <card class="card">
    <form @submit="join">
      <div class="mb-3 font">회원 가입</div>
      <user-id></user-id>
      <user-name></user-name>
      <password-check></password-check>
      <user-email></user-email>
      <button-item msg="회원가입"></button-item>
      <p class="forgot-password text-right">
        Already registered
        <router-link :to="{ name: 'login' }">Login?</router-link>
      </p>
    </form>
  </card>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import PasswordCheck from "@/components/user/item/PasswordCheck.vue";
import UserEmail from "@/components/user/item/UserEmail.vue";
import UserId from "@/components/user/item/UserId.vue";
import UserName from "@/components/user/item/UserName.vue";
import ButtonItem from "@/components/user/item/ButtonItem.vue";
const user = "user";
export default {
  name: "UserRegister",
  data() {
    return {
      user: {},
      userpwdcheck: "",
    };
  },
  components: {
    PasswordCheck,
    UserEmail,
    UserId,
    UserName,
    ButtonItem,
  },
  computed: {
    ...mapGetters(user, [
      "userpwd",
      "userid",
      "username",
      "email",
      "isIdCheck",
      "isPwdCheck",
    ]),
  },
  methods: {
    ...mapActions(user, ["registUser"]),
    join(event) {
      event.preventDefault();
      if (!this.isIdCheck) alert("중복된 아이디입니다.");
      else if (!this.isPwdCheck) alert("비밀번호를 다시 확인해주세요.");
      else {
        this.registUser({
          user: {
            userid: this.userid,
            username: this.username,
            userpwd: this.userpwd,
            email: this.email,
          },
          callback: () => {
            this.$router.push({ name: "login" });
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
  height: 90vh;
  vertical-align: center;
}
.font {
  font-size: 3.5ch;
  font-weight: bold;
}
</style>
