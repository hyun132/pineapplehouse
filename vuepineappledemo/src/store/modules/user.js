import http from "@/utils/http";
import jwtDecode from "jwt-decode";
import moment from "moment";
import router from "@/router";
import { login, findById, tokenRegeneration, logout } from "@/utils/user-admin";

const user = {
  namespaced: true,
  state: {
    users: [],
    user: {},

    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,

    isIdCheck: false,
    isPwdCheck: false,

    findUserId: "",
    todayUserCnt: 0,
    timeCnt: null,

    userpwd: "",
    userid: "",
    username: "",
    email: "",

    favorite: [],
  },
  getters: {
    users(state) {
      return state.users;
    },
    isIdCheck(state) {
      return state.isIdCheck;
    },
    isPwdCheck(state) {
      return state.isPwdCheck;
    },
    params(state) {
      return state.params;
    },
    count(state) {
      return state.count;
    },
    todayUserCnt(state) {
      return state.todayUserCnt;
    },
    timeCnt(state) {
      return state.timeCnt;
    },
    user(state) {
      return state.user;
    },
    isLogin(state) {
      return state.isLogin;
    },
    isLoginError(state) {
      return state.findUserId;
    },
    userInfo(state) {
      return state.userInfo;
    },
    isValidToken(state) {
      return state.isValidToken;
    },
    findUserId(state) {
      return state.findUserId;
    },
    userpwd(state) {
      return state.userpwd;
    },
    userid(state) {
      return state.userid;
    },
    username(state) {
      return state.username;
    },
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
    email(state) {
      return state.email;
    },
    favorite(state) {
      return state.favorite;
    },
  },
  mutations: {
    SET_USER_LIST(state, data) {
      state.users = data;
    },
    SET_USER(state, data) {
      state.user = data;
    },
    CLEAR_USER(state) {
      state.user = {};
    },
    SET_FIND_USER(state, data) {
      state.findUserId = data.userid;
    },
    SET_USERPWD(state, userpwd) {
      state.userpwd = userpwd;
    },
    SET_COUNT(state, count) {
      state.count = count;
    },
    SET_USERID(state, userid) {
      state.userid = userid;
    },
    SET_USERNAME(state, username) {
      state.username = username;
    },
    SET_EMAIL(state, email) {
      state.email = email;
    },
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
      console.log(state.isLogin);
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    CLEAR_FIND_USER(state) {
      state.findUserId = "";
    },
    SET_IS_ID_CHECK(state, idCheck) {
      state.isIdCheck = idCheck;
    },
    SET_IS_PWD_CHECK(state, isPwdCheck) {
      state.isPwdCheck = isPwdCheck;
    },
    SET_TODAY_USER_CNT(state, todayUserCnt) {
      state.todayUserCnt = todayUserCnt;
    },
    SET_TIME_CNT(state, timeCnt) {
      state.timeCnt = timeCnt;
    },
    ADD_FAVORITE(state, payload) {
      state.favorite.push(payload);
    },
    DELETE_FAVORITE(state, payload) {
      state.favorite = payload;
    },
    SET_FAVORITE(state, payload) {
      state.favorite = payload;
    },
  },
  actions: {
    getUserList({ rootState, commit }) {
      const params = rootState.board.params;
      http
        .get(
          "/admin/user" +
            `?pg=${params.pg}&key=${params.key}&word=${params.word}`
        )
        .then(({ data }) => {
          data.users.map((user) => {
            const obj = moment(user.joindate, "YYYY-MM-DD hh:mm:ss");
            user.joindate = obj.format("YYYY-MM-DD HH:mm");
            return user;
          });
          commit("SET_USER_LIST", data.users);
          commit("SET_TODAY_USER_CNT", data.todayUserCnt);
          commit("SET_TIME_CNT", data.timeCnt);
          commit("SET_COUNT", data.count);
        });
    },
    registUser(context, payload) {
      http.post(`/user`, payload.user).then(({ data }) => {
        if (data === "success") {
          let msg = "회원 가입이 완료되었습니다.";
          alert(msg);
          payload.callback();
        }
      });
    },
    deleteUser(context, payload) {
      let userid = payload.userid;
      if (payload.type == "user") userid = payload.user.userid;
      http.delete(`/user/${userid}`).then(({ data }) => {
        if (data === "success") {
          let msg = "탈퇴가 완료되었습니다.";
          alert(msg);
          if (payload.type == "user") {
            context.commit("SET_IS_LOGIN", false);
            context.commit("SET_USER_INFO", null);
            context.commit("SET_IS_VALID_TOKEN", false);
          }
          payload.callback();
        }
      });
    },
    modifyUser(context, payload) {
      console.log(payload);
      http.put(`/user`, payload.user).then(({ data }) => {
        if (data === "success") {
          let msg = "수정이 완료되었습니다.";
          if (payload.adminCheck) msg = "관리자 승격이 완료되었습니다.";

          alert(msg);
          payload.callback();
        }
      });
    },
    getUser(context, payload) {
      http.get(`/user/${payload.userid}`).then(({ data }) => {
        context.commit("SET_USER", data);
      });
    },
    async loginUser({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async logoutUser({ commit }, userid) {
      await logout(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("등록된 유저 정보가 없습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    findPassword(context, payload) {
      http.post(`/user/find`, payload.userinfo).then(({ data }) => {
        // console.log(response);
        context.commit("SET_FIND_USER", payload.userinfo);
        if (data === "success") {
          payload.callback();
        } else {
          alert("일치하는 회원 정보가 없습니다.");
        }
        // console.log(data);
      });
    },
    resetPassword(context, payload) {
      // console.log(payload);
      http.put(`/user/reset`, payload.userinfo).then((response) => {
        // console.log(response);
        if (response.data == "success") {
          alert("비밀번호 재설정 완료");
          context.commit("CLEAR_FIND_USER");
          payload.callback();
        }
        // console.log(data);
      });
    },
    idCheck(context, userid) {
      http.get(`/user/user/${userid}`).then(({ data }) => {
        if (data == "success") context.commit("SET_IS_ID_CHECK", false);
        else context.commit("SET_IS_ID_CHECK", true);
      });
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      // decode해서 userid 정보 보냄
      await findById(
        decodeToken.userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
          } else {
            alert("등록된 회원 정보가 없습니다.");
          }
        },
        async (error) => {
          // console.log(error.response.status);
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async getFavorite(context, userid) {
      await http.get(`/user/favorite/${userid}`).then(({ data }) => {
        // console.log(response);
        context.commit("SET_FAVORITE", data);
      });
    },
    async addFavorite(context, payload) {
      await http.post(`/user/favorite`, payload).then((response) => {
        // console.log(response);
        payload.id = response.data;
        console.log(payload.id + "vs" + response.id);
        if (response.status == 200) {context.commit("ADD_FAVORITE", payload); alert("관심 정보 설정이 완료되었습니다.");}
        else alert("관심지역 등록 실패");
      });
    },
    async deleteFavorite(context, id) {
      await http.delete(`/user/favorite/${id}`).then((response) => {
        console.log(response.status);
        if (response.status == 200) {
          context.commit("SET_FAVORITE",context.state.favorite.filter((item) => (item.id != id)))
          // context.commit("DELETE_FAVORITE", id);
           alert("관심 정보 삭제가 완료되었습니다.")}
        else alert("관심지역 삭제 실패");
      });
    },
    async tokenRegeneration({ commit, state }) {
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          if (error.response.status === 401) {
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("success");
                }
                alert("세션 시간 만료. 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" }).catch(() => true);
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },
  },
};

export default user;
