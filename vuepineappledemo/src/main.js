/*!

 =========================================================
 * Vue Paper Dashboard - v2.0.0
 =========================================================

 * Product Page: http://www.creative-tim.com/product/paper-dashboard
 * Copyright 2019 Creative Tim (http://www.creative-tim.com)
 * Licensed under MIT (https://github.com/creativetimofficial/paper-dashboard/blob/master/LICENSE.md)

 =========================================================

 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 */
import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "@/store/index.js";
import { BModal, VBModal } from "bootstrap-vue";

import PaperDashboard from "./plugins/paperDashboard";
import "@/utils/vueBootstrap.js";

Vue.use(PaperDashboard);

Vue.filter("filterEnterToBr", (value) => {
  if (!value) return value;
  // 문자열에 enter값을 <br />로 변경.(html상에서 줄바꿈 처리)
  return value.replace(/(?:\r\n|\r|\n)/g, "<br />");
});

/* eslint-disable no-new */
new Vue({
  store,
  router: router,
  render: (h) => h(App),
}).$mount("#app");
