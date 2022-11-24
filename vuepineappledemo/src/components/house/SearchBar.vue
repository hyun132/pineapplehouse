<template>
  <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <ul class="navbar-nav ml-auto" >

        <b-form-select
          v-model="sidoCode"
          :options="SidoList"
          @change="searchGugun"
          class="mx-1"
        ></b-form-select>
        <b-form-select
          v-model="gugunCode"
          :options="GugunList"
          @change="searchDong"
          class="mx-1"
        ></b-form-select>
        <b-form-select
          v-model="dongCode"
          :options="DongList"
          @change="searchHouse"
          class="mx-1"
        ></b-form-select>

        </ul>
        <b-button @click="testFavorite" class="mt-2">관심 지역 등록</b-button>
      </div>
  </nav>
</template>

<script>
import { mapActions,mapGetters } from 'vuex'
// this.sidoList.find((element) => (element.value == "11"))
import Button from '../Button.vue';
export default {
  components: { Button },
  data() {
    return {
      dongCode: null,
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapGetters('user',["userInfo"]),
    ...mapGetters('house', ["SidoList", "DongList", "GugunList"]),
  },
  created() {
    this.searchSido();

    console.log("옵저버니?")
    console.log(this.$store.state.house.sidos);
    // console.log(this.sidos[1])
    // this.sidos.forEach((sido) => console.log(sido.text));
    // if(this.$store.state.house.favDongCode){
    //   this.$store.dispatch('house/getGugun',this.$store.state.house.favDongCode)
    //   this.$store.dispatch('house/getDong',this.$store.state.house.favDongCode)

    //   // this.sidoCode = this.$store.state.house.sidoCode
    //   // this.gugunCode = this.$store.state.house.gugunCode
    // }
    // console.log(this.$store.state.house.favDongCode);
    // this.isHovered = this.$store.state.user.favorite.find((item)=> (item.dongCode == this.dongCode && item.aptCode==null))
  },
  // 여기서 올 때 sidocode 다 들고 옴 어차피
  // 그러면 sidolist 있음 => change gugunlist
  //
  methods: {
    ...mapActions({addFavorite : "user/addFavorite"}),

    testFavorite(){
      if(this.dongCode) {
        this.addFavorite({dongCode:this.dongCode,userid:this.userInfo.userid,dongName: "우앙"})
        this.searchHouse(this.dongCode)
      }
    },
    searchSido() {
      console.log("searchSido called")
      this.$store.dispatch('house/getSido').then(() => {
        console.log("시도리스트", this.SidoList);
      })
    },
    searchGugun() {
      console.log("searchGugun called",this.sidoCode)
      this.$store.dispatch('house/getGugun',this.sidoCode)
    },
    searchDong() {
      console.log("searchDong called",this.gugunCode)
      this.$store.dispatch('house/getDong',this.gugunCode)
    },
    searchHouse() {
      console.log("searchHouse called")
      this.$store.dispatch('house/getHouseList',{
        dong: this.dongCode,
      })
    }
  },
  watch:{

  }
};
</script>


<style scoped>

</style>
