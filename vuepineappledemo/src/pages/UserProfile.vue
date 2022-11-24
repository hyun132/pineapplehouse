<template>
  <div class="row containerBody">
    <div class="col-xl-4 col-lg-5 col-md-6">
      <user-card> </user-card>
    </div>
    <div class="col-xl-5 col-lg-6 col-md-7">
      <router-view> </router-view>
    </div>
    <div class="col-xl-3 col-lg-3 col-md-3">
      <user-content title="내가 쓴 글"></user-content>
      <user-content title="내가 쓴 댓글"></user-content>
    </div>
  </div>
</template>
<script>
import EditProfileForm from "./UserProfile/EditProfileForm.vue";
import UserCard from "./UserProfile/UserCard.vue";
import UserInfo from "../components/user/UserInfo.vue";
import UserContent from "./UserProfile/UserContent.vue";
export default {
  components: {
    EditProfileForm,
    UserCard,
    UserInfo,
    UserContent,
  },
};
</script>
<style></style>
