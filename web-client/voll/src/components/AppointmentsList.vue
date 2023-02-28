<template>
    <SmallLogo />
    <Search :page="page" :entities="appointments" @filter="update" @list="list" />
    <ul class="list" id="list" v-if="appointments && appointments.length">
        <li class="list-item" v-for="appointment, index in appointments" :key="index">
            <h3 class="title">{{ appointment.date }}
                <i @click="detail($event)" @keyup.enter="detail($event)"
                    class="fa-solid fa-caret-down fa-caret-up" tabindex="0"></i>
            </h3>
            <p class="gray">{{ appointment.time }}</p>
            <p class="title">{{ appointment.doctorName }}</p>
            <p class="gray">{{ appointment.specialty }}</p>
            <p class="title">{{ appointment.patientName }}</p>
            <p class="gray">Patient</p>
            <div class="none">
                <router-link :to="`form/${page}/${appointment.id}`">
                    <ButtonReverse Class="list-btn btn-reverse" Value="Edit"></ButtonReverse>
                </router-link>
                <ButtonReverse @click="showModal(appointment)"
                    @keyup.enter="showModal(appointment)"
                    Class="list-btn btn-reverse" Value="Cancel appointment">
                </ButtonReverse>
            </div>
        </li>
    </ul>
    <AppointmentModal ref="modal" :page="page" />
</template>

<script lang="ts">
import axios from 'axios';
import { defineComponent } from 'vue';
import SmallLogo from './SmallLogo.vue';
import ButtonReverse from './ButtonReverse.vue';
import AppointmentModal from './AppointmentModal.vue';
import Search from './Search.vue';
import IAppointment from '@/interfaces/IAppointment';


export default defineComponent({
    name: "List",
    components: {
        ButtonReverse,
        AppointmentModal,
        SmallLogo,
        Search
    },
    data() {
        return {
            page: this.$route.name as string,
            token: sessionStorage.getItem('token'),
            appointments: [] as IAppointment[]
        }
    },
    methods: {
        update(filteredAppointments) {
            this.appointments = filteredAppointments
        },
        detail(event) {
            event.target.classList.toggle('rotate')
            const li = event.target.parentNode.parentNode
            const divDetails = li.children[6]
            divDetails.classList.toggle('none')
        },
        list() {
            const url = `http://localhost:8081/${this.page}`
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${this.token}`
                }
            }).then(response => {
                this.appointments = response.data.content
                this.appointments.forEach(appointment => {
                    appointment.date = (appointment.date.substring(5) + '/' + appointment.date.substring(0,4)).replaceAll('-','/')
                    appointment.time = appointment.time.substring(0,5)
                    appointment.specialty = appointment.specialty.charAt(0) + appointment.specialty.substring(1).toLowerCase()
                })
            }).catch(error => {
                console.log(error)
            })
        },
        handleForbidden(code) {
            if (code === 403) {
                sessionStorage.clear()
                this.$router.push('/login')
            }
        },
        showModal(appointment) {
            (this.$refs.modal as typeof AppointmentModal).showModal(appointment)
        }
    },
    created() {
        this.list()
    }
})
</script>

<style scoped>

.title {
    font-size: 1.1rem;
    margin-bottom: 0;
    font-weight: 600;
}

.gray {
    font-size: 1.05rem;
    margin-top: 5px;
}
</style>