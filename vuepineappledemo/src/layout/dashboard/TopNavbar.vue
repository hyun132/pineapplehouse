<template>
  <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <button
        class="navbar-toggler navbar-burger"
        type="button"
        @click="toggleSidebar"
        :aria-expanded="$sidebar.showSidebar"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-bar"></span>
        <span class="navbar-toggler-bar"></span>
        <span class="navbar-toggler-bar"></span>
      </button>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="ti-zoom-in"></i>
              <p>About us</p>
            </a>
          </li>
          <drop-down
            class="nav-item"
            :title="username"
            title-classes="nav-link"
            icon="ti-user"
          >
            <b-dropdown-item
              v-if="userInfo"
              class="dropdown-item"
              @click.prevent="onClickLogout"
              >로그아웃</b-dropdown-item
            >
            <div v-else>
              <router-link class="dropdown-item" to="/user/login"
                >로그인</router-link
              >
              <router-link class="dropdown-item" to="/user/join"
                >회원가입</router-link
              >
            </div>
          </drop-down>
          <li class="nav-item" v-if="userInfo && userInfo.isadmin == 1">
            <a class="nav-link">
              <i class="ti-settings"></i>
              <p @click="movePage">회원 관리</p>
            </a>
          </li>
          <li class="divider"></li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
import { mapState, mapActions } from "vuex";
const user = "user";
export default {
  computed: {
    ...mapState(user, ["userInfo"]),
    username() {
      if (this.userInfo) return "안녕하세요 " + this.userInfo.username + "님";
      else return "안녕하세요 손님";
    },
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    },
  },
  data() {
    return {
      activeNotifications: false,
    };
  },
  methods: {
    ...mapActions(user, ["logoutUser"]),
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    onClickLogout() {
      console.log(this.userInfo.userid);
      this.logoutUser(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/dashboard") this.$router.push({ name: "main" });
    },
    movePage: function () {
      this.$router.push({ name: "userlist" });
    },
  },
};
</script>
<style>
.dropdown-item.active,
.dropdown-item:active {
  color: #fff;
  text-decoration: none;

  background-color: #66615b;
}
.dropdown .dropdown-menu .dropdown-item {
  /* color: #66615b !important; */
  font-size: 14px;
  padding: 10px 45px 10px 15px;
  clear: both;
  white-space: nowrap;
  width: 100%;
  display: block;
}
.set {
  color: #66615b;
}
</style>
