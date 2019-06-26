import Vue from 'vue'
import Router from 'vue-router'

import Home from './views/Home.vue'
import List from './components/List.vue'
import Test from './components/Test.vue'
import Transfers from './components/Transfers.vue'
import Accounts from './components/Accounts.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/list',
      component: List
    },
    {
      path: '/test',
      component: Test
    },
    {
      path: '/transfers',
      component: Transfers
    },
    {
      path: '/accounts',
      component: Accounts
    },
    //redirect to home if page does not exist
    { path: '*', redirect: '/' }
  ]
})
