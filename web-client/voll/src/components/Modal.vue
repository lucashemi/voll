<template>
    <div class="none modal" id="modal" ref="modal">
        <div class="modal-content">
            <i class="fa-regular fa-user modal-icon"></i>
            <Title text="Do you want to deactivate this profile?" Class="title large" />
            <div class="modal-dados">
                <Title id="modal-name" ref="modalName" text="Name" Class="title" />
                <div>
                    <p id="modal-info" ref="modalInfo" class="gray"></p>
                </div>
            </div>
            <p>By deactivating this profile, your information will be disabled for searches and future appointments.</p>
            <p>Make sure there are no appointments scheduled, if so, the deactivation cannot be completed.</p>
            <Button @click="closeModal($event)" @keyup.enter="closeModal($event)" id="modal-deactivate"
                Class="btn form-btn" Value="Deactivate this profile" />
            <ButtonReverse @click="closeModal($event)" @keyup.enter="closeModal($event)" Class="btn-reverse form-btn"
                Value="Cancel" />
        </div>
    </div>
</template>

<script lang="ts">
import axios from 'axios';
import { defineComponent } from 'vue';
import Button from './Button.vue';
import ButtonReverse from './ButtonReverse.vue';
import Title from './Title.vue';


export default defineComponent({
    name: "Modal",
    components: {
        Title,
        Button,
        ButtonReverse
    },
    data () {
        return {
            token: sessionStorage.getItem('token')
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
            e.target.parentNode.parentNode.classList.add('none')
        },
        showModal(id, name, info) {
            (this.$refs.modal as HTMLDivElement).classList.remove('none');
            (this.$refs.modalName as HTMLHeadingElement).innerHTML = name;

            if (this.page === 'doctors') {
                (this.$refs.modalInfo as HTMLParagraphElement).innerHTML = info.charAt(0).toUpperCase() + info.toLowerCase().slice(1);
            } else if (this.page === 'patients') {
                (this.$refs.modalInfo as HTMLParagraphElement).innerHTML = info
            }

            const deactivateElement = document.getElementById('modal-deactivate')
            const url = `http://localhost:8081/${this.page}/${id}`
            deactivateElement.onclick = () => {
                axios.delete(url, {
                    headers: {
                        Authorization: `Bearer ${this.token}`
                    }
                }).then(response => {
                    console.log(response)
                    this.$emit('list')
                }).catch(error => {
                    this.$emit('treatForbidden', error.response.status)
                })
            }
        }
    }
})
</script>

<style>
.modal {
    background-color: rgb(0, 0, 0);
    background-color: rgba(0, 0, 0, 0.4);
    height: 100%;
    left: 0;
    position: fixed;
    top: 0;
    width: 100%;
    z-index: 1;
}

.modal-content {
    background-color: #fefefe;
    border: 1px solid #888;
    border-radius: 1rem;
    margin: 7% auto;
    padding: 20px;
    width: 80%;
}

.modal-icon {
    color: #0b3b60;
    font-size: 4rem;
}
</style>