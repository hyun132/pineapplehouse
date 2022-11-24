import http from "@/utils/http";
import moment from "moment";

const comment = {
  namespaced: true,
  state: {
    comments: [],
    userComments: [],
  },
  getters: {
    comments(state) {
      return state.comments;
    },
    userComments(state) {
      return state.userComments;
    },
  },
  mutations: {
    SET_COMMENTS(state, comments) {
      comments.map((comment) => {
        const obj = moment(comment.commenttime, "YYYY-MM-DD hh:mm:ss");
        comment.commenttime = obj.format("YYYY-MM-DD HH:mm");
        return comment;
      });
      state.comments = comments;
      // console.log(state.comments);
    },
    SET_USER_COMMENTS(state, comments) {
      comments.map((comment) => {
        const obj = moment(comment.commenttime, "YYYY-MM-DD hh:mm:ss");
        comment.commenttime = obj.format("YYYY-MM-DD HH:mm");
        return comment;
      });
      state.userComments = comments;
    },
  },
  actions: {
    getUserComments(context, userid) {
      http.get(`/comment/user/${userid}`).then(({ data }) => {
        context.commit("SET_USER_COMMENTS", data);
      });
    },

    deleteComment(context, payload) {
      http.delete(`/comment/${payload.commentno}`).then(({ data }) => {
        if (data === "success") {
          payload.callback();
        }
      });
    },

    modifyComment(context, payload) {
      http
        .put(`/comment/${payload.comment.commentno}`, payload.comment)
        .then(({ data }) => {
          if (data === "success") {
            payload.callback();
          }
        });
    },

    getComments(context, articleno) {
      // console.log(articleno);
      http.get(`/comment/${articleno}`).then(({ data }) => {
        context.commit("SET_COMMENTS", data);
      });
    },

    createComment(context, payload) {
      http.post("/comment", payload.comment).then(({ data }) => {
        if (data === "success") {
          payload.callback();
        }
      });
    },
  },
};

export default comment;
