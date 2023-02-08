<template>
    <SmallLogo />
    <Search :page="page" :entities="entities" @filter="update" @list="list" />
    <ul class="list" id="list" v-if="entities && entities.length">
        <li class="list-item" v-for="entity,index in entities" :key="index">
            <h3 class="title">{{ entity.name }}
                <i @click="detail($event, entity.id, index)" @keyup.enter="detail($event, entity.id, index)"
                    class="fa-solid fa-caret-down fa-caret-up" tabindex="0"></i>
            </h3>
            <p v-if="page === 'doctors'">{{
                entity.specialty.charAt(0).toUpperCase() +
                    entity.specialty.toLowerCase().slice(1)
            }}</p>
            <p v-else-if="page === 'patients'">{{ entity.phone }}</p>
            <div class="none">
                <p class="gray">{{ entity.email }}</p>
                <p v-if="page === 'doctors'" class="gray">{{ entity.phone }}</p>
                <p v-else-if="page === 'patients'" class="gray">{{ entity.ssn }}</p>
                <p class="gray">{{ entity.address && entity.address.addressLine1 }}</p>
                <p class="gray">{{ entity.address && entity.address.addressLine2 }}</p>
                <p class="gray">{{ entity.address && entity.address.city }} {{ entity.address && entity.address.state }}
                    {{ entity.address && entity.address.postalCode }}</p>
                <router-link :to="`form/${page}/${entity.id}`">
                    <ButtonReverse Class="list-btn btn-reverse" Value="Edit"></ButtonReverse>
                </router-link>
                <ButtonReverse v-if="page === 'doctors'" @click="showModal(entity.id, entity.name, entity.specialty)"
                    @keyup.enter="showModal(entity.id, entity.name, entity.specialty)" Class="list-btn btn-reverse"
                    Value="Deactivate profile">
                </ButtonReverse>
                <ButtonReverse v-else-if="page === 'patients'" @click="showModal(entity.id, entity.name, entity.phone)"
                    @keyup.enter="showModal(entity.id, entity.name, entity.phone)" Class="list-btn btn-reverse"
                    Value="Deactivate profile">
                </ButtonReverse>
            </div>
        </li>
    </ul>
    <Modal ref="modal" />
</template>

<script lang="ts">
import axios from 'axios';
import { defineComponent } from 'vue';
import SmallLogo from './SmallLogo.vue';
import ButtonReverse from './ButtonReverse.vue';
import Modal from './Modal.vue';
import Search from './Search.vue';
import { Mask } from 'maska';
import IDoctor from '@/interfaces/IDoctor';
import IPatient from '@/interfaces/IPatient';


export default defineComponent({
    name: "List",
    components: {
        ButtonReverse,
        Modal,
        SmallLogo,
        Search
    },
    data() {
        return {
            page: this.$route.name as string,
            entities: [] as IDoctor[] || [] as IPatient[],
            token: sessionStorage.getItem('token'),
            phoneMask: new Mask({ mask: "(###) ###-####" }),
            ssnMask: new Mask({ mask: "###-##-####" })
        }
    },
    methods: {
        update(filteredEntities) {
            this.entities = filteredEntities
        },
        detail(event, id, index) {
            if (!this.entities[index].address) {
                const url = `http://localhost:8081/${this.page}/${id}`
                axios.get(url, {
                    headers: {
                        Authorization: `Bearer ${this.token}`
                    }
                }).then(response => {
                    const entity = response.data
                    entity.phone = this.phoneMask.masked(entity.phone)
                    if (entity.ssn) {
                        entity.ssn = this.ssnMask.masked(entity.ssn)
                    }
                    this.entities[index] = entity
                }).catch(error => {
                    const code = error.response.status
                    this.handleForbidden(code)
                })
            }

            event.target.classList.toggle('rotate')
            const li = event.target.parentNode.parentNode
            const divDetails = li.children[2]
            divDetails.classList.toggle('none')
        },
        list() {
            const url = `http://localhost:8081/${this.page}`
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${this.token}`
                }
            }).then(response => {
                this.entities = response.data.content
                if (this.page === 'patients') {
                    this.entities.forEach(entity => {
                        entity.phone = this.phoneMask.masked(entity.phone)
                    });
                }
            }).catch(error => {
                console.log(error)
                const code = error.response.status
                this.handleForbidden(code)
            })
        },
        handleForbidden(code) {
            if (code === 403) {
                sessionStorage.clear()
                this.$router.push('/login')
            }
        },
        showModal(id, name, specialty) {
            (this.$refs.modal as typeof Modal).showModal(id, name, specialty)
        }
    },
    created() {
        this.list()
    }
})
</script>

<style>
.list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    padding: 0;
}

.list-item {
    border: 3px solid #0b3b60;
    border-radius: 1rem;
    box-sizing: border-box;
    height: fit-content;
    margin: 1rem;
    padding-bottom: 0.5em;
    width: 18em;
    list-style-type: none;
    padding: 0;
}

.fa-caret-down {
    cursor: pointer;
    margin-left: 0.3rem;
    rotate: 0deg;
    transition: 0.3s;
}

.fa-caret-down.rotate {
    rotate: 180deg;
    transition: 0.3s;
}
</style>