<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout wrap>
      
      <v-flex
        md12
      >
        <material-card
          color="orange"
          title="Cuentas Activas"
          text="Cajas de ahorro en pesos o dolares"
        >
          <v-data-table
            :headers="headers"
            :items="items"
            hide-actions
          >
            <template
              slot="headerCell"
              slot-scope="{ header }"
            >
              <span
                class="font-weight-light text-warning text--darken-3"
                v-text="header.text"
              />
            </template>
            <template
              slot="items"
              slot-scope="{ index, item }"
            >
              <td>{{ index + 1 }}</td>
              <td>{{ item.cbu }}</td>
              <td class="text-xs-left">{{ item.type }}</td>
              <td class="text-xs-right">${{ item.balance }}</td>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
      <v-flex xs12>
        <div class="text-xs-center">
          <v-btn
            large
            :loading="loading"
            :disabled="loading"
            color="secondary"
            @click="loader = 'loading'"
          >
            Solicitar Nueva Cuenta
          </v-btn>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axiosClient from '../axios/client'

export default {
  data: () => ({
      userId: null,
      loader: null,
      loading: false,
      headers: [
        {
          sortable: false,
          text: 'ID',
          value: 'id'
        },
        {
          text: 'CBU',
          value: 'cbu',
          align: 'left'
        },
        {
          sortable: false,
          text: 'Tipo',
          value: 'type'
        },
        {
          text: 'Saldo',
          value: 'balance',
          align: 'right'
        }
      ],
      items: []
  }),
  created(){
    this.userId = this.$root.$data.userId;
  },
  mounted(){
    axiosClient.getAllAccountsByClientId(this.userId)
      .then(response=> {  
          this.items = response.data
          console.log(this.items)  
      })  
  },
  methods: {
  
  },
  watch: {
    loader () { //Script for the loading button
      const l = this.loader
      this[l] = !this[l]

      setTimeout(() => (this[l] = false), 3000)

      this.loader = null
    }
  } 
}
</script>

<!--Styles for the loading button-->
<style>
  .custom-loader {
    animation: loader 1s infinite;
    display: flex;
  }
  @-moz-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-webkit-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-o-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
</style>