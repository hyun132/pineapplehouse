<template>
  <card class="card">
    <form @submit="find">
      <div class="mb-3 font">비밀번호 찾기</div>
      <user-id no="find"></user-id>
      <user-name></user-name>
      <user-email></user-email>
      <button-item msg="비밀번호 찾기"></button-item>
      <p class="forgot-password text-right">
        <router-link :to="{ name: 'login' }">Login?</router-link>
      </p>
    </form>
  </card>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const user = "user";
import UserEmail from "@/components/user/item/UserEmail.vue";
import UserId from "@/components/user/item/UserId.vue";
import UserName from "@/components/user/item/UserName.vue";
import ButtonItem from "@/components/user/item/ButtonItem.vue";
export default {
  name: "UserFindPassword",
  components: {
    UserEmail,
    UserId,
    UserName,
    ButtonItem,
  },
  computed: {
    ...mapGetters(user, ["userid", "username", "email"]),
  },
  methods: {
    ...mapActions(user, ["findPassword", "userpwd"]),
    find(event) {
      event.preventDefault();
      this.findPassword({
        userinfo: {
          userid: this.userid,
          username: this.username,
          email: this.email,
        },
        callback: () => {
          this.$router.push({ name: "resetpassword" });
        },
      });

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
