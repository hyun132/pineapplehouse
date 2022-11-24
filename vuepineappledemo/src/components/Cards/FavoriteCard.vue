<template>
  <card>
    <div>
      <div class="row">
        <div class="ml-auto mr-auto" v-if="$slots.content">
          <slot name="content"></slot>
        </div>
      </div>
    </div>
  </card>
</template>
<script>
import Card from "./Card.vue";

export default {
  name: "news-card",
  components: {
    Card,
  },
};
</script>
<style></style>
