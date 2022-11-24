<template>
  <card class="card">
    <form @submit="login">
      <div class="mb-3 font">로그인</div>
      <user-id no="login"></user-id>
      <password-input></password-input>
      <button-item msg="로그인"></button-item>
      <p class="forgot-password text-right">
        <router-link :to="{ name: 'findpassword' }"
          >Forgot password?</router-link
        >
      </p>
    </form>
  </card>
</template>

<script>
import UserId from "@/components/user/item/UserId.vue";
import PasswordInput from "@/components/user/item/PasswordInput.vue";
import ButtonItem from "@/components/user/item/ButtonItem.vue";
import { mapActions, mapGetters } from "vuex";
const user = "user";
export default {
  name: "UserLogin",
  components: {
    UserId,
    PasswordInput,
    ButtonItem,
  },
  computed: {
    ...mapGetters(user, [
      "userid",
      "userpwd",
      "isLogin",
      "isLoginError",
      "userInfo",
      "favorite",
    ]),
  },
  methods: {
    ...mapActions(user, ["loginUser", "getUserInfo", "getFavorite"]),
    async login(event) {
      event.preventDefault();

      await this.loginUser({ userid: this.userid, userpwd: this.userpwd });
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      // console.log(this.isLogin);
      if (this.isLogin) {
        await this.getUserInfo(token);
        await this.getFavorite(this.userid);
        console.log(this.favorite);
        alert("로그인 완료");
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      } else {
        alert("아이디 또는 비밀번호를 다시 확인해주세요.");
      }
    },
  },
};
</script>

<style scoped>
.box {
  display: flex;
  width: 50%;
  height: 50vh;
  vertical-align: center;
}
.font {
  font-size: 3.5ch;
  font-weight: bold;
}
</style>
