<template>
  <v-container
    fill-height
    fluid
    grid-list-xl>
    <v-layout
      justify-center
      wrap
    >
      <v-flex
        xs12
      >
        <material-card
          color="green"
          title="Transferir a otra cuenta"
          text="Complete los datos"
        >
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
          >
            <v-container py-0>
              <v-layout wrap>
                <v-flex
                  xs12
                  md4
                >
                  <v-select
                    v-model="originCbu"
                    :items="accounts"
                    item-text="cbu"
                    :rules="[v => !!v || 'CBU de destino es requerido']"
                    label="Seleccione cuenta de orígen"
                    required
                  >
                    <template slot="item" slot-scope="data">
                      {{data.item.type}} - {{data.item.cbu}} - ${{data.item.balance}}
                    </template>
                  </v-select>
                </v-flex>
                <v-flex
                  xs12
                  md4
                >
                  <v-text-field
                    v-model="destinationCbu"
                    :rules="[v => !!v || 'CBU de destino es requerido']"
                    class="purple-input"
                    label="CBU Destino"
                    required
                  />
                </v-flex>
                <v-flex
                  xs12
                  md4
                >
                  <v-text-field
                    label="Monto"
                    class="purple-input"
                    type="number"
                    required/>
                </v-flex>
                <v-flex class="text-xs-center"
                  xs12
                >
                  <v-btn
                    :disabled="!valid"
                    class="mx-0 font-weight-light"
                    color="success"
                  >
                    Transferir
                  </v-btn>

                  <v-btn
                    color="error"
                    @click="reset"
                  >
                    Resetear Campos
                  </v-btn>
                </v-flex>
              </v-layout>
            </v-container>
          </v-form>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axiosClient from '../axios/client'

export default {
  data: () => ({
      valid: true,
      userId: null,
      loader: null,
      loading: false,
      accounts: [],
      originCbu: null,
      destinationCbu: null,
      sum: null
  }),
  created(){
    this.userId = this.$root.$data.userId;
  },
  mounted(){
    axiosClient.getAllAccountsByClientId(this.userId)
      .then(response=> {  
          this.accounts = response.data
          console.log(this.accounts)  
      })  
  },
   methods: {
    validate () {
      if (this.$refs.form.validate()) {
        this.snackbar = true
      }
    },
    reset () {
      this.$refs.form.reset()
    }
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
