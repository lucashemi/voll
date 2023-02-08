<template>
<form action="" class="form" id="form">
    <Logo Class="logo-huge" />
    <Title text="Sign in" Class="title"/>
    <div class="form-div">
        <slot>Fieldset</slot>
    </div>
</form>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Title from './Title.vue'
import Logo from './Logo.vue'

export default defineComponent({
    name: 'Form',
    components: {
        Title,
        Logo
    }
})
</script>

<style>
.form {
    border: 3px solid #0b3b60;
    border-radius: 1rem;
    width: fit-content;
    margin: 2rem auto;
}

.form-div {
    display: flex;
    flex-direction: column;
    margin: 1rem 4rem;
}

@media screen and (max-width: 900px) {
    .form {
      margin: 1rem;
      width: auto;
    }
}
</style>