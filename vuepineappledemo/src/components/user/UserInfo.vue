<template>
  <card class="card">
    <div>
      <div>
        <div class="mb-3 font">회원 정보</div>
        <user-id lass="text-center" no="readonly"></user-id>
        <user-name no="readonly"></user-name>
        <user-email no="readonly"></user-email>
        <b-col class="text-center">
          <button @click="movePage" class="btn btn-dark btn-lg ml-4 mr-3">
            수정
          </button>
          <button type="submit" class="btn btn-dark btn-lg" @click="withdrawl">
            탈퇴
          </button>
        </b-col>
      </div>
    </div>
  </card>
</template>

<script>
import { mapActions, mapState } from "vuex";
import UserEmail from "@/components/user/item/UserEmail.vue";
import UserName from "@/components/user/item/UserName.vue";
import UserId from "@/components/user/item/UserId.vue";
const user = "user";
export default {
  name: "UserInfo",
  components: {
    UserEmail,
    UserId,
    UserName,
  },
  computed: {
    ...mapState(user, ["userInfo"]),
  },
  methods: {
    ...mapActions(user, ["deleteUser"]),
    movePage: function () {
      this.$router.push({ name: "usermodify" });
    },
    withdrawl() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        this.deleteUser({
          type: "user",
          user: this.userInfo,
          callback: () => {
            this.$router.push({ name: "main" });
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
