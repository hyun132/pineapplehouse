import http from "@/utils/http";
import axios from "axios";

const house = {
  namespaced: true,
  state: () => ({
    sidos: null,
    guguns: [{value: null, text: "선택하세요"}],
    dongs: [{value: null, text: "선택하세요"}],
    houses: [],
    favDongCode: null,
    house: Object,
    detail: Object,
    chartData:[]
  }),
  mutations: {
    /////////////////////////////// House start /////////////////////////////////////
    SET_SIDO_LIST(state, list) {
      let temp = [[{value: null, text: "선택하세요"}]];
      list.forEach((sido) => {

        temp.push({value: sido.regionCode, text: sido.regionName});
        console.log(sido)
      });
      state.sidos = temp;
      console.log(state.sidos)
    },
    SET_GUGUN_LIST(state, list) {
      list.forEach((gugun) => {
        state.guguns.push({value: gugun.regionCode, text: gugun.regionName});
      });
      console.log(" state.guguns", state.guguns)
    },
    SET_DONG_LIST(state, list) {
      list.forEach((dong) => {
        state.dongs.push({value: dong.regionCode, text: dong.regionName});
      });

      console.log(" state.dongs", state.dongs)
    },

    CLEAR_SIDO_LIST(state) {
      state.sidos = [{value: null, text: "선택하세요"}];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{value: null, text: "선택하세요"}];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{value: null, text: "선택하세요"}];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      // console.log("Mutations", house);
      state.detail = house;
    },
    SET_SELECTED_HOUSE: (state, house) => {
      state.house = house;
      console.log("SET_SELECTED_HOUSE called ", house);
    },
    SET_SELECTED_CHART_DATA: (state, data) => {
      console.log("SET_SELECTED_CHART_DATA called ", data);
      state.chartData = data;
    },
    SET_DONG(state,dongCode) {
      state.dongCode = dongCode
    },
  },
  actions: {
    getSido({commit}) {
      commit("CLEAR_SIDO_LIST")

      http
        .get(`/house/sido`)
        .then(({data}) => {
          console.log(data);
          // console.log(context);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({commit}, sidoCode) {
      commit("CLEAR_GUGUN_LIST");
      const params = {sido: sidoCode};
      http
        .get(`/house/gugun`, {params})
        .then(({data}) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({commit}, gugunCode) {
      commit("CLEAR_DONG_LIST");
      const params = {gugun: gugunCode};
      http
        .get(`/house/dong`, {params})
        .then(({data}) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseList({commit}, {dong, date}) {
      commit("CLEAR_APT_LIST", []);
      return http
        .request({
          url: `/house/${dong}`,
          method: "get",
        })
        .then((response) => {
          console.log(response);
          console.log(response.data);
          commit("SET_HOUSE_LIST", response.data);
        })
        .catch((e) => console.log(e + "err"));
    },

    getFavHouse({commit}, {dong, aptCode}) {
      return http
        .request({
          url: `/house/${dong}/${aptCode}`,
          method: "get",
        })
        .then((response) => {
          console.log(response.data);
          commit("SET_SELECTED_HOUSE", response.data);
        })
        .catch((e) => console.log(e + "err"));
    },

    detailHouse({commit}, aptCode) {
      console.log("detailHouse",aptCode)
      // axios.defaults.withCredentials = true;
      return http
        .get(`/house/houseDetail/${aptCode}`)
        .then((response) => {
          console.log(response.data);
          commit("SET_DETAIL_HOUSE", response.data);
        })
        .catch((e) => console.log(e + "err"));
    },

    getChartData({commit}, aptCode) {
      console.log("getChartData",aptCode)
      // commit("CLEAR_APT_LIST", []);
      // axios.defaults.withCredentials = true;
      return http
        .get(`/house/housePriceHistory/${aptCode}`)
        .then((response) => {
          console.log(response.data);
          commit("SET_SELECTED_CHART_DATA", response.data);
        })
        .catch((e) => console.log(e + "err"));
    },

  }, getters: {
    GHouseList: state => state.houses,
    GHouse: state => state.house,
    GDetail :state=> {console.log("detail" + state.detail); return state.detail},
    SelectedHouse: state => state["house/house"],
    SidoList: state => state.sidos,
    GugunList: state => state.guguns,
    DongList: state => state.dongs,
    chartData: state =>{ console.log("detail" + state.chartData); return state.chartData},
  }
}

export default house


