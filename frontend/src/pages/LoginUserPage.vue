<template>
    <div id="app">
      <LoginForm />
    </div>
  </template>
  
  <script>
  import LoginForm from './components/LoginForm.vue';
  
  export default {
    name: 'App',
    components: {
      LoginForm,
    },
  };
  </script>
  
  <style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin: 0;
    padding: 0;
    height: 100vh;
    display: flex;
  }
  
  body {
    margin: 0;
    padding: 0;
  }
  
  html {
    height: 100%;
  }
  </style>
  