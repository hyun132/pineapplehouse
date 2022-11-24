<template>
  <div class="containerBody">
    <!-- 시 선택 -->
    <nav class="navbar navbar-expand-lg navbar-light background mb-5">
      <div class="container-fluid">
        <ul class="navbar-nav ml-auto">
          <b-form-select
            v-model="sidoCode"
            :options="$store.state.news.sidos"
            @change="set()"
            class="mx-1"
          ></b-form-select>
        </ul>
      </div>
    </nav>

    <!--news cards-->
    <div v-if="sidoCode">
      <p>[최신 20개]</p>
      <div class="row">
        <div
          class="col-md-6 col-xl-3"
          v-for="news in newsList"
          :key="news.newsid"
        >
          <news-card>
            <div class="numbers" slot="content">
              <b
                ><a :href="news.url">
                  <p style="font-size: 20px">{{ news.title }}</p></a
                ></b
              >
              <p>{{ news.content }}</p>
            </div>
          </news-card>
        </div>
      </div>
    </div>
    <div v-else>
      <h3 style="text-align: center">소식을 알고 싶은 지역을 선택해주세요.</h3>
    </div>
  </div>
</template>
<script>
import { NewsCard } from "@/components/index";
import { mapMutations, mapState } from "vuex";
const news = "news";
export default {
  components: {
    NewsCard,
  },
  /**
   * Chart data used to render news, charts. Should be replaced with server data
   */
  data() {
    return {
      sidoCode: null,
    };
  },
  watch: {
    sidoCode() {
      if (this.sidoCode == null) this.CLEAR_NEWS_LIST();
    },
  },
  computed: {
    ...mapState(news, ["newsList"]),
    sidoList() {
      // console.log("in SidoList", this.$store.state.news.sidos);
      return this.$store.state.news.sidos;
    },
  },
  created() {
    this.searchSido();
  },
  methods: {
    ...mapMutations(news, ["CLEAR_NEWS_LIST"]),
    searchSido() {
      // console.log("searchSido called");
      this.$store.dispatch("news/getSido");
    },
    set() {
      if (this.sidoCode) {
        let sidoCode = this.sidoCode + "00000000";
        this.$store.dispatch("news/getNewsList", sidoCode);
      }
    },
  },
};
</script>
<style scoped>
a:hover {
  color: #66615b;
}
a {
  color: #888179;
}
.background {
  background-color: #66615b;
}
</style>
