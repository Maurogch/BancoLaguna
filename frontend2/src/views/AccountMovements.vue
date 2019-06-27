<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout
      justify-center
      wrap
    >
    <!------------------>
    <v-flex
        md12
    >
      <material-card
          color="green"
          title="Ultimos Movimientos"
          text="Lista de transferencias hechas y recibidas"
      >
        <v-data-table
        :headers="headers"
        :items="items"
        :pagination.sync="pagination"
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
      </material-card>
    </v-flex>
  <!------------------>
      <v-flex
        md12
      >
        <material-card
          color="blue"
          title="Ultimos Movimientos"
          text="Lista de transferencias hechas y recibidas"
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
                class="subheading font-weight-light text-success text--darken-3"
                v-text="header.text"
              />
            </template>
            <template
              slot="items"
              slot-scope="{ item }"
            >
              <td class="text-xs-right">{{ item.type }}</td>
              <td class="text-xs-right">{{ item.cbuOrigin }}</td>
              <td class="text-xs-right">{{ item.cbuDestination }}</td>
              <td class="text-xs-right">${{ item.sum }}</td>
              <td class="text-xs-right">{{ item.dateTime }}</td>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
      <v-flex
        md12
      >
        <material-card
          color="purple"
          flat
          full-width
          title="Ultimos Movimientos"
          text="Lista de transferencias hechas y recibidas"
        >
          <v-data-table
            :headers="headers"
            :items="items.slice(0, 7)"
            hide-actions
          >
            <template
              slot="headerCell"
              slot-scope="{ header }"
            >
              <span
                class="subheading font-weight-light text--darken-3"
                v-text="header.text"
              />
            </template>
            <template
              slot="items"
              slot-scope="{ item }"
            >
              <td class="text-xs-right">{{ item.type }}</td>
              <td class="text-xs-right">{{ item.cbuOrigin }}</td>
              <td class="text-xs-right">{{ item.cbuDestination }}</td>
              <td class="text-xs-right">${{ item.sum }}</td>
              <td class="text-xs-right">{{ item.dateTime }}</td>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axiosClient from '../axios/client'

export default {
  data: () => ({
    userId: null,
    pagination: {
      sortBy: 'dateTime',
      descending: true
    },
    headers: [
          { text: 'Tipo de cuenta', value: 'type', align: 'right', sortable: false,},
          { text: 'Cuenta orígen', value: 'cbuOrigin', align: 'right' },
          { text: 'Cuenta destino', value: 'cbuDestination', align: 'right' },
          { text: 'Monto', value: 'sum', align: 'right' },
          { text: 'Fecha', value: 'dateTime', align: 'right', isDescending: true}
        ],
        items: [
          /*
          cbuDestination,
          cbuOrigin,
          sum,
          type,
          dateTime
          */
        ]
  }),
  created(){
    this.userId = this.$root.$data.userId;
  },
  mounted(){
    axiosClient.getAllTransfersByClientId(this.userId)
      .then(response=> {  
          this.items = response.data
          console.log(this.items)  
      })  
  }
}
</script>
