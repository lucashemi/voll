<template>
    <header class="header">
        <router-link to="/">
            <LogoReverse Class="logo" />
        </router-link>
        <nav class="navbar">
            <ul class="navbar-ul">
                <li><router-link to="/doctors" class="link">Doctors</router-link></li>
                <li><router-link to="/patients" class="link">Patients</router-link></li>
                <li><router-link to="/appointments" class="link">Appointments</router-link></li>
                <li><router-link @click="logout()" to="/login" class="link">Sign out</router-link></li>
            </ul>
        </nav>
    </header>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import LogoReverse from './LogoReverse.vue';

export default defineComponent({
    name: 'Header',
    components: {
        LogoReverse
    },
    methods: {
        logout() {
            sessionStorage.clear()
        }
    }
})
</script>

<style scoped>
.header {
    background-color: #0b3b60;
    display: flex;
    justify-content: center;
    align-items: center;
}

.navbar-ul {
    display: flex;
    list-style-type: none;
    padding-left: 20px;
}

.link {
    color: #fbfbfb;
    ;
    font-size: 1.1rem;
    margin: 1rem;
    text-decoration: none;
}

.link:hover,
.link:focus {
    border-bottom: 2px solid #fbfbfb;
    ;
}

@media screen and (max-width: 900px) {
    .header {
        flex-direction: column;
    }

    .navbar-ul {
        padding-left: 0;
    }

    .navbar-ul li a {
        margin: 0.5rem;
    }
}
</style>