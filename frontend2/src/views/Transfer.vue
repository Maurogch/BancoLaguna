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
                    item-text="id"
                    :rules="[v => !!v || 'CBU de destino es requerido']"
                    label="Seleccione cuenta de orígen"
                    required
                  >
                    <template slot="selection" slot-scope="data">
                      {{data.item.cbu}}
                    </template>
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
                    v-model="sum"
                    label="Monto"
                    :rules="[v => !!v || 'Monto es requerido']"
                    class="purple-input"
                    type="number"
                    required/>
                </v-flex>
                <v-flex class="text-xs-center"
                  xs12
                >
                  <v-btn
                    :disabled="!valid"
                    :loading="loading"
                    class="mx-0 font-weight-light"
                    color="success"
                    @click="validate"
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
      <!--<v-snackbar
              :color="color"
              :bottom="false"
              :top="false"
              :left="false"
              :right="false"
              v-model="snackbar"
              dark
            >
              <v-icon
                color="white"
                class="mr-3"
              >
                mdi-bell-plus
              </v-icon>
              <div>{{message}}</div>
              <v-icon
                size="16"
                @click="snackbar = false"
              >
                mdi-close-circle
              </v-icon>
            </v-snackbar>-->
    </v-layout>
  </v-container>
</template>

<script>
import axiosClient from '../axios/client'
import axiosAccount from '../axios/account'

export default {
  data: () => ({
      valid: true,
      userId: null,
      loader: null,
      loading: false,
      accounts: [],
      originCbu: null,
      destinationCbu: null,
      sum: null,
      color: null,
      snackbar: false,
      message: null
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
      //self = this;
      if (this.$refs.form.validate()) {
        //this.snackbar = true
        this.loader = 'loading';
        console.log(this.originCbu);
        console.log(this.destinationCbu);
        console.log(this.sum);
        
        axiosAccount.transfer(this.originCbu, this.destinationCbu, this.sum)
        .then(function (response) {
          // handle success
          //this.color = "green";
          //this.message = response.data;
          //this.snackbar = true;
          alert(response.data)
          console.log(response);
        })
        .catch(function (error){
          // handle error
          //this.color = "red";
          //this.message = error.response.data;
          //this.snackbar = true;
          alert(error.response.data)
          console.log(error);
          console.log(error.response.data);
        })
      }
    },
    reset () {
      this.$refs.form.reset()
    },
    /*snack: function (message) {
      this.color = "red"
      this.message = message
      this.snackbar = true
    }*/
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
