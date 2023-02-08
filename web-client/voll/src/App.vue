<template>
    <router-view/>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: "App"
});
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Open+Sans&display=swap');

:root {
  font-family: 'Open Sans', sans-serif;
}

body {
  background-color: #fff;
  color: #111;
  text-align: center;
  margin: 0;
}

.container {
  margin: 0 auto;
  max-width: 1300px;
}

.field-label {
    display: block;
    color: #0b3b60;
    font-weight: 700;
    margin: 0.3em auto;
}

.field-input {
    background-color: #fcfcfc;
    font-size: 0.95rem;
    border: 2px solid #0b3b60;
    border-radius: 1rem;
    padding: 5px;
}

.form-field-type {
    display: inline-block;
}

.error-message {
    display: block;
    color: darkred;
    position: absolute;
}

.btn-register {
    cursor: pointer;
    display: inline-block;
    margin: 0.5rem;
    padding: 1vw;
    text-decoration: none;
}

.form-btn {
    border-radius: 1rem;
    font-size: 1rem;
    height: 3rem;
    margin: 0.7rem;
    text-align: center;
    width: 18rem;
}

.gray {
    color: #666;
}

.none {
    display: none !important;
}

.logo {
    width: 6rem;
    margin: 0.5rem 0;
}
.logo-huge {
    margin: 0.5rem auto;
    width: 10rem;
}

.img {
    max-width: 100%;
}

a {
    text-decoration: none;
}

@media screen and (max-width: 900px) {
    .container {
      top: 1rem;
      width: auto;
      margin: 0 auto;
    }
    .btn-register {
     padding: 1rem; 
    }
}
</style>
