<template>
  <div class="col-12">
    <b-row>
      <b-col class="text-left mt-5"> </b-col>
    </b-row>
    <board-input-item type="modify"></board-input-item>
  </div>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardModify",
  data: function () {
    return {};
  },
  components: {
    BoardInputItem,
  },
};
</script>

<style scoped></style>
