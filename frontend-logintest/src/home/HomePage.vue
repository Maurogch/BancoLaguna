<template>
    <div>
        <img class="center" src="./../imgs/logo.png">
        <p style="text-align: right;color: blue">Usuario: {{account.user.username + ' (' + account.user.firstName + ' ' + account.user.lastName + ')'}}</p>
        <h1>Hola {{account.user.firstName}}!</h1>
        <h4>Se ha logueado exitosamente al Online Banking de Banco Laguna</h4>
        <em v-if="users.loading">Cargando Cuentas...</em>
        <span v-if="users.error" class="text-danger">ERROR: {{users.error}}</span>
        <ul v-if="users.items">
            <li v-for="user in users.items" :key="user.id">
                <p>Place Holder para Cuentas disponibles</p>
            </li>
        </ul>
        <p>
            <router-link to="/login">Logout</router-link>
        </p>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
    computed: {
        ...mapState({
            account: state => state.account,
            users: state => state.users.all
        })
    },
    created () {
        this.getAllUsers();
    },
    methods: {
        ...mapActions('users', {
            getAllUsers: 'getAll',
            deleteUser: 'delete'
        })
    }
};
</script>