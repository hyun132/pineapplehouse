<template>
  <div class="col-12">
    <b-row>
      <b-col class="text-left mt-5"> </b-col>
    </b-row>
    <board-input-item type="register"></board-input-item>
  </div>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style scoped>
hr {
  background-color: black;
  height: 3px;
}
</style>
