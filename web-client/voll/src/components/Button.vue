<template>
    <input :value="Value" :class="Class" :id="Id" :type="Type">
</template>

<script lang="ts">
import { defineComponent } from 'vue';


export default defineComponent({
    name: 'Button',
    props: {
        Value: {
            type: String
        },
        Class: {
            type: String
        },
        Id: {
            type: String
        },
        Type: {
            type: String
        }
    }
})
</script>

<style>
.btn {
    background-color: #0b3b60;
    border: 2px solid #0b3b60;
    border-radius: 1rem;
    color: #fbfbfb;
    cursor: pointer;
    font-weight: 600;
}

.btn:hover, .btn:focus {
    background-color: #fbfbfb;
    color: #0b3b60;
}

.form-btn {
    font-size: 1rem;
    height: 3rem;
    margin: 0.7rem;
    width: 18rem;
}

@media screen and (max-width: 900px) {
    .form-btn {
        width: 16rem !important;
    }
}
</style>