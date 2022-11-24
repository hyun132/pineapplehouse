<template>
  <div>
    <!-- 메뉴 -->
    <b-row class="mb-4">
      <b-col class="text-left">
        <b-button
          variant="dark"
          class="mr-2"
          @click="(type = 'articles'), CLEAR_PARAMS(), getList()"
          >전체글</b-button
        >
        <b-button
          variant="dark"
          @click="(type = 'notice'), CLEAR_PARAMS(), getList()"
          >공지사항</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-form-select
          v-model="params.key"
          :options="options"
          style="width: 20%"
          class="mr-2 select"
        ></b-form-select>
        <input style="width: 20%" class="mr-2" v-model="params.word" />
        <b-button variant="dark" @click="search" class="mr-2">검색</b-button>
        |
        <b-button variant="dark" @click="movePage" class="ml-2"
          >글 등록</b-button
        >
      </b-col>
    </b-row>

    <div class="col-12">
      <card>
        <div slot="raw-content" class="table-responsive">
          <paper-table
            v-if="articles"
            listType="boardlist"
            :data="articles"
            :columns="columns"
            :tableColumns="tableColumns"
          >
          </paper-table>
          <div v-else class="text-center m-5">게시글이 없습니다.</div>
        </div>
      </card>
    </div>

    <b-pagination
      :current-page="params.pg"
      :total-rows="count"
      align="center"
      variant="dark"
      @page-click="pageClick"
      class="customPagination"
    ></b-pagination>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import { PaperTable } from "@/components";
const board = "board";
export default {
  components: {
    PaperTable,
  },
  data() {
    return {
      type: "",
      options: [
        { value: "", text: "검색 조건" },
        { value: "subject", text: "제목" },
        { value: "userid", text: "id" },
      ],
      columns: ["articleno", "subject", "userid", "regtime"],
      tableColumns: ["글번호", "제목", "글쓴이", "작성일"],
    };
  },
  computed: {
    ...mapGetters(board, ["articles", "count", "params"]),
  },
  created() {
    this.getArticles();
    // console.log("어이", this.articles[2]);
  },
  methods: {
    ...mapActions(board, ["getArticles", "getNotice"]),
    ...mapMutations(board, ["CLEAR_PARAMS", "SET_PARAMS"]),
    movePage: function () {
      this.$router.push({ name: "boardwrite" });
    },
    pageClick: function (button, pg) {
      this.SET_PARAMS({ pg: pg, key: this.params.key, word: this.params.word });
      this.getList();
    },
    getList: function () {
      this.loading = true;
      if (this.type == "articles" || this.type == "") {
        this.getArticles();
      } else if (this.type == "notice") {
        this.getNotice();
      }
    },
    search() {
      this.SET_PARAMS({ pg: 1, key: this.params.key, word: this.params.word });
      this.getList();
    },
  },
};
</script>

<style scoped>
::v-deep .customPagination .page-link {
  background: #fffcf5;
  color: black;
  border: #fffcf5 1px solid;
}
::v-deep .customPagination .active button {
  background: white;
}
input {
  border-radius: 4px;
  height: 36px;
  border-color: grey;
  border-width: 1px;
}
.box {
  min-height: 70vh;
}
</style>
