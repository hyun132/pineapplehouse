<template>
  <b-list-group-item class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-3">
        <b>답변</b>
      </h5>
    </div>

    <p
      v-if="show == 'none'"
      :inner-html.prop="qna.answer | filterEnterToBr"
    ></p>
    <b-form-textarea
      v-else
      rows="3"
      max-rows="6"
      v-model="qna.answer"
    ></b-form-textarea>

    <div class="text-right" v-if="userInfo && userInfo.isadmin == 1">
      <b-button-group>
        <b-button v-if="show == 'none'" @click="mvmodify" class="mr-1"
          >수정</b-button
        >
        <b-button v-else @click="modify('modify')" class="mr-1"
          >수정 완료</b-button
        >
        <b-button @click="modify('remove')">삭제</b-button>
      </b-button-group>
    </div>
  </b-list-group-item>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const qna = "qna";
const user = "user";
export default {
  data() {
    return {
      show: "none",
    };
  },
  methods: {
    ...mapActions(qna, ["modifyQnA", "getQnaList"]),
    mvmodify() {
      this.show = "show";
    },
    modify(type) {
      if (type == "remove") this.qna.answer = null;
      else this.show = "none";
      const payload = {
        qna: this.qna,
        callback: () => {
          this.getQnaList();

          this.$bvToast.toast("답변이 수정되었습니다.", {
            title: "답변 수정",
            variant: "success",
            toaster: "b-toaster-bottom-center",
            autoHideDelay: 2000,
            solid: true,
          });
        },
      };

      this.modifyQnA(payload);
    },
  },
  computed: {
    ...mapGetters(user, ["userInfo"]),
    ...mapGetters(qna, ["qna"]),
  },
};
</script>

<style></style>
