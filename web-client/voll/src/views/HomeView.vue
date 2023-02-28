<template>
    <Header />
    <main class="container margin-top">
        <Logo Class="logo-huge" />
        <Title text="Managing your clinic" Class="title"/>
        <p>Choose in which session you want to start:</p>
        <div class="cards">
            <router-link to="/doctors">
                <button class="btn card">
                    <i class="fa-sharp fa-solid fa-user-doctor big-icon"></i>
                    <h3 class="card-title">Doctors</h3>
                </button>
            </router-link>
            <router-link to="/patients">
                <button class="btn card">
                    <i class="fa-regular fa-user big-icon"></i>
                    <h3 class="card-title">Patients</h3>
                </button>
            </router-link>
            <router-link to="/appointments">
                <button class="btn card">
                    <i class="fa-solid fa-calendar-days big-icon"></i>
                    <h3 class="card-title">Appointments</h3>
                </button>
            </router-link>
        </div>
    </main>
</template>

<script lang="ts">
import Header from '@/components/Header.vue';
import { defineComponent } from 'vue';
import Logo from '../components/Logo.vue';
import Title from '../components/Title.vue';

export default defineComponent({
    name: 'Home',
    components: {
        Logo,
        Title,
        Header
    },
    created() {
        if (!sessionStorage.getItem('token')) {
                this.$router.push('/login')
        }
    }
})

</script>

<style scoped>
.cards {
    align-items: center;
    display: flex;
    justify-content: center;
    margin-top: 1.5rem;
}

.card {
    border-radius: 1em;
    margin: 3em;
    padding: 2em;
    height: 11rem;
    width: 15rem;
}

.big-icon {
    font-size: 3rem;
}

.margin-top {
    margin-top: 2rem;
}

@media screen and (max-width: 900px) {
    .cards {
        flex-direction: column;
        margin-top: 1rem;
    }

    .card {
        margin: 1rem;
        width: 50vw;
    }

    .cards a {
        width: 100vw;
    }
}
</style>