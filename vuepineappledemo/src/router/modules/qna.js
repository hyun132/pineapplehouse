import VueRouter from "vue-router";

export default new VueRouter({
  routes: [
    {
      path: "/qna",
      name: "qna",
      component: () => import("@/pages/QnA"),
    },
  ],
});
