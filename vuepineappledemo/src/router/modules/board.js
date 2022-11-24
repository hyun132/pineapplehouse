import VueRouter from "vue-router";

export default new VueRouter({
  routes: [
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
          component: () => import("@/components/board/BoardModify"),
        },
        {
          path: "delete/:articleno",
          name: "boarddelete",
          component: () => import("@/components/board/BoardDelete"),
        },
      ],
    },
  ],
});
