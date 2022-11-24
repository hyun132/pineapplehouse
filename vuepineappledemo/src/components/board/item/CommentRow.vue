<template>
  <b-list-group-item class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-3">
        <b>{{ comment.userid }}</b>
      </h5>
      <small class="text-muted">{{ comment.commenttime }}</small>
    </div>

    <p :inner-html.prop="comment.comment | filterEnterToBr"></p>

    <div class="text-right" v-if="userInfo && input.userid == userInfo.userid">
      <b-button-group>
        <b-button @click="showModalModify" class="mr-1">수정</b-button>
        <b-button @click="showModalDelete">삭제</b-button>
      </b-button-group>
    </div>

    <b-modal
      :ref="`comment-${comment.commentno}`"
      title="댓글 수정"
      header-bg-variant="dark"
      header-text-variant="light"
      centered
      hide-footer
    >
      <div>
        <b-input-group style="width: 240px" prepend="작성자">
          <b-form-input v-model="input.userid" readonly></b-form-input>
        </b-input-group>
        <b-form-textarea
          v-model="input.comment"
          placeholder="댓글을 입력하세요."
          rows="3"
          max-rows="6"
        ></b-form-textarea>
      </div>

      <div class="text-right">
        <b-button-group>
          <b-button @click="hideModalModify" class="mr-1">취소</b-button>
          <b-button @click="modify">수정</b-button>
        </b-button-group>
      </div>
    </b-modal>
  </b-list-group-item>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const comment = "comment";
const user = "user";
export default {
  props: {
    articleno: Number,
    comment: Object,
  },
  methods: {
    ...mapActions(comment, ["modifyComment", "getComments", "deleteComment"]),
    showModalModify() {
      this.$refs[`comment-${this.comment.commentno}`].show();
    },
    hideModalModify() {
      this.$refs[`comment-${this.comment.commentno}`].hide();
    },
    modify() {
      const payload = {
        comment: {
          commentno: this.comment.commentno,
          comment: this.input.comment,
        },
        callback: () => {
          this.input.userid = "";
          this.input.comment = "";
          this.getComments(this.articleno);

          this.hideModalModify();

          this.$bvToast.toast("댓글이 수정되었습니다.", {
            title: "댓글 알림",
            variant: "success",
            toaster: "b-toaster-bottom-center",
            autoHideDelay: 2000,
            solid: true,
          });
        },
      };
      this.modifyComment(payload);
    },
    showModalDelete() {
      this.$bvModal
        .msgBoxConfirm("정말로 삭제 하시겠습니까?", {
          centered: true,
        })
        .then((value) => {
          // console.log(value);
          if (value) {
            this.delete();
          }
        });
    },
    delete() {
      const payload = {
        commentno: this.comment.commentno,
        callback: () => {
          this.getComments(this.articleno);

          setTimeout(() => {
            this.$bvToast.toast("댓글이 삭제되었습니다.", {
              title: "댓글 알림",
              variant: "success",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 2000,
              solid: true,
            });
          }, 500);
        },
      };
      this.deleteComment(payload);
    },
  },
  computed: {
    ...mapGetters(user, ["userInfo"]),
    input() {
      return { ...this.comment };
    },
  },
};
</script>

<style></style>
