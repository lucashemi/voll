<template>
    <h2 class="small-logo">{{ text }}<img src="../assets/img/small-logo.png" alt="small-logo"></h2>
</template>

<script lang="ts">
import { defineComponent } from 'vue';


export default defineComponent({
    name: 'SmallLogo',
    props: {
        text: {
            type: String
        }
    }
})
</script>

<style>
.small-logo {
    color: #0b3b60;
}
.small-logo img {
    width: 2em;
}
</style>