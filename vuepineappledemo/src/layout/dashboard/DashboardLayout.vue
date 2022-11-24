<template>
  <div class="wrapper">
    <side-bar>
      <template slot="links">
        <sidebar-link to="/dashboard" name="메인" icon="ti-align-justify" />
        <sidebar-link to="/board" name="게시판" icon="ti-clipboard" />
        <sidebar-link to="/news" name="뉴스" icon="ti-book" />
        <sidebar-link to="/favorite" name="관심 지역/아파트" icon="ti-heart" />
        <sidebar-link to="/house" name="실거래 정보" icon="ti-map" />
        <sidebar-link to="/qna" name="Q&amp;A" icon="ti-help-alt" />
        <sidebar-link
          v-show="userInfo"
          to="/stats"
          name="회원 정보"
          icon="ti-user"
        />
      </template>
      <mobile-menu>
        <li class="nav-item">
          <a class="nav-link">
            <i class="ti-panel"></i>
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
            <p>회원 관리</p>
          </a>
        </li>
        <li class="divider"></li>
      </mobile-menu>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click.native="toggleSidebar"> </dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss"></style>
<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "./MobileMenu";
import { mapState, mapActions } from "vuex";
const user = "user";
export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu,
  },
  computed: {
    ...mapState(user, ["userInfo"]),
    username() {
      if (this.userInfo) return "안녕하세요 " + this.userInfo.username + "님";
      else return "안녕하세요 손님";
    },
  },
  methods: {
    ...mapActions(user, ["logoutUser"]),
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
    onClickLogout() {
      console.log(this.userInfo.userid);
      this.logoutUser(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/dashboard") this.$router.push({ name: "main" });
    },
  },
};
</script>
