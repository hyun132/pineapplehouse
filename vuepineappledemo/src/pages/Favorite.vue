<template>
  <div class="row containerBody">
    <div class="col-md-6 col-xl-3" v-for="data in favorite" :key="data.id">
      <favorite-card>
        <div style="text-align: center" slot="content">
          <p style="font-size: 20px">{{ data.dongName }}</p>
          <!-- <p>{{ data.aptName }}</p> -->
          <b-button variant="dark" @click="movePage(data)" class="mb-1"
            >관심 정보 보러 가기</b-button
          >
          <b-button variant="dark" @click="remove(data)"
            >관심 정보 삭제 하기</b-button
          >
        </div>
      </favorite-card>
    </div>
  </div>
</template>
<script>
import {mapActions, mapGetters, mapMutations} from "vuex";
import FavoriteCard from "../components/Cards/FavoriteCard.vue";
export default {
  components: {
    FavoriteCard,
  },
  data() {
    return {};
  },
  computed: {
    ...mapGetters({
      favorite: "user/favorite",
    }),
  },
  created() {
    console.log(this.favorite);
  },
  methods: {
    ...mapMutations({setHouse:"house/SET_SELECTED_HOUSE", setDong:"house/SET_DONG"}),
    ...mapActions({getFavHouse:"house/getFavHouse", deleteFavorite: "user/deleteFavorite"}),
    //여기에 집 하나만 가져오는 코드 추가해야함.
    movePage: function (selected) {
      if(selected.aptCode){
        this.getFavHouse({
        dong:selected.dongCode,aptCode:selected.aptCode
        })
        this.$router.push({ name: "detail" });
      }else{
        this.setDong(selected.dongCode)
        this.$router.push({ name: "map" });
      }
    },
    remove(selected) {
      this.deleteFavorite(selected.id);
    }
  },
};
</script>
<style></style>
