<template>
    <h2 :class="Class">{{ text }}</h2>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'Title',
    props: {
        text: {
            type: String
        },
        Class: {
            type: String
        }
    }
})
</script>

<style>
.title {
    color: #0b3b60;
}

.large {
    font-size: 2rem;
}
</style>