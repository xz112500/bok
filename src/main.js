// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Element from 'element-ui'
import router from './router'
import '../src/../css/main.css'
import 'element-ui/lib/theme-chalk/index.css';
/*import '../js/jquery'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'*/


Vue.use(Element)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  mode:'hash',
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
