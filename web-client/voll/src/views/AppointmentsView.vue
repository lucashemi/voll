<template>
    <Header/>
    <main class="container">
        <Title text="Appointments" Class="title large"/>
        <router-link to="/form/appointments/new" class="btn btn-register">Schedule new appointment</router-link>
        <div>
            <AppointmentsList />
        </div>
    </main>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Header from '@/components/Header.vue';
import Title from '../components/Title.vue';
import AppointmentsList from '@/components/AppointmentsList.vue';


export default defineComponent({
    name: 'AppointmentsView',
    components: {
    Header,
    Title,
    AppointmentsList
}
})
</script>