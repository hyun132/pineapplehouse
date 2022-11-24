import http from "@/utils/http";
import moment from "moment";

const qna = {
  namespaced: true,
  state: {
    qnalist: null,
    qna: null,
  },
  getters: {
    qnalist: (state) => state.qnalist,
    qna: (state) => state.qna,
  },
  mutations: {
    SET_QNA_LIST(state, data) {
      state.qnalist = data;
      // console.log(state.qnalist.count);
    },
    SET_QNA(state, data) {
      state.qna = data;
    },
  },
  actions: {
    getQnaList(context) {
      const params = context.rootState.board.params;
      http
        .get("/qna" + `?pg=${params.pg}&key=${params.key}&word=${params.word}`)
        .then(({ data }) => {
          // console.log(data);
          context.rootState.board.count = data.count;
          context.commit("SET_QNA_LIST", data.qna);
        });
    },
    deleteQnA(context, payload) {
      http.delete(`/qna/${payload.qnano}`).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        payload.callback();
      });
    },
    getQnA(context, qnano) {
      http.get(`/qna/${qnano}`).then(({ data }) => {
        context.commit("SET_QNA", data);
      });
    },
    modifyQnA(context, payload) {
      console.log(payload);
      http.put(`/qna`, payload.qna).then(({ data }) => {
        // 현재 route를 /list로 변경.
        payload.callback();
      });
    },
    writeQnA(context, payload) {
      console.log(payload.qna);
      http.post("/qna", payload.qna).then(({ data }) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (data === "success") msg = "등록이 완료되었습니다.";
        alert(msg);
        payload.callback();
      });
    },
  },
};

export default qna;
