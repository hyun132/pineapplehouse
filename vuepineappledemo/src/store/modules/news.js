import http from "@/utils/http";

const state = () => ({
  sidos: [{ value: null, text: "선택하세요" }],
  newsList: null,
});

const mutations = {
  SET_SIDO_LIST(state, list) {
    list.forEach((sido) => {
      state.sidos.push({ value: sido.regionCode, text: sido.regionName });
    });

    console.log(state.sidos);
  },
  CLEAR_SIDO_LIST(state) {
    state.sidos = [{ value: null, text: "선택하세요" }];
  },
  SET_NEWS_LIST(state, newsList) {
    state.newsList = newsList;
  },
  CLEAR_NEWS_LIST(state) {
    state.newsList = null;
  },
};
const actions = {
  getSido({ commit }) {
    commit("CLEAR_SIDO_LIST");

    http
      .get(`/house/sido`)
      .then(({ data }) => {
        // console.log(data);
        // console.log(context);
        commit("SET_SIDO_LIST", data);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  getNewsList({ commit }, sidoCode) {
    // console.log(sidoCode);
    http.get(`/news/${sidoCode}`).then(({ data }) => {
      // console.log(data);
      commit("SET_NEWS_LIST", data.newslist);
    });
  },
};

const getter = {
  SidoList(state) {
    return state["news/sidos"];
  },
};

export default {
  namespaced: true,
  actions,
  state,
  getter,
  mutations,
};
