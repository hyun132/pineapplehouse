<template>
  <div class="col-12">
    <card>
      <div>삭제 처리 중...</div>
    </card>
  </div>
</template>

<script>
import { mapActions } from "vuex";
const board = "board";
export default {
  created() {
    const payload = {
      articleno: this.$route.params.articleno,
      callback: () => {
        this.$router.push({ name: "boardlist" });
      },
    };
    console.log(payload);
    this.deleteArticle(payload);
  },
  methods: {
    ...mapActions(board, ["deleteArticle"]),
  },
};
</script>

<style scoped></style>
