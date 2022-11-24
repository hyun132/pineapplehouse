<template>
  <table class="table">
    <thead>
      <slot name="columns">
        <th v-for="(column, index) in tableColumns" :key="index">
          {{ column }}
        </th>
      </slot>
    </thead>
    <tbody>
      <tr v-for="(item, index) in data" :key="index">
        <slot :row="item">
          <td
            v-for="(column, index) in columns"
            :key="index"
            @click="showModal(item['qnano'])"
          >
            {{ itemValue(item, column) }}
          </td>
        </slot>
      </tr>
    </tbody>

    <!-- 상세 & 수정 모달 -->
    <b-modal
      v-if="qna"
      header-text-variant="dark"
      title="Q&amp;A 상세"
      ref="qna"
      centered
      hide-footer
      size="lg"
    >
      <card>
        <div class="row">
          <label for="userid" class="col-sm-2 col-form-label">작성자 ID</label>
          <div class="col-sm-10">
            <input
              type="text"
              readonly
              class="form-control-plaintext"
              v-model="qna.userid"
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
              readonly
              :class="form"
              v-model="qna.subject"
            />
          </div>
        </div>
        <hr />
        <div>
          <textarea
            :class="form"
            id="content"
            readonly
            rows="4"
            v-model="qna.content"
          ></textarea>
        </div>

        <div class="text-right mt-2">
          <b-button-group>
            <b-button @click="hideModal" class="mr-1">닫기</b-button>
            <div v-if="userInfo && userInfo.userid == qna.userid">
              <b-button
                @click="mvmodify"
                v-if="form == 'form-control-plaintext'"
                class="mr-1"
                >수정</b-button
              >
              <b-button @click="modify" v-else class="mr-1">수정 완료</b-button>
              <b-button @click="remove">삭제</b-button>
            </div>
          </b-button-group>
        </div>
      </card>
      <answer-row v-if="qna.answer"></answer-row>
      <answer-write v-if="userInfo && userInfo.isadmin == 1"></answer-write>
    </b-modal>
  </table>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
const qna = "qna";
const user = "user";
export default {
  name: "qn-a-table",
  props: {
    columns: Array,
    tableColumns: Array,
    data: Array,
    type: {
      type: String, // striped | hover
      default: "hover",
    },
  },
  components: {
    "answer-write": () => import("@/components/qna/item/AnswerWrite.vue"),
    "answer-row": () => import("@/components/qna/item/AnswerRow.vue"),
  },
  data() {
    return {
      form: "form-control-plaintext",
    };
  },
  computed: {
    ...mapGetters(qna, ["qna"]),
    ...mapGetters(user, ["userInfo"]),
  },
  methods: {
    ...mapActions(qna, ["getQnA", "getQnaList", "deleteQnA", "modifyQnA"]),
    hasValue(item, column) {
      return item[column.toLowerCase()] !== "undefined";
    },
    itemValue(item, column) {
      if (column == `answer`) {
        // console.log(item[column]);
        if (item[column] == null) item[column] = "답변 대기";
        else if (item[column] != null && item[column] != "답변 대기")
          item[column] = "답변 완료";
      }
      return item[column.toLowerCase()];
    },
    showModal(qnano) {
      // console.log(qnano);
      this.getQnA(qnano);
      console.log(this.qna);
      this.$refs[`qna`].show();
    },
    hideModal() {
      this.$refs[`qna`].hide();
    },
    remove() {
      if (confirm("정말 삭제하시겠습니까?")) {
        const payload = {
          qnano: this.qna.qnano,
          callback: () => {
            this.hideModal();
            this.getQnaList();
          },
        };
        this.deleteQnA(payload);
      }
    },
    mvmodify() {
      document.getElementById("subject").removeAttribute("readonly");
      document.getElementById("content").removeAttribute("readonly");

      this.form = "form-control";
    },
    modify() {
      const payload = {
        qna: this.qna,
        callback: () => {
          this.getQnaList();
          document.getElementById("subject").setAttribute("readonly", true);
          document.getElementById("content").setAttribute("readonly", true);
          this.form = "form-control-plaintext";
        },
      };
      this.modifyQnA(payload);
    },
    // moveDetailView(aptInfo) {
    //   this.setHouse(aptInfo);
    //   this.$router.push({
    //     name: "detail",
    //   });
    // },
  },
};
</script>
<style></style>
