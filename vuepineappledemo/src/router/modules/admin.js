import VueRouter from "vue-router";

export default new VueRouter({
  routes: [
    {
      path: "/admin",
      name: "admin",
      component: () => import("@/pages/Admin"),
      redirect: "/admin/userlist",
      children: [
        {
          path: "userlist",
          name: "userlist",
          component: () => import("@/components/admin/UserList"),
        },
      ],
    },
  ],
});
