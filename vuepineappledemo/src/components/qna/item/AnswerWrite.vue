<template>
  <div class="box p-3">
    <b-input-group v-if="userInfo" style="width: 240px" prepend="작성자">
      <b-form-input v-model="userInfo.userid" readonly></b-form-input>
    </b-input-group>
    <b-input-group>
      <b-form-textarea
        placeholder="답변을 입력하세요."
        rows="3"
        max-rows="6"
        v-model="answer"
      ></b-form-textarea>
      <b-input-group-append>
        <b-button variant="secondary" @click="modify">등록</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const qna = "qna";
const user = "user";
export default {
  data() {
    return {
      answer: "",
    };
  },
  methods: {
    ...mapActions(qna, ["modifyQnA", "getQnaList"]),
    modify() {
      this.qna.answer = this.answer;
      const payload = {
        qna: this.qna,
        callback: () => {
          this.answer = "";

          this.getQnaList();

          this.$bvToast.toast("답변이 등록되었습니다.", {
            title: "답변 알림",
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

<style scoped>
.box {
  background-color: #968f87;
}
</style>
