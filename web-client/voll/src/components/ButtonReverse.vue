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
.list-btn {
    background-color: #fff;
    border: 1px solid #0b3b60;
    border-radius: 0.5em;
    color: #0b3b60;
    cursor: pointer;
    font-size: 1rem;
    font-weight: 500;
    margin-right: 0.5em;
    padding: 0 1%;
    text-align: center;
}

.btn-reverse {
    background-color: #fbfbfb;;
    border: 2px solid #0b3b60;
    color: #0b3b60;
    cursor: pointer;
    font-weight: 400;
    margin-bottom: 0.5rem;
}

.btn-reverse:hover, .btn-reverse:focus {
    background-color: #0b3b60;
    color: #fbfbfb;;
}
</style>