<template>
    <Header />
    <main class="container">
        <Title text="Patients" Class="title large" />
        <router-link to="/form/patients/new" class="btn btn-register">Register a new profile</router-link>
        <div>
            <List />
        </div>
    </main>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Title from '../components/Title.vue';
import List from '@/components/List.vue';
import Header from '@/components/Header.vue';

export default defineComponent({
    name: 'Patients',
    components: {
    Title,
    List,
    Header
}
})
</script>