import Vue from "vue";
import VueRouter from "vue-router";
import modules from "./modules/index";
import DashboardLayout from "@/layout/dashboard/DashboardLayout";
import Dashboard from "@/pages/Dashboard";
import UserProfile from "@/pages/UserProfile";
import Notifications from "@/pages/Notifications";
import Icons from "@/pages/Icons";
import Maps from "@/pages/Maps";
import Typography from "@/pages/Typography";
import TableList from "@/pages/TableList";
import NotFound from "@/pages/NotFoundPage";
import store from "@/store";
import House from "@/pages/House";

Vue.use(VueRouter);

// for user-service router-guard
const onlyAuthUser = async (to, from, next) => {
  let checkUserInfo = store.getters["user/checkUserInfo"];
  let checkToken = store.getters["user/checkToken"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo != null && token) {
    // token 유효성 체크
    await store.dispatch("user/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("접근 권한이 없습니다.");
    // next({ name: "login" });
    router.push({ name: "login" }).catch();
  } else {
    next();
  }
};

// for admin-service router-guard
const onlyAdminUser = async (to, from, next) => {
  let checkUserInfo = store.getters["user/checkUserInfo"];
  let checkToken = store.getters["user/checkToken"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo != null && token) {
    // token 유효성 체크
    await store.dispatch("user/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null || checkUserInfo.isadmin != 1) {
    alert("관리자만 접근 가능한 페이지입니다.");
    router.push({ name: "main" }).catch();
  } else {
    next();
  }
};

// configure router
const router = new VueRouter({
  routes: [
    {
      path: "/",
      name: "main",
      component: DashboardLayout,
      redirect: "/dashboard",
      children: [
        {
          path: "dashboard",
          name: "dashboard",
          component: Dashboard,
        },
        {
          path: "stats",
          name: "stats",
          component: UserProfile,
          redirect: "/stats/info",
          children: [
            {
              path: "info",
              name: "userinfo",
              beforeEnter: onlyAuthUser, // router-guard
              component: () => import("@/components/user/UserInfo"),
            },
            {
              path: "modify",
              name: "usermodify",
              component: () => import("@/components/user/UserModify"),
            },
          ],
        },
        {
          path: "notifications",
          name: "notifications",
          component: Notifications,
        },
        {
          path: "icons",
          name: "icons",
          component: Icons,
        },
        {
          path: "/house",
          name: "House",
          redirect: "/house/map",
          component: House,
          children: [
            {
              path: "map",
              name: "map",
              component: () => import("@/pages/Maps"),
            },
            {
              path: "detail",
              name: "detail",
              component: () => import("@/components/house/HouseView"),
            },
          ],
        },
        {
          path: "typography",
          name: "typography",
          component: Typography,
        },
        {
          path: "table-list",
          name: "table-list",
          component: TableList,
        },
        {
          path: "/board",
          name: "board",
          component: () => import("@/pages/Board"),
          redirect: "/board/list",
          children: [
            {
              path: "list",
              name: "boardlist",
              component: () => import("@/components/board/BoardList"),
            },
            {
              path: "write",
              name: "boardwrite",
              beforeEnter: onlyAuthUser, // router-guard
              component: () => import("@/components/board/BoardWrite"),
            },
            {
              path: "view/:articleno",
              name: "boardview",
              component: () => import("@/components/board/BoardView"),
            },
            {
              path: "modify",
              name: "boardmodify",
              beforeEnter: onlyAuthUser, // router-guard
              component: () => import("@/components/board/BoardModify"),
            },
            {
              path: "delete/:articleno",
              name: "boarddelete",
              component: () => import("@/components/board/BoardDelete"),
            },
          ],
        },
        {
          path: "/admin",
          name: "admin",
          component: () => import("@/pages/Admin"),
          redirect: "/admin/userlist",
          children: [
            {
              path: "userlist",
              name: "userlist",
              beforeEnter: onlyAdminUser, // router-guard
              component: () => import("@/components/admin/UserList"),
            },
          ],
        },
        {
          path: "user",
          name: "user",
          component: () => import("@/pages/User"),
          children: [
            {
              path: "join",
              name: "join",
              component: () => import("@/components/user/UserRegister"),
            },
            {
              path: "login",
              name: "login",
              component: () => import("@/components/user/UserLogin"),
            },
            {
              path: "findpassword",
              name: "findpassword",
              component: () => import("@/components/user/UserFindPassword"),
            },
            {
              path: "resetpassword",
              name: "resetpassword",
              component: () => import("@/components/user/UserResetPassword"),
            },
          ],
        },
        {
          path: "news",
          name: "news",
          component: () => import("@/pages/News"),
        },
        {
          path: "/qna",
          name: "qna",
          component: () => import("@/pages/QnA"),
          redirect: "/qna/qnalist",
          children: [
            {
              path: "qnalist",
              name: "Q&A",
              component: () => import("@/components/qna/QnAList"),
            },
          ],
        },
        {
          path: "/favorite",
          name: "favorite",
          component: () => import("@/pages/Favorite"),
        },
      ],
    },
    { path: "*", component: NotFound },
  ],
  linkActiveClass: "active",
  modules: {
    user: modules.user,
    admin: modules.admin,
    board: modules.board,
  },
});
export default router;
