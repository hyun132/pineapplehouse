<template>
  <house-card>
    <div class="text-center" v-if="GDetail">
      <card>
        <div v-if="GDetail">
        <!-- <div class="card-image"> -->
          <img :src="GHouse.representativePhoto" width="400px" height="300px" />
          <!-- <img v-else src="@/assets/img/no_img.png"/> -->
        </div>
        <div class="card-header" v-if="GDetail">
          <slot name="header">
            <h4 v-if="GHouse" class="card-title">
              {{ GHouse.aptName }}
            </h4>
            <p class="card-category">
              <slot v-if="GHouse" name="subTitle">
                {{ GHouse.aptName }}
              </slot>
            </p>
          </slot>
          <!-- <b-button @click="testFavorite" class="mb-2 mt-1">관심 아파트 등록</b-button> -->
        </div>

        <div class="table-full-width table-responsive">
          <table class="table">
            <tbody v-if="GHouse">
              <tr v-for="(column, index) in columns" :key="index">
                <th scope="row" v-if="koColumns[index]">{{koColumns[index]}}</th>
                <td>{{ GHouse[column] }}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="col-12">
          <chart-card v-if="data" title='매매가 변동' :chart-data="data" :chart-options="options" chartType = "Line">
            <span slot="footer">
              <i class="ti-reload"></i> Updated 3 minutes ago
            </span>
            <div slot="legend">
              <i class="fa fa-circle text-info"></i> Open
              <i class="fa fa-circle text-danger"></i> Click
              <i class="fa fa-circle text-warning"></i> Click Second Time
            </div>
          </chart-card>
        </div>
      </card>
    </div>
  </house-card>
</template>
<script>
import HouseCard from "@/components/house/HouseCard";
import {mapActions, mapGetters} from "vuex";
import Card from '../Cards/Card.vue';
import HouseTable from "@/components/house/HouseTable";
import Chartist from "chartist";
import chartCard from "@/components/Cards/ChartCard";
import Icons from '../../pages/Icons.vue';
import Button from '../Button.vue';

export default {
  name: 'house-view',
  components: {
    HouseCard,
    Card,
    HouseTable,
    chartCard,
    Icons,
    Button
  },
  data() {
    return {
      columns: [
        "dongCode",
        "aptName",
        "pyoeng",
        "address",
        "roadAddress",
        "totalHouseholdCount",
        "totalLeaseHouseholdCount",
        "highFloor",
        "lowFloor",
        "aptCode",
        "batlRatio",
        "btlRatio",
        "parkingPossibleCount",
        "constructionCompanyName",
        "heatMethodTypeCode",
        "supplyArea",
        "exclusiveArea",
        "exclusiveRate",
        "roomCnt",
        "bathroomCnt",
        "householdCountByPyeong",
        "dealPrice",
        "leasePrice",
        "rentPrice"
      ],
      koColumns: [
        "동코드",
        "아파트 이름",
        "평",
        "주소",
        "도로명 주소",
        "총 가구 수",
        "총 임대 가구 수",
        "최고층",
        "최저층",
        "아파트 코드",
        "용적률",
        "건폐율",
        "주차 가능 대수",
        "건설사",
        "난방타입",
        "공급면적",
        "전용면적",
        "전용률",
        "방 수",
        "화장실 수",
        "해당 면적 세대 수",
        "거래가격",
        "임대가격",
        "임대료"
      ],
      data: null,
      options: null,
    }
  },
  computed: {
    ...mapGetters({GHouse: "house/GHouse", GDetail: "house/GDetail", chartData: "house/chartData"}),
  },
  methods: {
    ...mapActions({getDetail: "house/detailHouse", getChartData: "house/getChartData"}),
  },
  watch: {
    GHouse(){
      if(this.GHouse) this.getDetail(this.GHouse["aptCode"])
    },
    GDetail(){
        if(this.Ghouse) this.representativePhoto = this.Ghouse['representativePhoto']
        this.getChartData(this.GDetail["aptCode"])
        this.data= {
          labels:
          this.chartData.map((a) => a["marketPrice"]),

          series: [
            this.chartData.map((a) => parseInt(a["lowerMarketPrice"])),
            this.chartData.map((a) => parseInt(a["upperMarketPrice"])),
          ],
        }
        this.options = {
        low: Math.max(this.data.series[0]),
        high: Math.min(this.data.series[1]),
        showArea: true,
        height: "245px",
        axisX: {
          showGrid: false,
        },
        lineSmooth: Chartist.Interpolation.simple({
          divisor: 2,
        }),
        showLine: true,
        showPoint: false,
      }
    }
  },
  computed: {
    ...mapGetters({GHouse: "house/GHouse", GDetail: "house/GDetail", chartData: "house/chartData",userInfo:"user/userInfo"}),
  },
  methods: {
    ...mapActions({getDetail: "house/detailHouse", getChartData: "house/getChartData",addFavorite:"user/addFavorite"}),
    testFavorite(){
      this.addFavorite({dongCode:this.GHouse.dongCode,aptCode:this.GHouse.aptCode,userid:this.userInfo.userid})
    }

  },
  mounted() {
    console.log(this.GHouse)
    if(this.GHouse) this.getDetail(this.GHouse["aptCode"])
    // this.getDetail(this.GHouse["aptCode"])
    console.log("housesview called")
  },
}
</script>
<style>
</style>
