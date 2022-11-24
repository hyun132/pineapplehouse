import Vuex, { createLogger } from "vuex";

import Vue from "vue";

import house from "@/store/modules/house.js";
import user from "@/store/modules/user.js";
import news from "@/store/modules/news";
import board from "@/store/modules/board.js";
import comment from "@/store/modules/comment.js";
import qna from "@/store/modules/qna.js";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);
export default new Vuex.Store({
  modules: {
    house: house,
    user: user,
    news: news,
    board: board,
    comment: comment,
    qna: qna,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
