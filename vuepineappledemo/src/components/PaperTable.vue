<template>
  <table class="table">
    <thead>
      <slot name="columns">
        <th v-for="column in tableColumns" :key="column">{{ column }}</th>
      </slot>
    </thead>
    <tbody>
      <tr v-for="(item, index) in data" :key="index">
        <slot :row="item" v-if="listType == 'boardlist'">
          <td
            v-for="(column, index) in columns"
            :key="index"
            @click="moveBoardView(item.articleno)"
          >
            {{ itemValue(item, column) }}
          </td>
        </slot>
        <slot :row="item" v-if="listType == 'userlist'">
          <td
            v-for="(column, index) in columns"
            :key="index"
            @click="openUserModal(item.userid)"
          >
            {{ itemValue(item, column) }}
          </td>
        </slot>
      </tr>
    </tbody>
  </table>
</template>
<script>
export default {
  name: "paper-table",
  props: {
    columns: Array,
    tableColumns: Array,
    data: Array,
    listType: String,
    type: {
      type: String, // striped | hover
      default: "striped",
    },
    title: {
      type: String,
      default: "",
    },
    subTitle: {
      type: String,
      default: "",
    },
  },
  computed: {
    tableClass() {
      return `table-${this.type}`;
    },
  },
  methods: {
    hasValue(item, column) {
      return item[column.toLowerCase()] !== "undefined";
    },
    itemValue(item, column) {
      if (this.listType == "userlist" && column == "isadmin") {
        if (item[column] == "0") item[column] = "사용자";
        else if (item[column] == "1") item[column] = "관리자";
      }
      return item[column.toLowerCase()];
    },
    moveBoardView(articleno) {
      this.$router.push({
        name: "boardview",
        params: { articleno: articleno },
      });
    },
    openUserModal(userid) {
      this.$emit("openUserModal", userid);
    },
  },
};
</script>
<style></style>
