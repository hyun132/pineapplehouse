import http from "@/utils/http";
import moment from "moment";

const board = {
  namespaced: true,
  state: {
    article: {},
    articles: null,
    params: {
      pg: 1,
      key: "",
      word: "",
    },
    count: 0,
    userArticles: null,
  },
  getters: {
    article(state) {
      return state.article;
    },
    articles(state) {
      return state.articles;
    },
    params(state) {
      return state.params;
    },
    count(state) {
      return state.count;
    },
    userArticles(state) {
      return state.userArticles;
    },
  },
  mutations: {
    CLEAR_PARAMS(state) {
      state.params = {
        pg: 1,
        key: "",
        word: "",
      };
    },
    SET_PARAMS(state, params) {
      state.params = params;
    },
    SET_ARTICLE(state, data) {
      state.article = data;
    },
    SET_USER_ARTICLES(state, articles) {
      articles.map((article) => {
        const obj = moment(article.regtime, "YYYY-MM-DD hh:mm:ss");
        article.regtime = obj.format("YYYY-MM-DD HH:mm");
        return article;
      });
      state.userArticles = articles;
      // console.log(state.userArticles);
    },
    ARTICLES(state, payload) {
      state.count = payload.count;
      payload.articles.map((article) => {
        if (article.regtime.split(":").length > 1) {
          let registerTime = article.regtime.split(":");
          let articleHour = registerTime[0];
          let articleMinutes = registerTime[1];
          var date = new Date();
          let time =
            (date.getHours() - articleHour) * 60 +
            (date.getMinutes() - articleMinutes);
          if (time <= 60) {
            article.regtime = time + "분 전";
          } else {
            const obj = moment(article.regtime, "hh:mm:ss");
            article.regtime = obj.format("HH:mm");
          }
        }
        return article;
      });
      state.articles = payload.articles;
    },
  },
  actions: {
    getUserArticles(context, userid) {
      http.get(`/board/user/${userid}`).then(({ data }) => {
        context.commit("SET_USER_ARTICLES", data);
      });
    },
    deleteArticle(context, payload) {
      http.delete(`/board/${payload.articleno}`).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        payload.callback();
      });
    },
    getArticle(context, articleno) {
      http.get(`/board/${articleno}`).then(({ data }) => {
        context.commit("SET_ARTICLE", data);
      });
    },
    modifyArticle(context, payload) {
      http.put(`/board`, payload.article).then(({ data }) => {
        let msg = "수정 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "수정이 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        payload.callback();
      });
    },
    writeArticle(context, payload) {
      console.log(payload.article);
      http.post("/board", payload.article).then(({ data }) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (data === "success") msg = "등록이 완료되었습니다.";
        alert(msg);
        payload.callback();
      });
    },
    getArticles(context) {
      const store = this.state.board.params;
      http
        .get("/board" + `?pg=${store.pg}&key=${store.key}&word=${store.word}`)
        .then(({ data }) => {
          // console.log(data);
          context.commit("ARTICLES", data);
        });
    },
    getNotice(context) {
      const store = this.state.board.params;
      http
        .get(
          "/admin/notice" +
            `?pg=${store.pg}&key=${store.key}&word=${store.word}`
        )
        .then(({ data }) => {
          context.commit("ARTICLES", data);
        });
    },
  },
};

export default board;
