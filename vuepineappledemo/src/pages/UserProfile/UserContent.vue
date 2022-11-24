<template>
  <card class="card" :title="title">
    <div>
      <ul class="list-unstyled team-members">
        <li v-if="title == '내가 쓴 댓글'">
          <div
            class="row"
            v-for="comment in userComments"
            :key="comment.commentno"
          >
            <div class="col-6 ml-3">
              <router-link
                :to="{
                  name: 'boardview',
                  params: { articleno: comment.articleno },
                }"
              >
                {{ comment.comment }}
              </router-link>
              <br />
            </div>
            | {{ comment.commenttime }}
          </div>
        </li>
        <li v-else>
          <div
            class="row"
            v-for="article in userArticles"
            :key="article.articleno"
          >
            <div class="col-6 ml-3">
              <router-link
                :to="{
                  name: 'boardview',
                  params: { articleno: article.articleno },
                }"
              >
                {{ article.subject }}
              </router-link>
              <br />
            </div>
            | {{ article.regtime }}
          </div>
        </li>
      </ul>
    </div>
  </card>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
const board = "board";
const comment = "comment";
const user = "user";
export default {
  props: {
    title: String,
  },
  data() {
    return {};
  },
  created() {
    // console.log(this.userInfo.userid);
    if (this.title == "내가 쓴 글") {
      this.getUserArticles(this.userInfo.userid);
    } else {
      this.getUserComments(this.userInfo.userid);
      // console.log(this.userComments);
    }
  },
  computed: {
    ...mapGetters(user, ["userInfo"]),
    ...mapGetters(comment, ["userComments"]),
    ...mapGetters(board, ["userArticles"]),
  },
  methods: {
    ...mapActions(comment, ["getUserComments"]),
    ...mapActions(board, ["getUserArticles"]),
  },
};
</script>
<style></style>
