<template>
  <card>
    <b-row class="m-2">
      <b-col>
        <b-form-input
          class="mb-3 input"
          id="subject"
          v-model="input.subject"
          type="text"
          placeholder="제목을 입력하세요."
          required
          ref="subject"
        />

        <b-row>
          <b-col>
            <b-form-textarea
              class="mb-3 input"
              id="content"
              v-model="input.content"
              rows="20"
              max-rows="10"
              placeholder="내용을 입력하세요."
              required
              ref="content"
            ></b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="text-right">
          <b-col class="mt-3">
            <b-button @click="registArticle" variant="dark" class="mr-2"
              >등록</b-button
            >
            <b-button @click="moveList" variant="dark">목록</b-button>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </card>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
const user = "user";
const board = "board";
export default {
  name: "BoardInputItem",
  props: {
    // 부모 component로 부터 전달받은 type 정보를 받아옴
    type: { type: String },
  },
  data: function () {
    return {};
  },
  computed: {
    ...mapGetters(board, ["article"]),
    ...mapState(user, ["userInfo"]),
    input() {
      if (this.type == "modify") {
        // 수정화면일 경우
        return { ...this.article };
      } else {
        // 등록화면일 경우
        return {
          subject: "",
          content: "",
        };
      }
    },
  },
  methods: {
    ...mapActions(board, ["writeArticle", "modifyArticle"]),
    registArticle() {
      this.input.userid = this.userInfo.userid;
      const payload = {
        article: this.input,
        callback: () => {
          this.moveList();
        },
      };
      if (this.type == "register") {
        this.writeArticle(payload);
      } else {
        this.modifyArticle(payload);
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
