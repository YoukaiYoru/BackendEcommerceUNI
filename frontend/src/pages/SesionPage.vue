<template>
    <div class="login-container">
        <div class="login-box">
            <button class="close-button">×</button>
            <h2>
                <span class="icon">👤</span>
                INICIA SESION
            </h2>
            <form @submit.prevent="submitForm">
                <div class="input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" v-model="email" required />
                </div>
                <div class="input-group">
                    <label for="password">Contraseña</label>
                    <input type="password" id="password" v-model="password" required />
                    <a href="#" class="forgot-password">¿Olvidaste tu contraseña?</a>
                </div>
                <button type="submit" class="login-button">Iniciar  Sesion</button>
                <button type="button" class="register-button">Registrate</button>
            </form>
        </div>
    </div>
    </template>
    <script>
export default {
  data() {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    submitForm() {
      // Lógica para manejar el envío del formulario
      console.log('Email:', this.email);
      console.log('Password:', this.password);
    }
  }
};
</script>
    
    
    
    <style >
    body, html {
    margin: 0;
    padding: 0;
    height: 100;
    font-family: Arial, sans-serif;
    background-color: #f6f2f2;
    
  
    }
    
    .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f2f6f6; 
    }
    
    .login-box {
    background-color: #1a1a1a;
    padding: 40px 90px;
    border-radius: 15px;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
    text-align: center;
    position:relative;
    max-width: 400px;
    width: 100%;
  
    
    }
    
    .close-button {
    position: absolute;
    top: 10px;
    right: 30px;
    padding: 10px 20px;
    background: none;
    border: none;
    font-size: 1.5rem;
    color: #fff;
    cursor: pointer;
    }
    
    h2 {
    color: #fff;
    margin-bottom: 10px;
    size: 30;
    }
    
    .icon {
    font-size: 1.5rem;
    vertical-align: middle;
    }
    
    .input-group {
    margin-bottom: 15px;
    }
    
    .input-group label {
    display: block;
    color: #fff;
    margin-bottom: 5px;
    text-align: left;
   
    }
    
    .input-group input {
    width: 100%;
    padding: 10px;
    border-radius: 15px;
    border: 1px solid #333;
    background-color: #303030;
    color: #fff;
    border-color: rgb(254, 0, 0);
    }
    
    .forgot-password {
    display: block;
    margin-top: 5px;
    text-align: right;
    color: #fef3f3;
    text-decoration: rgb(199, 21, 21);
    }
    
    .login-button,
    .register-button {
    width: 100%;
    padding: 10px;
    border: none;
    border-radius: 15px;
    margin-top: 10px;
    cursor: pointer;
    border-color: rgb(107, 52, 52);
    }
    
    .login-button {
    background-color: #a42222;
    color: #fff;
    }
    
    .register-button {
    background-color: #991c1c;
    color: #fff;
    }

    .login-button:hover,
    .register-button:hover {
      background-color: #6c535c;
    }
    .login-button:active,
    .register-button:active {
      background-color: #f70303;
    }
    </style>
    