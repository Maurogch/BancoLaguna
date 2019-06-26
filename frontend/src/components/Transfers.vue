<template>
  <v-data-table
    :headers="headers"
    :items="accounts"
    class="elevation-1"
  >
    <template slot="headerCell" slot-scope="props">
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <span v-on="on">
            {{ props.header.text }}
          </span>
        </template>
        <span>
          {{ props.header.text }}
        </span>
      </v-tooltip>
    </template>
    <template v-slot:items="props">
      <td class="text-xs-right">{{ props.item.type }}</td>
      <td class="text-xs-right">{{ props.item.cbuOrigin }}</td>
      <td class="text-xs-right">{{ props.item.cbuDestination }}</td>
      <td class="text-xs-right">${{ props.item.sum }}</td>
      <td class="text-xs-right">{{ props.item.dateTime }}</td>
    </template>
  </v-data-table>
</template>

<script>
  import axiosClient from '../axios/client'

  export default {
    data () {
      return {
        headers: [
          { text: 'Tipo de cuenta', value: 'type', align: 'right', sortable: false,},
          { text: 'Cuenta orígen', value: 'cbuOrigin', align: 'right' },
          { text: 'Cuenta destino', value: 'cbuDestination', align: 'right' },
          { text: 'Monto', value: 'sum', align: 'right' },
          { text: 'Fecha', value: 'dateTime', align: 'right' }
        ],
        accounts: [
          /*
          cbuDestination,
          cbuOrigin,
          sum,
          type,
          dateTime
          */
        ]
      }
    },
    mounted(){
      axiosClient.getAllTransfersByClientId(1) //client id should be got from global variable
        .then(response=> {  
            this.accounts = response.data
            console.log(this.accounts)  
        })  
    }
  }
</script>