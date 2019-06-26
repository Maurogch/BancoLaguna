// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

// Components
import './components'

// Plugins
import './plugins'

// Sync router with store
import { sync } from 'vuex-router-sync'

// Application imports
import App from './App'
import i18n from '@/i18n'
import router from '@/router'
import store from '@/store'

//Test global variable


// Sync store with router
sync(store, router)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  data: { //Using this as global variables, accesed in other componets as this.$root.$data.var
    userId: 1,
    userName: "Mauro Guerreiro"
  },
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
