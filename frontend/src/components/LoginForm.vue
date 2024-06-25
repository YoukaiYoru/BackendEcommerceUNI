<template>
    <div class="container">
      <div class="login-container">
        <h1>Bienvenido!</h1>
        <form @submit.prevent="login">
          <div>
            <label for="username">Usuario:</label>
            <input type="text" id="username" v-model="username" required />
          </div>
          <div>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" v-model="password" required />
          </div>
          <button type="submit">Iniciar sesión</button>
          <p>No tienes una cuenta? <a href="#">Regístrate</a></p>
        </form>
      </div>
      <div class="side-container"></div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        username: '',
        password: '',
      };
    },
    methods: {
      login() {
        // Lógica para iniciar sesión
        console.log(`Usuario: ${this.username}, Contraseña: ${this.password}`);
      },
    },
  };
  </script>
  
  <style scoped>
  .container {
    display: flex;
    height: 100vh;
    width: 100vw;
  }
  
  .login-container {
    width: 90%;
    background-color: #ffffff;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  
  .side-container {
    width: 50%;
    background-color: #fddde6;
  }
  
  form {
    display: flex;
    flex-direction: column;
    width: 300px;
  }
  
  label {
    margin-bottom: 8px;
    font-weight: bold;
  }
  
  input {
    margin-bottom: 15px;
    padding: 10px;
    border: 1px solid #bc2828;
    border-radius: 8px;
  }
  
  button {
    padding: 10px;
    background-color: #800000;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #660000;
  }
  
  p {
    margin-top: 10px;
  }
  
  a {
    color: #800000;
    text-decoration: none;
  }
  
  a:hover {
    text-decoration: underline;
  }
  </style>
  