<template>
  <div class="col-12">
    <b-row class="mb-1">
      <!-- 왼쪽 -->
      <b-col class="text-right mb-3">
        <router-link to="/board/list">
          <b-button variant="dark">목록</b-button>
        </router-link>
      </b-col>
    </b-row>
    <card>
      <h3>
        <strong>{{ article.subject }}</strong>
      </h3>
      <div style="height: 50px; padding-left: 1rem">
        <div style="font-size: 1.5ch">
          <strong>{{ article.userid }}</strong> | {{ article.regtime }} |
          {{ article.hit }}
        </div>
      </div>

      <div
        style="height: 300px; padding-left: 1rem"
        :inner-html.prop="article.content"
      ></div>
    </card>
    <b-row
      v-if="userInfo && userInfo.userid == article.userid"
      class="mt-1 mb-3"
    >
      <!-- 오른쪽 -->
      <b-col class="text-right">
        <b-button-group>
          <b-button variant="dark" @click="modify" class="mr-2"
            >글수정</b-button
          >
          <b-button variant="dark" @click="remove">글삭제</b-button>
        </b-button-group>
      </b-col>
    </b-row>

    <b-list-group>
      <comment-row
        v-for="comment in comments"
        :key="comment.commentno"
        :articleno="article.articleno"
        :comment="comment"
      ></comment-row>
    </b-list-group>
    <comment-write></comment-write>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
const board = "board";
const user = "user";
const comment = "comment";
export default {
  components: {
    "comment-write": () => import("@/components/board/item/CommentWrite.vue"),
    "comment-row": () => import("@/components/board/item/CommentRow.vue"),
  },
  data: function () {
    return {};
  },
  methods: {
    ...mapActions(board, ["getArticle"]),
    ...mapActions(comment, ["getComments"]),
    modify(event) {
      event.preventDefault();
      this.$router.push({
        name: "boardmodify",
        params: this.article.articleno,
      });
    },
    remove(event) {
      event.preventDefault();
      this.$router.push({
        name: "boarddelete",
        params: this.article.articleno,
      });
    },
  },
  computed: {
    ...mapGetters(board, ["article"]),
    ...mapGetters(comment, ["comments"]),
    ...mapState(user, ["userInfo"]),
  },
  created() {
    const articleno = this.$route.params.articleno;
    // action 함수 호출
    this.getArticle(articleno);
    // console.log(articleno);
    this.getComments(articleno);
    // console.log(this.userInfo);
  },
};
</script>

<style scoped>
.content {
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 480px;
}
h3 {
  height: 80px;
  background-color: #fffcf5;
  border-radius: 10px;
  display: flex;
  align-items: center;
  padding: 1rem;
}
</style>
