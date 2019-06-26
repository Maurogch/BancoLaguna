<template>
  <v-toolbar
    id="core-toolbar"

    flat
    prominent
    style="background: #eee;"
  >
    <div class="v-toolbar-title">
      <v-toolbar-title
        class="tertiary--text font-weight-light"
      >
        <v-btn
          v-if="responsive"
          class="default v-btn--simple"
          dark
          icon
          @click.stop="onClickBtn"
        >
          <v-icon>mdi-view-list</v-icon>
        </v-btn>
        {{ title }}
      </v-toolbar-title>
    </div>

    <v-spacer />
    <v-toolbar-items>
      <v-flex
        align-center
        layout
        py-2
      >
        <router-link
          v-ripple
          class="toolbar-items"
          to="/accounts"
        >
          <v-icon color="tertiary">mdi-view-dashboard</v-icon>
        </router-link>
        <v-menu
          bottom
          left
          content-class="dropdown-menu"
          offset-y
          transition="slide-y-transition">
          <!--<router-link
            v-ripple
            slot="activator"
            class="toolbar-items"
            to="/notifications"
          >--> <!--router-link changed to div to prevent redirection-->
          <div 
            v-ripple 
            slot="activator"
            class="toolbar-items">
            <v-badge
              color="error"
              overlap
            >
              <template slot="badge">
                {{ notifications.length }}
              </template>
              <v-icon color="tertiary">mdi-bell</v-icon>
            </v-badge>
          </div>
          <!--</router-link>>-->
          <v-card>
            <v-list dense>
              <v-list-tile
                v-for="notification in notifications"
                :key="notification"
                @click="onClick"
              >
                <v-list-tile-title
                  v-text="notification"
                />
              </v-list-tile>
            </v-list>
          </v-card>
        </v-menu>
        <div
          v-ripple
          class="toolbar-items"
        >
          <v-icon color="tertiary">mdi-account</v-icon>
        </div> <div>{{this.$root.$data.userName}}</div>
      </v-flex>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>

import {
  mapMutations
} from 'vuex'

export default {
  data: () => ({
    notifications: [
      'Notificaciones acá',
      'Posible notificación de movimiento en cuentas',
      'Posibilidad de redireccionar al hacer click en una notificación'
    ],
    title: null,
    responsive: false,
    responsiveInput: false
  }),

  watch: {
    '$route' (val) {
      switch(val.name) { 
        case "Accounts": { 
            this.title = 'Cuentas';
            break; 
        } 
        case "UserProfile": { 
            this.title = 'Perfil de Usuario';
            break; 
        } 
        case "AccountMovements": {
            this.title = 'Ultimos Movimientos';
            break;    
        } 
        case "Transfer": { 
            this.title = 'Transferencias'; 
            break; 
        }
        case "Notifications": { 
            this.title = 'Notificaciones'; 
            break; 
        }   
        default: { 
            this.title = val.name
            break;              
        } 
      }
    }
  },

  mounted () {
    this.onResponsiveInverted()
    window.addEventListener('resize', this.onResponsiveInverted)
  },
  beforeDestroy () {
    window.removeEventListener('resize', this.onResponsiveInverted)
  },

  methods: {
    ...mapMutations('app', ['setDrawer', 'toggleDrawer']),
    onClickBtn () {
      this.setDrawer(!this.$store.state.app.drawer)
    },
    onClick () {
      //
    },
    onResponsiveInverted () {
      if (window.innerWidth < 991) {
        this.responsive = true
        this.responsiveInput = false
      } else {
        this.responsive = false
        this.responsiveInput = true
      }
    }
  }
}
</script>

<style>
  #core-toolbar a {
    text-decoration: none;
  }
</style>
