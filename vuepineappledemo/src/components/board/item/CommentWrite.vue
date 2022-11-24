<template>
  <div class="box p-3">
    <b-input-group v-if="userInfo" style="width: 240px" prepend="작성자">
      <b-form-input v-model="userInfo.userid" readonly></b-form-input>
    </b-input-group>
    <b-input-group>
      <b-form-textarea
        placeholder="댓글을 입력하세요."
        rows="3"
        max-rows="6"
        v-model="comment"
      ></b-form-textarea>
      <b-input-group-append>
        <b-button variant="secondary" @click="write">등록</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const comment = "comment";
const board = "board";
const user = "user";
export default {
  data() {
    return {
      comment: "",
    };
  },
  methods: {
    ...mapActions(comment, ["createComment", "getComments"]),
    write() {
      const payload = {
        comment: {
          articleno: this.article.articleno,
          userid: this.userInfo.userid,
          comment: this.comment,
        },
        callback: () => {
          this.comment = "";

          this.getComments(this.article.articleno);

          this.$bvToast.toast("댓글이 등록되었습니다.", {
            title: "댓글 알림",
            variant: "success",
            toaster: "b-toaster-bottom-center",
            autoHideDelay: 2000,
            solid: true,
          });
        },
      };

      this.createComment(payload);
    },
  },
  computed: {
    ...mapGetters(user, ["userInfo"]),
    ...mapGetters(board, ["article"]),
  },
};
</script>

<style scoped>
.box {
  background-color: #968f87;
}
</style>
