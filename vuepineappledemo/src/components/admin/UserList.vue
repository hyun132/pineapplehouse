<template>
  <div>
    <div
      class="row col"
      style="
        display: flex;

        justify-content: center;
      "
    >
      <div
        class="col-md-3 col-xl-3 ml-1"
        v-for="stats in statsCards"
        :key="stats.title"
      >
        <stats-card>
          <div
            class="icon-big text-center"
            :class="`icon-${stats.type}`"
            slot="header"
          >
            <i :class="stats.icon"></i>
          </div>
          <div class="numbers" slot="content">
            <p>{{ stats.title }}</p>
            {{ stats.value }}
          </div>
          <div class="stats" slot="footer">
            <i :class="stats.footerIcon"></i> {{ stats.footerText }}
          </div>
        </stats-card>
      </div>
      <div class="col-md-6 col-12">
        <chart-card
          title="시간대별 회원 가입 증가 추이"
          :chart-data="userChart.data"
          :chart-options="userChart.options"
        >
          <div slot="legend">
            <i class="fa fa-circle text-info"></i> 1시간 간격
          </div>
        </chart-card>
      </div>
    </div>

    <b-row class="mb-2 ml-1">
      <b-col class="text-left">
        <b-form-select
          v-model="params.key"
          :options="options"
          style="width: 20%"
          class="mr-2 select"
        ></b-form-select>
        <input style="width: 20%" class="mr-2 word" v-model="params.word" />
        <b-button variant="dark" @click="search">검색</b-button>
      </b-col>
      <b-col class="text-right mr-3">
        <b-button variant="dark" @click="beforeRegistUser">회원 등록</b-button>
      </b-col>
    </b-row>

    <div class="col-12">
      <card>
        <paper-table
          v-if="users.length"
          listType="userlist"
          :data="users"
          :columns="columns"
          :tableColumns="tableColumns"
          @openUserModal="rowClickListener"
        ></paper-table>
        <div v-else class="text-center m-5">회원 목록이 없습니다.</div>
      </card>
    </div>

    <!-- 회원 모달 -->
    <b-modal
      hide-footer
      centered
      size="lg"
      v-model="show"
      header-text-variant="dark"
      header-
      title="회원 정보"
    >
      <form ref="form" @submit="action">
        <label for="username" class="form-label"><strong>이름 </strong></label>
        <div v-if="type == 'modify'" class="mb-2 view">
          {{ user.username }}
        </div>
        <b-form-input
          v-else
          id="name-input"
          v-model="user.username"
          placeholder="이름을 입력하세요."
          required
          class="mb-2"
        ></b-form-input>
        <label for="userid" class="form-label"><strong>아이디</strong></label>
        <div v-if="type == 'modify'" class="mb-2 view">{{ user.userid }}</div>
        <b-form-group
          v-else
          :invalid-feedback="invalidFeedbackIdCheck"
          valid-feedback=""
          :state="stateIdCheck"
        >
          <b-form-input
            id="id-input"
            v-model="user.userid"
            :state="stateIdCheck"
            placeholder="아이디를 입력하세요."
            required
            class="mb-2"
          ></b-form-input>
        </b-form-group>

        <div id="idcheck-result"></div>
        <label for="userpwd" class="form-label"
          ><strong>비밀번호</strong></label
        >
        <b-form-input
          v-if="type == 'modify'"
          id="pwd-input"
          v-model="user.userpwd"
          :placeholder="user.userpwd"
          required
          class="mb-2"
        ></b-form-input>
        <b-form-input
          v-else
          id="pwd-input"
          v-model="user.userpwd"
          placeholder="비밀번호를 입력하세요."
          required
          class="mb-2"
        ></b-form-input>
        <b-form-valid-feedback id="checkpwd-input"> </b-form-valid-feedback>
        <label for="email" class="form-label"><strong>이메일</strong></label>
        <b-form-input
          v-if="type == 'modify'"
          id="email-input"
          v-model="user.email"
          :placeholder="user.email"
          required
          class="mb-2"
        ></b-form-input>
        <b-form-input
          v-else
          id="email-input"
          v-model="user.email"
          placeholder="비밀번호를 입력하세요."
          required
          class="mb-2"
        ></b-form-input>

        <div class="w-100">
          <div class="float-right">
            <b-button
              v-if="type == 'modify' && user.isadmin == '사용자'"
              variant="dark"
              class="mr-1"
              @click="admin = true"
              type="submit"
            >
              관리자로 승격
            </b-button>
            <b-button
              v-if="type == 'modify'"
              variant="dark"
              class="mr-1"
              type="submit"
            >
              수정
            </b-button>
            <b-button
              variant="dark"
              v-if="type == 'modify'"
              class="mr-1"
              @click="deleteuser"
            >
              삭제
            </b-button>

            <b-button variant="dark" v-else class="mr-1" type="submit">
              등록
            </b-button>
            <b-button variant="dark" @click="show = false"> 닫기 </b-button>
          </div>
        </div>
      </form>
    </b-modal>
    <!-- 회원 모달 -->

    <b-pagination
      :total-rows="count"
      :current-page="params.pg"
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
import { StatsCard, ChartCard } from "@/components/index";
const user = "user";
const board = "board";
export default {
  components: {
    PaperTable,
    StatsCard,
    ChartCard,
  },
  data() {
    return {
      admin: false,
      userChart: {
        data: {
          labels: [],
          series: [],
        },
        options: {
          seriesBarDistance: 10,
          axisX: {
            showGrid: false,
          },
          height: "245px",
        },
      },
      statsCards: [
        {
          type: "warning",
          icon: "ti-face-smile",
          title: "Total",
          value: 0,
          footerText: "",
          footerIcon: "ti-reload",
        },
        {
          type: "danger",
          icon: "ti-pulse",
          title: "Today",
          value: 0,
          footerText: "",
          footerIcon: "ti-reload",
        },
      ],
      type: "",
      show: false,
      options: [
        { value: "", text: "검색 조건" },
        { value: "subject", text: "이름" },
        { value: "userid", text: "id" },
      ],
      columns: [
        "userid",
        "username",
        "userpwd",
        "email",
        "joindate",
        "isadmin",
      ],
      tableColumns: ["ID", "이름", "비밀번호", "이메일", "가입일", "구분"],
    };
  },
  computed: {
    ...mapGetters(board, ["params"]),
    ...mapGetters(user, [
      "count",
      "users",
      "user",
      "timeCnt",
      "todayUserCnt",
      "isIdCheck",
    ]),
    stateIdCheck() {
      if (this.user.userid) this.idCheck(this.user.userid);
      return this.isIdCheck;
    },
    invalidFeedbackIdCheck() {
      if (this.user.userid && !this.isIdCheck) return "중복된 아이디입니다.";
    },
  },
  watch: {
    users() {
      this.statsCards[0].value = this.users.length;
      this.statsCards[1].value = this.todayUserCnt;
      this.statsCards[0].footerText = this.users[0].joindate;
      this.statsCards[1].footerText = this.users[0].joindate;
      this.userChart.data.series = [];
      this.userChart.data.series.push(this.timeCnt);
    },
  },
  created() {
    this.CLEAR_PARAMS();
    this.getUserList();
    for (let num = 0; num < 24; num++) {
      this.userChart.data.labels.push(num);
    }
  },

  methods: {
    ...mapActions(user, [
      "getUserList",
      "registUser",
      "deleteUser",
      "modifyUser",
      "idCheck",
    ]),
    ...mapMutations(user, ["SET_USER", "CLEAR_USER"]),
    ...mapMutations(board, ["SET_PARAMS", "CLEAR_PARAMS"]),
    rowClickListener(userid) {
      for (let user of this.users) {
        if (user.userid === userid) {
          this.SET_USER(user);
        }
      }
      this.type = "modify";
      this.show = true;
    },
    action() {
      if (this.type == "modify") {
        if (this.admin == true) {
          this.user.isadmin = 1;
        } else this.user.isadmin = null;
        this.modifyUser({
          user: this.user,
          adminCheck: this.admin,
          callback: () => {
            this.show = false;
            this.getUserList();
            this.admin = false;
          },
        });
      } else {
        if (!this.isIdCheck) alert("중복된 아이디입니다.");
        else {
          this.registUser({
            user: this.user,
            callback: () => {
              this.show = false;
              this.getUserList();
            },
          });
        }
      }
    },

    beforeRegistUser() {
      this.CLEAR_USER();
      this.show = true;
      this.type = "regist";
    },
    pageClick: function (button, pg) {
      this.SET_PARAMS({ pg: pg, key: this.params.key, word: this.params.word });
      this.getUserList();
    },
    deleteuser() {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.deleteUser({
          userid: this.user.userid,
          callback: () => {
            this.show = false;
            this.getUserList();
          },
        });
      }
    },
    search() {
      this.SET_PARAMS({ pg: 1, key: this.params.key, word: this.params.word });
      this.getUserList();
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
.word {
  border-radius: 4px;
  height: 36px;
  border-color: grey;
  border-width: 1px;
}
.view {
  width: 100%;
  padding: 8px 10px;
  margin: 3px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: whitesmoke;
  box-sizing: border-box;
  font-size: medium;
}
.box {
  min-height: 70vh;
}
</style>
