<template>
<v-app> <!--veutify needs to surround the whole app with this tag-->
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/list">List</router-link> |
      <router-link to="/test">Test Axios</router-link> |
      <router-link to="/accounts">Accounts</router-link> |
      <router-link to="/transfers">Transfers</router-link>
    </div>
    <router-view/>
  </div>
</v-app> 
</template>
 

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
