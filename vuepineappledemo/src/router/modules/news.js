import VueRouter from "vue-router";

export default new VueRouter({
  routes: [
    {
      path: "/news",
      name: "news",
      component: () => import("@/pages/News"),
    },
  ],
});
