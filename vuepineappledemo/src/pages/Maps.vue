<template>
  <card class="card-map" title="Maps">
    <span slot="header" class="card-subtitle list-group-horizontal">
      <!--       <p class="card-category"> 거래정보 조회 </p>-->
      <search-bar/>
    </span>
    <div class="map">
      <div id="map"></div>
    </div>
    <div class="col-12">
      <card class="card-plain">
        <router-view/>
        <div class="table-full-width table-responsive">
          <house-table
            v-if="GHouseList"
            type="hover"
            :data="GHouseList"
            :columns="columns"
            :tableColumns="tableColumns"
          >
          </house-table>
        </div>
      </card>
    </div>
  </card>
</template>
<script>
import HouseTable from "@/components/house/HouseTable";
import HouseView from "@/components/house/HouseView";
import SearchBar from "@/components/house/SearchBar";
import {mapActions, mapGetters, mapMutations, mapState} from "vuex";

const house = "house";

// const tableColumns = [
//   "일련번호",
//   "아파트 이름",
//   "동 코드",
//   "동 이름",
//   "시 이름",
//   "군 이름",
//   "건축년도",
//   "지번",
// ];
export default {

  components: {
    HouseTable,
    HouseView,
    SearchBar,
  },
  computed: {
    // selectedHouse() {
    //   return this.$store.state.house.house;
    // },
    ...mapGetters({GHouseList: "house/GHouseList", selectedHouse: "house/SelectedHouse"}),

  },
  data() {
    return {
      selected: Object,
      infowindow: null,
      show: false,
      title: "Stripped Table",
      subTitle: "Here is a subtitle for this table",
      columns: [
        "aptCode",
        "aptName",
        "address",
        "dealPrice",
      ],
      tableColumns: [
        "일련번호",
        "아파트 이름",
        "지번",
        "거래가격"
      ]
    }
  },
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    ...mapMutations({setSelected:"house/SET_SELECTED_HOUSE"}),
    addKakaoMapScript() {
      const script = document.createElement("script");

      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=9863d35fc05716ac9975480bee9eb490";
      document.head.appendChild(script);
      script.onload = () => kakao.maps.load(this.initMap);
/*       var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(35.3351575394625, 129.01074046469583),
        level: 2,
      };
      var map = new kakao.maps.Map(container, options); */
    },
    initMap() {
      console.log("initMap");
      this.displayMarker(this.GHouseList)
    },
    movePage: function () {
      this.$router.push("detail");
    },
    displayMarker(val) {
      var markers = []
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(35.3351575394625, 129.01074046469583),
        level: 2,
      };
      var map = new kakao.maps.Map(container, options);
      console.log("displayMarkers");
      var bounds = new kakao.maps.LatLngBounds();

      if (!val) {
        bounds.extend(new kakao.maps.LatLng(
            value['latitude'],
            value['longitude']
          ));
          map.setBounds(bounds);
        return
      }
      val.forEach((value, index) => {
        console.log(value)
        // 마커를 생성합니다
        const marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(
            value['latitude'],
            value['longitude']
          ), // 마커의 위치
          clickable: true
        });

        // 마커에 표시할 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: val[index].aptName, // 인포윈도우에 표시할 내용
        });
        kakao.maps.event.addListener(marker, "click", () => {
          console.log(value)
          this.selected = value
          // this.$store.commit("house/SET_SELECTED_HOUSE", value)
          // this.state.house.commit("house/SET_SELECTED_HOUSE",value)
          console.log(this.selected)
        });
        // 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
        // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        (function (marker, infowindow) {
          // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
          kakao.maps.event.addListener(marker, "mouseover", function () {
            infowindow.open(map, marker);
          });

          // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
          kakao.maps.event.addListener(marker, "mouseout", function () {
            infowindow.close();
          });

          // marker.addListener("click",function () {
          //   console.log(value)
          //   this.selected = value
          //   console.log(this.selected)
          // })
          markers.push(marker)
          bounds.extend(new kakao.maps.LatLng(
            value['latitude'],
            value['longitude']
          ));
        })(marker, infowindow);
      }, this)
      map.setBounds(bounds);
    },

  },
  watch: {
    GHouseList: {
      handler(newValue, oldValue) {
        console.log("GHouseList", newValue)

        if (newValue == undefined || window.kakao.maps == undefined) return
        this.displayMarker(newValue);
      },

    },
    selected: {
      handler(newValue) {
        console.log("selected called")
        this.$store.commit(this.setSelected, newValue)
        this.movePage()
      },
    }
  },
};
</script>
<style>
.map {
  width: 100%;
  height: 400px;
}
</style>
