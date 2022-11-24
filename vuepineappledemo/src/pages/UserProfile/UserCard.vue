<template>
  <card class="card-user">
    <div slot="image">
      <img src="@/assets/img/background.jpg" alt="..." />
    </div>
    <div>
      <div class="author">
        <img
          class="avatar border-white"
          src="@/assets/img/faces/face-0.jpg"
          alt="..."
        />
        <h4 class="title">
          {{ userInfo.username }}
          <br />
          <a href="#">
            <small>@{{ userInfo.userid }}</small>
          </a>
        </h4>
      </div>
    </div>
    <hr />
  </card>
</template>
<script>
import { mapGetters } from "vuex";
const user = "user";
export default {
  computed: {
    ...mapGetters(user, ["userInfo"]),
  },
  methods: {
    getClasses(index) {
      var remainder = index % 3;
      if (remainder === 0) {
        return "col-lg-3 offset-lg-1";
      } else if (remainder === 2) {
        return "col-lg-4";
      } else {
        return "col-lg-3";
      }
    },
  },
};
</script>
<style></style>
