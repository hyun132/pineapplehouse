<template>
  <table class="table">
    <thead>
    <slot name="columns">
      <th v-for="(column, index) in tableColumns" :key="index">{{column}}</th>
    </slot>
    </thead>
    <tbody>
    <tr v-for="(item, index) in data" :key="index">
      <slot :row="item">
        <td
          v-for="(column, index) in columns"
          :key="index"
          @click="moveDetailView(item)"
        >
          {{ itemValue(item, column) }}
        </td>
      </slot>
    </tr>
    </tbody>
  </table>
</template>
<script>
import {mapMutations} from "vuex";

export default {
  name: 'house-table',
  props: {
    columns: Array,
    tableColumns : Array,
    data: Array,
    type: {
      type: String, // striped | hover
      default: "striped"
    },
    // title: {
    //   type: String,
    //   default: ""
    // },
    // subTitle: {
    //   type: String,
    //   default: ""
    // }
  },
  computed: {
    tableClass() {
      return `table-strip`;
    }
  },
  methods: {
    ...mapMutations({setHouse:"house/SET_SELECTED_HOUSE"}),
    hasValue(item, column) {
      return item[column.toLowerCase()] !== "undefined";
    },
    itemValue(item, column) {
      return item[column];
    },
    moveDetailView(aptInfo) {
      this.setHouse(aptInfo)
      this.$router.push({
        name: "detail"
      });
    },
  }
};
</script>
<style>
</style>
