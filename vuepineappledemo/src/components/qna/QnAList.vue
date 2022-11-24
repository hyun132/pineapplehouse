<template>
  <div>
    <!-- 메뉴 -->
    <b-row class="mb-4">
      <b-col class="text-right">
        <b-button variant="dark" @click="showModalRegist" class="mr-3"
          >Q&amp;A등록</b-button
        >
      </b-col>
    </b-row>

    <div class="col-12">
      <card>
        <div slot="raw-content" class="table-responsive">
          <qn-a-table
            v-if="qnalist"
            :data="qnalist"
            :columns="columns"
            :tableColumns="tableColumns"
          >
          </qn-a-table>
          <div v-else class="text-center m-5">등록된 QnA 목록이 없습니다.</div>
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

    <!-- 등록 모달 -->
    <b-modal
      header-text-variant="dark"
      title="Q&amp;A 등록"
      ref="qna-regist"
      centered
      hide-footer
      size="lg"
    >
      <card v-if="userInfo">
        <div class="row">
          <label for="userid" class="col-sm-2 col-form-label">작성자 ID</label>
          <div class="col-sm-10">
            <input
              type="text"
              readonly
              class="form-control-plaintext"
              :value="userInfo.userid"
            />
          </div>
        </div>
        <hr />
        <div class="row">
          <label for="subject" class="col-sm-2 col-form-label">제목</label>
          <div class="col-sm-10">
            <input
              id="subject"
              type="text"
              class="form-control"
              v-model="qna.subject"
            />
          </div>
        </div>
        <hr />
        <div>
          <textarea
            id="content"
            rows="4"
            class="form-control"
            v-model="qna.content"
          ></textarea>
        </div>
        <div class="text-right mt-2">
          <b-button-group>
            <b-button @click="regist" class="mr-1">등록</b-button>
            <b-button @click="hideModalRegist" class="mr-1">취소</b-button>
          </b-button-group>
        </div>
      </card>
    </b-modal>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import QnATable from "@/components/qna/item/QnATable";
const board = "board";
const qna = "qna";
const user = "user";
export default {
  components: {
    QnATable,
  },
  data() {
    return {
      type: "",
      columns: ["answer", "qnano", "subject", "userid", "qnaregtime"],
      tableColumns: ["답변 상태", "번호", "제목", "글쓴이", "작성일"],
      qna: {
        subject: "",
        content: "",
      },
    };
  },
  computed: {
    ...mapGetters(board, ["count", "params"]),
    ...mapGetters(qna, ["qnalist"]),
    ...mapGetters(user, ["userInfo"]),
  },
  created() {
    this.getQnaList();
    // console.log(this.qnalist);
  },
  methods: {
    ...mapActions(qna, ["getQnaList", "writeQnA"]),
    ...mapMutations(board, ["CLEAR_PARAMS", "SET_PARAMS"]),
    pageClick: function (button, pg) {
      this.SET_PARAMS({ pg: pg, key: this.params.key, word: this.params.word });
      this.getQnaList();
    },
    showModalRegist() {
      if (this.userInfo) this.$refs[`qna-regist`].show();
      else {
        alert("접근 권한이 없습니다.");
        // next({ name: "login" });
        this.$router.push({ name: "login" });
      }
    },
    hideModalRegist() {
      this.$refs[`qna-regist`].hide();
    },
    regist() {
      this.qna.userid = this.userInfo.userid;
      const payload = {
        qna: this.qna,

        callback: () => {
          this.hideModalRegist();
          this.getQnaList();
        },
      };
      this.writeQnA(payload);
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
.box {
  min-height: 70vh;
}
</style>
