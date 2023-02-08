<template>
    <main class="container">
        <Form>
            <Fieldset>
                <label class="field-label" for="email">E-mail</label>
                <input type="email" class="field-input margin-top" id="email" v-model="email" autofocus required/>
                <span class="error-message"></span>
            </Fieldset>
            <Fieldset>
                <label class="field-label" for="password">Password</label>
                <input type="password" class="field-input margin-top" id="password" v-model="password" required/>
                <span class="error-message"></span>
            </Fieldset>
            <div class="margin-top">
                <Button @click.prevent="login()" Value="Sign In" Class="btn form-btn" Id="send" Type="submit" />
            </div>
        </Form>
    </main>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Form from '../components/Form.vue';
import Fieldset from '../components/Fieldset.vue';
import Button from '../components/Button.vue';
import axios from 'axios';
import handleBadRequest from '@/utilities/handleBadRequest';

export default defineComponent({
    name: "Login",
    components: {
        Form,
        Fieldset,
        Button
    },
    data() {
        return {
            email: '',
            password: '',
            logged: sessionStorage.length > 0
        }
    },
    methods: {
        login() {
            axios.post(`http://localhost:8081/login`, {
                login: this.email,
                password: this.password
            })
                .then(response => {
                    sessionStorage.setItem("token", response.data.token)
                    this.$router.push('/')
                })
                .catch(error => {
                    const errors = error.response.data
                    const code = error.response.status
                    this.handleBadRequest(code, errors)
                })
        },
        handleBadRequest
    },
    mounted() {
        if (this.logged) {
            this.$router.push('/')
        }
    }
})
</script>

<style>
.margin-top {
    margin-top: 5px;
}
</style>