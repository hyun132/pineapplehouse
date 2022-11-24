import VueRouter from "vue-router";

export default new VueRouter({
  routes: [
    {
      path: "/user",
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
  ],
});
