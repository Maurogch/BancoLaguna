<template>
    <ul>
        <li v-for="client in clients" :key="client.id"> {{client.name}}   </li>
    </ul>
</template>

<script>
import axiosClient from '../axios/client'

export default {
    data(){
        return{
            clients: []
        }
    },
    mounted(){
        axiosClient.getAll()
            .then(response=> {  
                console.log("entre a axios")
                this.clients = response.data
                console.log(this.clients)  
             })  
    }
}
</script>
