<template>
    <div class="none modal" id="modal" ref="modal">
        <div class="modal-content">
            <i class="fa-regular fa-user modal-icon"></i>
            <Title text="Do you want to cancel this appointment?" Class="title large" />
            <div class="modal-dados" v-if="appointment">
                <Title id="modal-name" :text="appointment.date + ' at ' + appointment.time" Class="title large" />
                <div class="margin-top">
                    <p class="title">{{ appointment.doctorName }}</p>
                    <p class="gray">{{ appointment.specialty }}</p>
                    <p class="title">{{ appointment.patientName }}</p>
                    <p class="gray">Patient</p>
                </div>
                <Fieldset>
                <p>Select the reason for cancellation:</p>
                <label class="field-label" for="reason">Reason</label>
                <select id="reason" name="reason" v-model="appointment.reasonForCancellation" class="field-input">
                    <option disabled value="">Select the reason</option>
                    <option value="PATIENT_GAVE_UP">Patient gave up</option>
                    <option value="LONG_WAITING_TIME">Long waiting time</option>
                    <option value="DOCTOR_CANCELED">Doctor canceled</option>
                    <option value="OTHER_REASON">Other reason</option>
                </select>
                <span class="error-message"></span>
            </Fieldset>
            </div>
            <Button @click="closeModal($event)" @keyup.enter="closeModal($event)" id="modal-deactivate"
                Class="btn form-btn" Value="Cancel appointment" />
            <ButtonReverse @click="closeModal($event)" @keyup.enter="closeModal($event)"
                Class="btn-reverse form-btn" Value="Don't cancel" />
        </div>
    </div>
</template>

<script lang="ts">
import IAppointment from '@/interfaces/IAppointment';
import axios from 'axios';
import { defineComponent } from 'vue';
import Button from './Button.vue';
import ButtonReverse from './ButtonReverse.vue';
import Fieldset from './Fieldset.vue';
import Title from './Title.vue';


export default defineComponent({
    name: "Modal",
    components: {
    Title,
    Button,
    ButtonReverse,
    Fieldset
},
    data() {
        return {
            token: sessionStorage.getItem('token'),
            appointment: null as IAppointment
        }
    },
    emits: ['list', 'treatForbidden'],
    props: {
        page: {
            type: String
        }
    },
    methods: {
        closeModal(e) {
            if (this.appointment.reasonForCancellation) {
                const url = `http://localhost:8081/${this.page}`
                axios.delete(url, {
                    headers: {
                        Authorization: `Bearer ${this.token}`
                    },
                    data: {
                        idAppointment: this.appointment.id,
                        reasonForCancellation: this.appointment.reasonForCancellation
                    }
                }).then(response => {
                    console.log(response)
                }).catch(error => {
                    console.log(error)
                })
            }
            e.target.parentNode.parentNode.classList.add('none')
            this.$emit('list')
        },
        showModal(appointment) {
            (this.$refs.modal as HTMLDivElement).classList.remove('none');
            this.appointment = appointment;
        }
    }
})
</script>

<style scoped>

.modal-content {
    background-color: #fefefe;
    border: 1px solid #888;
    border-radius: 1rem;
    margin: 4% auto;
    padding: 20px;
    width: 80%;
}

.title {
    font-size: 1.1rem;
    margin-bottom: 0;
    font-weight: 600;
}

.gray {
    font-size: 1.05rem;
    margin-top: 5px;
}

.large {
    font-size: 1.5rem;
}

.margin-top {
    margin-top: 2rem;
}
</style>