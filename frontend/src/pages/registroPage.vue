<template>
    <div class="container">
      <div class="side-container"></div>
      <div class="register-container">
        <h3>Completa los datos para registrarte</h3>
        <form @submit.prevent="register">
          <div class="form-group">
            <label for="username">Usuario:</label>
            <input class="input-username" type="text" id="username" v-model="username" required />
          </div>
          <div class="form-group">
            <label for="password">Contraseña:</label>
            <input class="input-password" type="password" id="password" v-model="password" required />
          </div>
          <div class="form-group">
            <label for="firstName">Nombres:</label>
            <input class="input-firstName" type="text" id="firstName" v-model="firstName" required />
          </div>
          <div class="form-group">
            <label for="lastName">Apellidos:</label>
            <input class="input-lastName" type="text" id="lastName" v-model="lastName" required />
          </div>
          <div class="form-group">
            <label for="email">Correo electrónico:</label>
            <input class="input-email" type="email" id="email" v-model="email" required />
          </div>
          <div class="form-group">
            <label for="phone">Número de teléfono:</label>
            <input class="input-phone" type="tel" id="phone" v-model="phone" required />
          </div>
          <button type="submit">Registrarte</button>
        </form>
      </div>
    </div>
    </template>
    
    <script setup>
    import { ref } from 'vue';  
    const username = ref('');
    const password = ref('');
    const firstName = ref('');
    const lastName = ref('');
    const email = ref('');
    const phone = ref('');
  
    const register = () => {
      console.log(`Usuario: ${username.value}`);
      console.log(`Contraseña: ${password.value}`);
      console.log(`Nombres: ${firstName.value}`);
      console.log(`Apellidos: ${lastName.value}`);
      console.log(`Correo electrónico: ${email.value}`);
      console.log(`Número de teléfono: ${phone.value}`);
    };
  </script>
  
    
    <style scoped>
    .container {
    display: flex;
    height: 100vh;
    width: 100vw;
    color: black;
    }
    
    .side-container {
    width: 55%;
    background-color: #fddde6;
    }
    
    .register-container {
    width: 45%;
    background-color: #ffffff;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    }
    
    h3 {
    margin-bottom: 20px;
    }
    
    form {
    display: flex;
    flex-direction: column;
    width: 300px;
    }
    
    .form-group {
    display: flex;
    flex-direction: column;
    margin-bottom: 15px;
    }
    
    label {
    margin-bottom: 5px;
    font-weight: bold;
    }
    
    input {
    padding: 10px;
    border: 2px solid rgb(254, 0, 0);
    border-radius: 15px;
    background-color:#ffffff;
    }
    
    button {
    padding: 10px;
    background-color: #991c1c;
    color: white;
    border: none;
    border-radius: 15px;
    cursor: pointer;
    }
    
    button:hover {
    background-color: #660000;
    }
    </style>
    