<template>
    <h1 :class="Class"><img class="img" src="../assets/img/logo-reverse.png"></h1>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent ({
    name: 'Logo',
    props: {
        Class: {
            type: String,
            required: true
        }
    }
})
</script>